package main.java.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import main.java.controleur.FourmiController;
import main.java.domainobject.Couple;
import main.java.modele.FourmiModel;
import main.java.observer.ObservateurCelluleValue;
import main.java.observer.ObservateurFourmi;

/**
 * @author nicolasbenizri
 * 
 *         Vue permettant de représenter sur un damier de cellules (JPanel)
 *         l'évolution d'une fourmi de Langton. Cette classe correspond à la vue
 *         du design pattern MVC. Elle est un observateur du modele.
 *
 */
public class FourmiView implements ObservateurFourmi, ActionListener {

	private FourmiModel model;
	private FourmiController controller;

	private Cellule[][] damier;
	private JButton buttonGo;
	private JButton buttonStop;

	/**
	 * Constructeur de la vue. Initialisation du model et du controleur. La vue
	 * est enregistrée à ce niveau auprès du modele.
	 * 
	 * @param model
	 * @param controller
	 */
	public FourmiView(FourmiModel model, FourmiController controller) {
		super();
		this.model = model;
		this.controller = controller;
		model.enregistrerObservateurFourmi(this);
	}

	/**
	 * Méthode d'affichage de la vue. La méthode actualiser est appelée pour
	 * retranscrire l'état initial du model (fourmi, cases coloriées).
	 */
	public void affichage() {

		/*
		 * Damier
		 */
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
		int nombreLignes = model.getNombreLignes();
		int nombreColonnes = model.getNombreColonnes();
		JPanel pan = new JPanel(new GridLayout(nombreLignes, nombreColonnes));
		damier = new Cellule[nombreLignes][nombreColonnes];
		for (int j = 0; j < nombreColonnes; j++) {
			for (int i = 0; i < nombreLignes; i++) {
				Cellule cell = new Cellule(i, j);
				damier[i][j] = cell;
				cell.setBorder(blackline);
				cell.addMouseListener(cell);
				pan.add(cell);
			}
		}

		/*
		 * Boutons
		 */
		buttonGo = new JButton("GO");
		buttonGo.addActionListener(this);

		buttonStop = new JButton("STOP");
		buttonStop.addActionListener(new Stopper());

		JPanel panneauButton = new JPanel();
		panneauButton.add(buttonGo);
		panneauButton.add(buttonStop);

		/*
		 * Description
		 */
		JTextArea description = getDescription();
		JPanel panneauDescription = new JPanel();
		panneauDescription.add(description);

		/*
		 * Frame
		 */
		JFrame cadre = new JFrame();
		cadre.setTitle("Fourmi de Langton");
		cadre.setSize(800, 600);
		cadre.setLocationRelativeTo(null);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cadre.getContentPane().setLayout(new BorderLayout());
		cadre.getContentPane().add(BorderLayout.SOUTH, panneauButton);
		cadre.getContentPane().add(BorderLayout.NORTH, panneauDescription);
		cadre.getContentPane().add(BorderLayout.CENTER, pan);
		cadre.setVisible(true);

		actualiserFourmi();
	}

	private JTextArea getDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("La fourmi de Langton est modelisée par la cellule noire. \n");
		sb.append("Lorsque la fourmi arrive sur une case blanche elle tourne à gauche et la couleur de la case devient rouge. \n");
		sb.append("Lorsque la fourmi arrive sur une case rouge elle tourne à droite et la couleur de la case devient blanche. \n");
		JTextArea description = new JTextArea(sb.toString());
		description.setEditable(false);
		description.setEnabled(false);
		return description;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.deplacerFourmi();
	}

	@Override
	public void actualiserFourmi() {
		Couple<Integer> positionFourmi = model.getPositionFourmi();
		Integer ordonneeFourmi = positionFourmi.getOrdonnee();
		Integer abscisseFourmi = positionFourmi.getAbscisse();

		// (Performance) Nous n'actualisons pas la totalité des cellules de la
		// table, mais seulement les cellules "adjacentes" à la position de la
		// fourmi.
		int ordonneeMax = Math.min(ordonneeFourmi + 1,
				model.getNombreColonnes() - 1);
		int ordonneeMin = Math.max(ordonneeFourmi - 1, 0);
		int abscisseMax = Math.min(abscisseFourmi + 1,
				model.getNombreLignes() - 1);
		int abscisseMin = Math.max(abscisseFourmi - 1, 0);

		for (int j = ordonneeMin; j <= ordonneeMax; j++) {
			for (int i = abscisseMin; i <= abscisseMax; i++) {
				Cellule cell = damier[i][j];
				if (abscisseFourmi == i && ordonneeFourmi == j) {
					cell.setBackground(Color.BLACK);
				} else if (model.getDamier()[i][j]) {
					cell.setBackground(Color.RED);
				} else {
					cell.setBackground(Color.WHITE);
				}
			}
		}
	}

	/**
	 * Méthode permettant de désactiver le bouton GO
	 */
	public void desactiverGo() {
		buttonGo.setEnabled(false);
	}

	/**
	 * Méthode permettant d'activer le bouton GO
	 */
	public void activerGo() {
		buttonGo.setEnabled(true);
	}

	/**
	 * @author nicolasbenizri
	 * 
	 *         Classe privée permettant de stopper l'évolution de la foumi de
	 *         Langton.
	 *
	 */
	private class Stopper implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.setContinuer(false);
		}
	}

	/**
	 * @author nicolasbenizri
	 * 
	 *         Classe privée définissant l'objet cellule utilisé dans la vue.
	 *         Une cellule écoute les événements liés aux actions de la souris.
	 *         Une cellule est également un observateur des "cellules" du
	 *         modele: lorsqu'une cellule est cliquée par la souris, elle
	 *         devient observateur de la "cellule" correspondante au niveau du
	 *         modele. Si la valeur (true/false) de la cellule du modele change,
	 *         la cellule de la vue est actualisée.
	 *
	 */
	private class Cellule extends JPanel implements ObservateurCelluleValue,
			MouseListener {

		private static final long serialVersionUID = -2230158403508830186L;

		private int abscisse;

		private int ordonnee;

		private Cellule(int abscisse, int ordonnee) {
			super();
			this.abscisse = abscisse;
			this.ordonnee = ordonnee;
			this.setBackground(Color.WHITE);
		}

		@Override
		public void actualiserCellule() {
			Couple<Integer> positionFourmi = model.getPositionFourmi();
			Integer ordonneeFourmi = positionFourmi.getOrdonnee();
			Integer abscisseFourmi = positionFourmi.getAbscisse();
			Cellule cell = damier[abscisse][ordonnee];
			if (abscisseFourmi == abscisse && ordonneeFourmi == ordonnee) {
				cell.setBackground(Color.BLACK);
			} else if (model.getDamier()[abscisse][ordonnee]) {
				cell.setBackground(Color.RED);
			} else {
				cell.setBackground(Color.WHITE);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			model.enregistrerObservateurCelluleValue(this);
			controller.changeCellValue(abscisse, ordonnee);
			model.supprimerObservateurCelluleValue(this);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

}
