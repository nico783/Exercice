package main.java.observer;

/**
 * @author nicolasbenizri
 * 
 *         Interface Observateur dans l'implémentation du design pattern
 *         Observateur.
 *
 */
public interface ObservateurFourmi {

	/**
	 * Méthode appelée par les observateurs (observateurFourmi) dans le but de
	 * s'actualiser. Cette méthode est en particulier utilisée par la méthode
	 * notifierUtilisateurFourmi() de l'objet (observable) observé.
	 */
	void actualiserFourmi();

}
