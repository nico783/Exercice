package main.java;

import main.java.controleur.FourmiController;
import main.java.controleur.FourmiControllerImpl;
import main.java.modele.FourmiModel;
import main.java.modele.FourmiModelImpl;

public class Main {

	/**
	 * 
	 * 
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Ligne utile pour éviter une exeption
		// "Exception in thread "AWT-EventQueue-0" java.lang.IllegalArgumentException: Comparison method violates its general contract!"
		// lorsque le nombre de lignes et colonnes choisi depasse 53.
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

		// Scanner sc = new Scanner(System.in);
		// System.out.println("Veuillez saisir la dimension de la grille :");
		// int dimension = sc.nextInt();
		// sc.close();

		int dimension = 75;

		FourmiModel model = new FourmiModelImpl(dimension, dimension);
		FourmiController controller = new FourmiControllerImpl(model);
	}

}
