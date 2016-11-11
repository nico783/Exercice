package main.java.domainobject;

/**
 * @author nicolasbenizri
 * 
 *         Classe utilitaire contenant des méthodes statiques utilisées dans le
 *         modèle de la fourmi de Langton
 *
 */
public class FourmiUtils {
	/**
	 * @param entier
	 * @return l'entier passé en paramètre modulo 4
	 */
	public static int modulo4(int entier) {
		if (entier >= 0) {
			return entier % 4;
		} else {
			return 3 - ((-entier - 1) % 4);
		}
	}

}
