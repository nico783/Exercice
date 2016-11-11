package main.java.domainobject;

/**
 * @author nicolasbenizri
 *
 *         Enumeration des différentes directions possibles.
 *
 */
public enum Direction {

	HAUTE(0), DROITE(1), BASSE(2), GAUCHE(3);

	private int code;

	private Direction(int code) {
		this.code = code;
	}

	/**
	 * Getter du code associé à une direction.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Setter du code associé à une direction.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return true si direction haute.
	 */
	public boolean isHaute() {
		return this == HAUTE;
	}

	/**
	 * @return true si direction droite.
	 */
	public boolean isDroite() {
		return this == DROITE;
	}

	/**
	 * @return true si direction basse.
	 */
	public boolean isBasse() {
		return this == BASSE;
	}

	/**
	 * @return true si direction gauche.
	 */
	public boolean isGauche() {
		return this == GAUCHE;
	}

}
