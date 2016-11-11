package main.java.domainobject;

/**
 * @author nicolasbenizri
 * 
 *         Classe permettant de representer et manipuler des couples d'objets.
 *         Par exemple des couples d'entiers.
 *
 * @param <E>
 */
public class Couple<E> {

	private E abscisse;

	private E ordonnee;

	/**
	 * @param abscisse
	 * @param ordonnee
	 */
	public Couple(E abscisse, E ordonnee) {
		super();
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}

	/**
	 * @return le premier paramètre du couple
	 */
	public E getAbscisse() {
		return abscisse;
	}

	/**
	 * @param abscisse
	 */
	public void setAbscisse(E abscisse) {
		this.abscisse = abscisse;
	}

	/**
	 * @return le second paramètre du couple
	 */
	public E getOrdonnee() {
		return ordonnee;
	}

	/**
	 * @param ordonnee
	 */
	public void setOrdonnee(E ordonnee) {
		this.ordonnee = ordonnee;
	}

}
