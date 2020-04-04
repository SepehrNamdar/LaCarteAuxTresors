package common;

import model.carte.Carte;
import model.carte.TypeAxe;

public class TestUtils {
    public static final int LARGEUR_CARTE = 3;
    public static final int HAUTEUR_CARTE = 4;

    public static void tracer(Carte carte) {
        for (int indexAxeVertical = 0; indexAxeVertical < HAUTEUR_CARTE; indexAxeVertical++) {
            for (int indexAxeHorizontal = 0; indexAxeHorizontal < LARGEUR_CARTE; indexAxeHorizontal++) {
                TypeAxe typeAxe = carte.getAxe(indexAxeHorizontal, indexAxeVertical);
                System.out.print(typeAxe + " ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }
}
