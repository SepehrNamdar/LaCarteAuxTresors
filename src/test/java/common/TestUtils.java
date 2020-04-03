package common;

import model.carte.Carte;
import model.carte.TypeAxe;

public class TestUtils {
    public static final int LARGEUR_CARTE = 3;
    public static final int HAUTEUR_CARTE = 4;

    public static void tracer(Carte carte) {
        for (int indexAxeVerticale = 0; indexAxeVerticale < HAUTEUR_CARTE; indexAxeVerticale++) {
            for (int indexAxeHorizontale = 0; indexAxeHorizontale < LARGEUR_CARTE; indexAxeHorizontale++) {
                TypeAxe typeAxe = carte.getAxe(indexAxeHorizontale, indexAxeVerticale);
                System.out.print(typeAxe + " ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }
}
