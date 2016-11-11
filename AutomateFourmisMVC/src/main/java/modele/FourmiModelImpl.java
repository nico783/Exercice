package main.java.modele;

import java.util.ArrayList;
import java.util.List;

import main.java.domainobject.Couple;
import main.java.domainobject.Direction;
import main.java.domainobject.Fourmi;
import main.java.observer.ObservateurCelluleValue;
import main.java.observer.ObservateurFourmi;

/**
 * @author nicolasbenizri
 * 
 *         Implémentation du modele de la fourmi de Langton.
 *
 */
public class FourmiModelImpl implements FourmiModel {

	private List<ObservateurFourmi> observateurFourmis = new ArrayList<>();

	private List<ObservateurCelluleValue> observateurCellules = new ArrayList<>();

	private int nombrePas;

	private int nombreLignes;

	private int nombreColonnes;

	private boolean[][] damier;

	private Fourmi fourmi;

	/**
	 * Constructeur du modele. Prend en paramètre le nombre de lignes et
	 * colonnes du damier. Initialise la position de la fourmi.
	 * 
	 * @param nombreLignes
	 * @param nombreColonnes
	 */
	public FourmiModelImpl(int nombreLignes, int nombreColonnes) {
		super();
		this.nombreLignes = nombreLignes;
		this.nombreColonnes = nombreColonnes;
		fourmi = new Fourmi();
		Couple<Integer> positionInitiale = new Couple<Integer>(
				(int) Math.floor((double) (nombreLignes / 2)),
				(int) Math.floor((double) (nombreColonnes / 2)));
		fourmi.setPosition(positionInitiale);
		damier = new boolean[nombreLignes][nombreColonnes];
		damier[positionInitiale.getAbscisse()][positionInitiale.getOrdonnee()] = true;
	}

	@Override
	public void deplacerFourmi() {
		deplacerFourmi(fourmi.getDirection());
		notifierObservateurFourmis();
	}

	/**
	 * Algorithme de deplacement de la fourmi de Langton.
	 */
	private void deplacerFourmi(Direction direction) {
		int x = fourmi.getPosition().getAbscisse();
		int y = fourmi.getPosition().getOrdonnee();

		if (direction.isHaute()) {
			if (y != this.nombreLignes - 1) {
				++y;
			} else {
				--y;
			}
		} else if (direction.isDroite()) {
			if (x != this.nombreColonnes - 1) {
				++x;
			} else {
				--x;
			}
		} else if (direction.isBasse()) {
			if (y != 0) {
				--y;
			} else {
				++y;
			}
		} else {
			if (x != 0) {
				--x;
			} else {
				++x;
			}
		}

		fourmi.getPosition().setAbscisse(x);
		fourmi.getPosition().setOrdonnee(y);

		// La direction que prend la fourmi quand elle arrive sur une case
		// dépend de la valeur de cette derniere
		fourmi.setDirection(damier[x][y]);

		// Quand la fourmi arrive sur une case elle change la valeur de celle-ci
		damier[x][y] = !damier[x][y];

		++nombrePas;
		System.out.println(nombrePas);
	}

	@Override
	public boolean[][] getDamier() {
		return damier;
	}

	@Override
	public int getNombreLignes() {
		return nombreLignes;
	}

	@Override
	public int getNombreColonnes() {
		return nombreColonnes;
	}

	@Override
	public Couple<Integer> getPositionFourmi() {
		return fourmi.getPosition();
	}

	@Override
	public void changeCellValue(int abscisse, int ordonnee) {
		damier[abscisse][ordonnee] = !damier[abscisse][ordonnee];
		notifierObservateurCelluleValue();
	}

	/*
	 * Méthodes de l'interface observable.
	 */
	@Override
	public void notifierObservateurFourmis() {
		for (ObservateurFourmi obs : observateurFourmis) {
			obs.actualiserFourmi();
		}
	}

	@Override
	public void notifierObservateurCelluleValue() {
		for (ObservateurCelluleValue obs : observateurCellules) {
			obs.actualiserCellule();
		}
	}

	@Override
	public void enregistrerObservateurFourmi(ObservateurFourmi o) {
		observateurFourmis.add(o);
	}

	@Override
	public void supprimerObservateurFourmi(ObservateurFourmi o) {
		int i = observateurFourmis.indexOf(o);
		if (i >= 0) {
			observateurFourmis.remove(i);
		}
	}

	@Override
	public void enregistrerObservateurCelluleValue(ObservateurCelluleValue o) {
		observateurCellules.add(o);
	}

	@Override
	public void supprimerObservateurCelluleValue(ObservateurCelluleValue o) {
		int i = observateurCellules.indexOf(o);
		if (i >= 0) {
			observateurCellules.remove(i);
		}
	}

}
