package main.java.controleur;

/**
 * @author nicolasbenizri
 * 
 *         Interface du controleur (design pattern MVC). Le controleur permet de
 *         deplacer la fourmi de Langton et de faire cesser son évolution.
 *
 */
public interface FourmiController {

	/**
	 * Méthode permettant de démarrer l'évolution de la fourmi de Langton.
	 */
	void deplacerFourmi();

	/**
	 * Setter du booleen continuer.
	 * 
	 * @param continuer
	 */
	void setContinuer(boolean continuer);

	void changeCellValue(int abscisse, int ordonnee);

}
