import application.CarteAuxTresorsGame;
import application.DimensionDTO;
import application.ElementRequest;
import application.ElementResponse;
import exposition.CarteAuxtTresors;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertThat(tracer(carteAuxtTresors.getElements())).isEqualTo(
                "C - 3 - 4\n" +
                "M - 1 - 0\n" +
                "M - 2 - 1\n" +
                "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}\n" +
                "T - 0 - 3 - 0\n" +
                "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}\n" +
                "T - 1 - 3 - 2\n" +
                "# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}\n" +
                "A - Lara - 0 - 3 - SUD - 3\n");
    }

    public String tracer(List<ElementResponse> elementsDto) {
        StringBuilder result = new StringBuilder();
        result.append("C" + " - " + LARGEUR + " - " + HAUTEUR + "\n");
        for (ElementResponse eltDto : elementsDto) {
            String type = eltDto.getType();
            int axeHorizontal = eltDto.getAxeHorizontal();
            int axeVertical = eltDto.getAxeVertical();
            if ("M".equals(type)) {
                result.append("M" + " - ").append(axeHorizontal).append(" - ").append(axeVertical).append("\n");
            } else {
                int nbTresor = eltDto.getNbTresor();
                if ("T".equals(type)) {
                    result.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}").append("\n");
                    result.append("T" + " - ").append(axeHorizontal).append(" - ").append(axeVertical).append(" - ").append(nbTresor).append("\n");
                } else if ("A".equals(type)) {
                    result.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}").append("\n");
                    result.append("A" + " - ").append(eltDto.getNom()).append(" - ").append(axeHorizontal).append(" - ").append(axeVertical).append(" - ").append(eltDto.getOrientation()).append(" - ").append(nbTresor).append("\n");
                }
            }
        }
        return result.toString();
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

    private List<ElementRequest> getElements() {
        ArrayList<ElementRequest> elementRequests = new ArrayList<>();
        elementRequests.add(getMontagne(1, 0));
        elementRequests.add(getMontagne(2, 1));
        elementRequests.add(getTresor(0, 3, 2));
        elementRequests.add(getTresor(1, 3, 3));
        elementRequests.add(getAventurier(NOM_AVENTURIER, 1, 1, "S", "AADADAGGA", 0));
        return elementRequests;
    }

    private ElementRequest getAventurier(
            String name, int axeHorizontal, int axeVertical, String orientation, String mouvements, int nbTresor) {
        ElementRequest aventurier = new ElementRequest();
        aventurier.setType("A");
        aventurier.setName(name);
        aventurier.setAxeHorizontal(axeHorizontal);
        aventurier.setAxeVertical(axeVertical);
        aventurier.setOrientation(orientation);
        aventurier.setMouvements(mouvements);
        aventurier.setNbTresor(nbTresor);
        return aventurier;
    }

    private ElementRequest getTresor(int axeHorizontal, int axeVertical, int nbTresor) {
        ElementRequest tresor = new ElementRequest();
        tresor.setType("T");
        tresor.setAxeHorizontal(axeHorizontal);
        tresor.setAxeVertical(axeVertical);
        tresor.setNbTresor(nbTresor);
        return tresor;
    }

    private ElementRequest getMontagne(int axeHorzontal, int axeVertical) {
        ElementRequest montagne = new ElementRequest();
        montagne.setType("M");
        montagne.setAxeHorizontal(axeHorzontal);
        montagne.setAxeVertical(axeVertical);
        return montagne;
    }
}
