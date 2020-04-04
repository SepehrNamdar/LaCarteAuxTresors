import application.CarteAuxTresorsGame;
import application.DimentionDTO;
import application.ElementDTO;
import exposition.CarteAuxtTresors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SimulateGameIT {

    @Test
    void Scenario_1() {
        CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();

        carteAuxtTresors.initCarte(getDimentions(), getElements());

        Assertions.assertThat(carteAuxtTresors.getCarte()).isEqualTo(getExpectedCarte());
    }

    private String getExpectedCarte() {
        return "";
    }

    private DimentionDTO getDimentions() {
        DimentionDTO dimentionDTO = new DimentionDTO();
        dimentionDTO.setLargeur(3);
        dimentionDTO.setHauteur(4);
        return dimentionDTO;
    }

    private List<ElementDTO> getElements() {
        ArrayList<ElementDTO> elementDTOS = new ArrayList<>();
        elementDTOS.add(getMontagne(1, 0));
        elementDTOS.add(getMontagne(2, 1));
        elementDTOS.add(gettresor(0, 3, 2));
        elementDTOS.add(gettresor(1, 3, 3));
        elementDTOS.add(getAventurier("Lara", 1, 1, "S", "AADADAGGA"));
        return elementDTOS;
    }

    private ElementDTO getAventurier(String name, int axeHorizontal, int axeVertical, String orientation, String mouvements) {
        ElementDTO aventurier = new ElementDTO();
        aventurier.setName(name);
        aventurier.setAxeHorizontal(axeHorizontal);
        aventurier.setAxeVertical(axeVertical);
        aventurier.setOrientation(orientation);
        aventurier.setMouvements(mouvements);
        return aventurier;
    }

    private ElementDTO gettresor(int axeHorizontale, int axeVerticale, int nbTresor) {
        ElementDTO tresor = new ElementDTO();
        tresor.setAxeHorizontal(axeHorizontale);
        tresor.setAxeVertical(axeVerticale);
        tresor.setNbTresor(nbTresor);
        return tresor;
    }

    private ElementDTO getMontagne(int axeHorzontale, int axeVertical) {
        ElementDTO montagne = new ElementDTO();
        montagne.setAxeHorizontal(axeHorzontale);
        montagne.setAxeVertical(axeVertical);
        return montagne;
    }
}
