package main.java.modele;

import main.java.domainobject.Couple;
import main.java.observer.Observable;

/**
 * @author nicolasbenizri
 * 
 *         Interface du modele de la fourmi de Langton. Le modèle étend
 *         l'interface Observable. La vue (FourmiView) est un observateur du
 *         modele pour être notifié lors des changements d'état de ce dernier.
 *
 */
public interface FourmiModel extends Observable {

	/**
	 * @return le nombre de lignes du damier support de la fourmi de Langton
	 */
	int getNombreLignes();

	/**
	 * @return le nombre de colonnes du damier support de la fourmi de Langton
	 */
	int getNombreColonnes();

	/**
	 * @return la position de la fourmi sur le damier
	 */
	Couple<Integer> getPositionFourmi();

	/**
	 * @return le tableau de booleens correspondant à l'état du damier
	 */
	boolean[][] getDamier();

	/**
	 * Méthode de déplacement de la fourmi de Langton
	 */
	void deplacerFourmi();

	/**
	 * Méthode changeant la valeur d'une case du damier
	 * 
	 * @param abscisse
	 * @param ordonnee
	 */
	void changeCellValue(int abscisse, int ordonnee);

}
