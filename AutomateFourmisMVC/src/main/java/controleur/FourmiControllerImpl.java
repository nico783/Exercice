package main.java.controleur;

import javax.swing.SwingWorker;

import main.java.modele.FourmiModel;
import main.java.vue.FourmiView;

/**
 * @author nicolasbenizri
 * 
 *         Implementation du controleur. Le controleur fait le lien entre la vue
 *         et le modele.
 *
 */
public class FourmiControllerImpl implements FourmiController {

	/**
	 * Durée en milliseconde entre chaque pas de la fourmi.
	 */
	protected static final long TIME_BETWEEN_STEP = 10;

	private FourmiView vue;

	private FourmiModel model;

	private boolean continuer;

	/**
	 * Constructeur du controleur. Le model est passé en paramètre et permet
	 * d'initialiser la vue.
	 * 
	 * @param model
	 */
	public FourmiControllerImpl(FourmiModel model) {
		super();
		this.model = model;
		this.vue = new FourmiView(model, this);
		this.vue.affichage();
	}

	@Override
	public void deplacerFourmi() {
		vue.desactiverGo();
		continuer = true;
		SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				while (continuer) {
					model.deplacerFourmi();
					Thread.sleep(TIME_BETWEEN_STEP);
				}
				return null;
			}
		};
		sw.execute();
	}

	@Override
	public void setContinuer(boolean continuer) {
		this.continuer = continuer;
		vue.activerGo();
	}

	@Override
	public void changeCellValue(int abscisse, int ordonnee) {
		model.changeCellValue(abscisse, ordonnee);
	}

}
