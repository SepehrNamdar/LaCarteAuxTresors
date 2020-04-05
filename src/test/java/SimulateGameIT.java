import application.CarteAuxTresorsGame;
import application.DimensionDTO;
import application.ElementDTO;
import common.TestUtils;
import exposition.CarteAuxtTresors;
import model.carte.Axe;
import model.carte.Carte;
import model.carte.TypeAxe;
import model.element.Aventurier;
import model.element.Montagne;
import model.element.Orientation;
import model.element.Tresor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.element.Orientation.*;
import static model.element.Orientation.EST;
import static org.assertj.core.api.Assertions.assertThat;

public class SimulateGameIT {

    public static final int LARGEUR = 3;
    public static final int HAUTEUR = 4;
    public static final String CASE_PLAINE = ".";
    public static final String CASE_MONTAGNE = "M";
    public static final String CASE_AVENTURIER = "A";
    public static final String CASE_TRESOR = "T";
    public static final String PARANTHESE_OUVRANTE = "(";
    public static final String PARANTHESE_FERMANTE = ")";
    public static final String NOM_AVENTURIER = "Lara";

    @Test
    void Scenario_1() {
        CarteAuxtTresors carteAuxtTresors = new CarteAuxTresorsGame();

        carteAuxtTresors.play(getDimentions(), getElements());

        assertThat(carteAuxtTresors.getCarte()).isEqualTo(getExpectedCarte());
        tracer(carteAuxtTresors.getElements());
    }


    public void tracer(List<ElementDTO> elementsDto) {
        System.out.println("C" + " - " + LARGEUR + " - " + HAUTEUR);
        for (ElementDTO eltDto : elementsDto) {
            String type = eltDto.getType();
            int axeHorizontal = eltDto.getAxeHorizontal();
            int axeVertical = eltDto.getAxeVertical();
            if ("M".equals(type)) {
                System.out.println("M" + " - " + axeHorizontal + " - " + axeVertical);
            } else {
                int nbTresor = eltDto.getNbTresor();
                if ("T".equals(type)) {
                    System.out.println("T" + " - " + axeHorizontal + " - " + axeVertical + " - " + nbTresor);
                } else if ("A".equals(type)) {
                    System.out.println("A" + " - " + eltDto.getNom() + " - " + axeHorizontal + " - " + axeVertical + " - " + eltDto.getOrientation() + " - " + nbTresor);
                }
            }
        }
    }

    private String[][] getExpectedCarte() {
        String[][] expectedCarte = new String[LARGEUR][HAUTEUR];
        expectedCarte[0][0] = CASE_PLAINE;
        expectedCarte[0][1] = CASE_PLAINE;
        expectedCarte[0][2] = CASE_PLAINE;
        expectedCarte[0][3] = CASE_AVENTURIER + PARANTHESE_OUVRANTE + NOM_AVENTURIER + PARANTHESE_FERMANTE;
        expectedCarte[1][0] = CASE_MONTAGNE;
        expectedCarte[1][1] = CASE_PLAINE;
        expectedCarte[1][2] = CASE_PLAINE;
        expectedCarte[1][3] = CASE_TRESOR + PARANTHESE_OUVRANTE + 2 + PARANTHESE_FERMANTE;
        expectedCarte[2][0] = CASE_PLAINE;
        expectedCarte[2][1] = CASE_MONTAGNE;
        expectedCarte[2][2] = CASE_PLAINE;
        expectedCarte[2][3] = CASE_PLAINE;
        return expectedCarte;
    }

    private DimensionDTO getDimentions() {
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
        aventurier.setType("A");
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
        tresor.setType("T");
        tresor.setAxeHorizontal(axeHorizontal);
        tresor.setAxeVertical(axeVertical);
        tresor.setNbTresor(nbTresor);
        return tresor;
    }

    private ElementDTO getMontagne(int axeHorzontal, int axeVertical) {
        ElementDTO montagne = new ElementDTO();
        montagne.setType("M");
        montagne.setAxeHorizontal(axeHorzontal);
        montagne.setAxeVertical(axeVertical);
        return montagne;
    }
}
