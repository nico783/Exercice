package main.java.observer;

/**
 * @author nicolasbenizri
 * 
 *         Interface Observable dans l'implémentation du design pattern
 *         Observateur.
 *
 */
public interface Observable {

	/**
	 * Méthode qui appele la méthode "actualiserFourmi" de chacun des
	 * observateurs de l'objet.
	 */
	void notifierObservateurFourmis();

	/**
	 * Méthode permettant d'enregistrer un ObservateurFourmi.
	 * 
	 * @param o
	 */
	void enregistrerObservateurFourmi(ObservateurFourmi o);

	/**
	 * Méthode permettant de supprimer un ObservateurFourmi.
	 * 
	 * @param o
	 */
	void supprimerObservateurFourmi(ObservateurFourmi o);

	/**
	 * Méthode qui appele la méthode "actualiserCellule" de chacun des
	 * observateurs de l'objet .
	 */
	void notifierObservateurCelluleValue();

	/**
	 * Méthode permettant d'enregistrer un ObservateurCelluleValue.
	 */
	void enregistrerObservateurCelluleValue(ObservateurCelluleValue o);

	/**
	 * Méthode permettant de supprimer un ObservateurCelluleValue.
	 */
	void supprimerObservateurCelluleValue(ObservateurCelluleValue o);

}
