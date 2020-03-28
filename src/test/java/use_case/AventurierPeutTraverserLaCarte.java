package use_case;

import model.aventurier.Aventurier;
import model.carte.Carte;
import model.carte.Dimensions;
import model.carte.Hauteur;
import model.carte.Largeur;
import org.junit.jupiter.api.Test;

public class AventurierPeutTraverserLaCarte {

    @Test
    void pleine() {
        Carte carte = new Carte(new Dimensions(new Largeur(3), new Hauteur(4)));
        Aventurier laura = new Aventurier("Laura");
    }
}