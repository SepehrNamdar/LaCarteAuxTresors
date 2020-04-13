import application.CarteAuxTresorsGame;
import client.ElementWriter;
import common.dto.DimensionDTO;
import common.dto.ElementDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static client.FileHelper.*;
import static common.TestUtils.HAUTEUR_CARTE;
import static common.TestUtils.LARGEUR_CARTE;
import static org.assertj.core.api.Assertions.assertThat;

public class SimulateGameIT {

    public static final String AVENTURIER_NAME = "Lara";
    private static CarteAuxTresorsGame carteAuxtTresors;

    @BeforeAll
    static void beforeAll() {
        carteAuxtTresors = new CarteAuxTresorsGame();
    }

    @Test
    void tresorScenario() {
        carteAuxtTresors.play(getDimensions(), getElements("AADADAGGA"));

        List<ElementDTO> elements = carteAuxtTresors.getElements();
        assertThat(getLines(elements)).isEqualTo(getScenarioTresorExpectedLines());
    }

    @Test
    void obstacleScenario() {
        carteAuxtTresors.play(getDimensions(), getElements("DAADAADAA"));

        List<ElementDTO> elements = carteAuxtTresors.getElements();
        assertThat(getLines(elements)).isEqualTo(getScenarioObstacleExpectedLines());
    }

    private String getScenarioTresorExpectedLines() {
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

    private String getScenarioObstacleExpectedLines() {
        return
            "C - 3 - 4\n" +
            "M - 1 - 0\n" +
            "M - 2 - 1\n" +
            "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}\n" +
            "T - 0 - 3 - 2\n" +
            "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}\n" +
            "T - 1 - 3 - 3\n" +
            "# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. de trésors ramassés}\n" +
            "A - Lara - 0 - 0 - EST - 0\n";
    }

    private String getLines(List<ElementDTO> elements) {
        StringBuilder result = new StringBuilder();
        result.append(ElementWriter.getCarteToWrite(getDimensions()));
        for (ElementDTO elt : elements) {
            result.append(ElementWriter.getElementToWrite(elt));
        }
        return result.toString();
    }

    private DimensionDTO getDimensions() {
        DimensionDTO dimensionDTO = new DimensionDTO();
        dimensionDTO.setLargeur(LARGEUR_CARTE);
        dimensionDTO.setHauteur(HAUTEUR_CARTE);
        return dimensionDTO;
    }

    private List<ElementDTO> getElements(final String movements) {
        ArrayList<ElementDTO> elementDTOS = new ArrayList<>();
        elementDTOS.add(getMontagne(1, 0));
        elementDTOS.add(getMontagne(2, 1));
        elementDTOS.add(getTresor(0, 3, 2));
        elementDTOS.add(getTresor(1, 3, 3));
        elementDTOS.add(getAventurier(AVENTURIER_NAME, 1, 1, "S", movements, 0));
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
