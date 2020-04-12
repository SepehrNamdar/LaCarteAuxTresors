import application.CarteAuxTresorsGame;
import client.ElementWriter;
import common.dto.DimensionDTO;
import common.dto.ElementDTO;
import exposition.CarteAuxtTresors;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static client.FileHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SimulateGameIT {

    public static final int LARGEUR = 3;
    public static final int HAUTEUR = 4;
    public static final String CASE_PLAINE = ".";
    public static final String NOM_AVENTURIER = "Lara";

    @Test
    void Scenario_1() {
        CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();

        carteAuxtTresors.play(getDimensions(), getElements());

        List<ElementDTO> elements = carteAuxtTresors.getElements();
        assertThat(getCarte(elements)).isEqualTo(getExpectedCarte());
        assertThat(tracer(elements)).isEqualTo(getExpectedLines());
    }

    private String getExpectedLines() {
        return
            "C - 3 - 4\n" +
            "M - 1 - 0\n" +
            "M - 2 - 1\n" +
            "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}\n" +
            "T - 0 - 3 - 0\n" +
            "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}\n" +
            "T - 1 - 3 - 2\n" +
            "# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. de trésors ramassés}\n" +
            "A - Lara - 0 - 3 - SUD - 3\n";
    }

    private String[][] getCarte(List<ElementDTO> elements) {
        String[][] carte = new String[LARGEUR][HAUTEUR];
        for (int indexAxeVertical = 0; indexAxeVertical < HAUTEUR; indexAxeVertical++) {
            for (int indexAxeHorizontal = 0; indexAxeHorizontal < LARGEUR; indexAxeHorizontal++) {
                carte[indexAxeHorizontal][indexAxeVertical] = ".";
            }
        }
        for (ElementDTO elt : elements) {
            int axeHorizontal = elt.getAxeHorizontal();
            int axeVertical = elt.getAxeVertical();
            String typeCase = elt.getType();
            if (MONTAGNE.equals(typeCase)) {
                carte[axeHorizontal][axeVertical] = MONTAGNE;
            } else if (TRESOR.equals(typeCase)) {
                carte[axeHorizontal][axeVertical] =
                        TRESOR + OPENING_PARANTHESE + elt.getNbTresor() + CLOSING_PARANTHESE;
            } else if (AVENTURIER.equals(typeCase)) {
                carte[axeHorizontal][axeVertical] =
                        AVENTURIER + OPENING_PARANTHESE + elt.getName() + CLOSING_PARANTHESE;
            }
        }
        return carte;
    }

    private String tracer(List<ElementDTO> elements) {
        StringBuilder result = new StringBuilder();
        result.append(ElementWriter.getCarteToWrite(getDimensions()));
        for (ElementDTO elt : elements) {
            result.append(ElementWriter.getElementToWrite(elt));
        }
        return result.toString();
    }

    private String[][] getExpectedCarte() {
        String[][] expectedCarte = new String[LARGEUR][HAUTEUR];
        expectedCarte[0][0] = CASE_PLAINE;
        expectedCarte[0][1] = CASE_PLAINE;
        expectedCarte[0][2] = CASE_PLAINE;
        expectedCarte[0][3] = AVENTURIER + OPENING_PARANTHESE + NOM_AVENTURIER + CLOSING_PARANTHESE;
        expectedCarte[1][0] = MONTAGNE;
        expectedCarte[1][1] = CASE_PLAINE;
        expectedCarte[1][2] = CASE_PLAINE;
        expectedCarte[1][3] = TRESOR + OPENING_PARANTHESE + 2 + CLOSING_PARANTHESE;
        expectedCarte[2][0] = CASE_PLAINE;
        expectedCarte[2][1] = MONTAGNE;
        expectedCarte[2][2] = CASE_PLAINE;
        expectedCarte[2][3] = CASE_PLAINE;
        return expectedCarte;
    }

    private DimensionDTO getDimensions() {
        DimensionDTO dimensionDTO = new DimensionDTO();
        dimensionDTO.setLargeur(LARGEUR);
        dimensionDTO.setHauteur(HAUTEUR);
        return dimensionDTO;
    }

    private List<ElementDTO> getElements() {
        ArrayList<ElementDTO> elementDTOS = new ArrayList<>();
        elementDTOS.add(getMontagne(1, 0));
        elementDTOS.add(getMontagne(2, 1));
        elementDTOS.add(getTresor(0, 3, 2));
        elementDTOS.add(getTresor(1, 3, 3));
        elementDTOS.add(getAventurier(NOM_AVENTURIER, 1, 1, "S", "AADADAGGA", 0));
        return elementDTOS;
    }

    private ElementDTO getAventurier(
            String name, int axeHorizontal, int axeVertical, String orientation, String mouvements, int nbTresor) {
        ElementDTO aventurier = new ElementDTO();
        aventurier.setType(AVENTURIER);
        aventurier.setName(name);
        aventurier.setAxeHorizontal(axeHorizontal);
        aventurier.setAxeVertical(axeVertical);
        aventurier.setOrientation(orientation);
        aventurier.setMouvements(mouvements);
        aventurier.setNbTresor(nbTresor);
        return aventurier;
    }

    private ElementDTO getTresor(int axeHorizontal, int axeVertical, int nbTresor) {
        ElementDTO tresor = new ElementDTO();
        tresor.setType(TRESOR);
        tresor.setAxeHorizontal(axeHorizontal);
        tresor.setAxeVertical(axeVertical);
        tresor.setNbTresor(nbTresor);
        return tresor;
    }

    private ElementDTO getMontagne(int axeHorzontal, int axeVertical) {
        ElementDTO montagne = new ElementDTO();
        montagne.setType(MONTAGNE);
        montagne.setAxeHorizontal(axeHorzontal);
        montagne.setAxeVertical(axeVertical);
        return montagne;
    }
}
