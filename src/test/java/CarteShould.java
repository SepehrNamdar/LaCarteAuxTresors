import model.carte.Carte;
import model.carte.Dimensions;
import model.carte.Hauteur;
import model.carte.Largeur;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarteShould {

    public static final int NB_CASES_LARGEUR_CARTE = 3;
    public static final int NB_CASES_HAUTEUR_CARTE = 1;

    @Test
    void beCreated() {
        Largeur largeur = new Largeur(NB_CASES_LARGEUR_CARTE);
        Hauteur hauteur = new Hauteur(NB_CASES_HAUTEUR_CARTE);

        Carte carte = new Carte(new Dimensions(largeur, hauteur));

        assertThat(carte.getLargeur()).isEqualTo(largeur);
        assertThat(carte.getHauteur()).isEqualTo(hauteur);
    }
}
