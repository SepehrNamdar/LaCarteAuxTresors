package model.carte;

public class Plan {
    private String[][] plan;

    public Plan(int nbCasesLargeur, int nbCasesHauteur) {
        plan = new String[nbCasesLargeur][nbCasesHauteur];
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < nbCasesLargeur; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < nbCasesHauteur; indexAxeVerticale++) {
                plan[indexAxeHorizontale][indexAxeVerticale] = ".";
            }
        }
    }

    public String getAxe(Axe axe) {
        return plan[axe.getAxeHorizontale().getNumCase()][axe.getAxeVerticale().getNumCase()];
    }
}
