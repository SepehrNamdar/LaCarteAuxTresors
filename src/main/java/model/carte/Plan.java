package model.carte;

public class Plan {
    private TypeCase[][] plan;

    public Plan(int nbCasesLargeur, int nbCasesHauteur) {
        plan = new TypeCase[nbCasesLargeur][nbCasesHauteur];
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < nbCasesLargeur; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < nbCasesHauteur; indexAxeVerticale++) {
                plan[indexAxeHorizontale][indexAxeVerticale] = TypeCase.PLAINE;
            }
        }
    }

    public TypeCase getAxe(Axe axe) {
        return plan[axe.getAxeHorizontale().getNumCase()][axe.getAxeVerticale().getNumCase()];
    }
}
