package main.java.domainobject;

/**
 * @author nicolasbenizri
 * 
 *         Représente la fourmi sur le damier. La fourmi est définie par sa
 *         position et sa direction.
 *
 */
public class Fourmi {

	private Couple<Integer> position;

	private Direction direction;

	/**
	 * Constructeur. La direction est par défaut Haute.
	 */
	public Fourmi() {
		super();
		this.direction = Direction.HAUTE;
	}

	/**
	 * @return position de la fourmi
	 */
	public Couple<Integer> getPosition() {
		return position;
	}

	/**
	 * @param position
	 */
	public void setPosition(Couple<Integer> position) {
		this.position = position;
	}

	/**
	 * @return direction de la fourmi
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Donne une direction à la fourmi en fonction du booleen passé en
	 * paramètre.
	 * 
	 * Si le paramètre est false, la fourmi tourne à droite par rapport à sa
	 * direction actuelle. Si le paramètre est true, la fourmi tourne à gauche
	 * par rapport à sa direction actuelle.
	 * 
	 * Exemple: pour une fourmi avec une direction initiale HAUTE,
	 * setDirection(true) donne la nouvelle direction GAUCHE à la fourmi
	 * 
	 * @param arg
	 */
	public void setDirection(boolean arg) {
		if (arg) {
			setDirection(getCodeTournerGauche());
		} else {
			setDirection(getCodeTournerDroite());
		}
	}

	private void setDirection(int code) {
		if (code == 0) {
			direction = Direction.HAUTE;
		} else if (code == 1) {
			direction = Direction.DROITE;
		} else if (code == 2) {
			direction = Direction.BASSE;
		} else {
			direction = Direction.GAUCHE;
		}
	}

	private int getCodeTournerGauche() {
		return FourmiUtils.modulo4(this.direction.getCode() - 1);
	}

	private int getCodeTournerDroite() {
		return FourmiUtils.modulo4(this.direction.getCode() + 1);
	}

}
