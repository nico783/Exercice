package main.java.observer;

/**
 * @author nicolasbenizri
 * 
 *         Interface Observateur dans l'implémentation du design pattern
 *         Observateur.
 *
 */
public interface ObservateurCelluleValue {

	/**
	 * Méthode appelé par les ObservateurCelluleValue pour s'actualiser. Cette
	 * méthode est appelé dans la méthode notifierObservateurCelluleValue() du
	 * modele (Observable).
	 */
	void actualiserCellule();

}
