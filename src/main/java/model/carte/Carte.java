package model.carte;

import model.aventurier.Aventurier;

import java.util.List;

public class Carte {
    private Dimensions dimensions;
    private TypeAxe[][] plan;

    public Carte(Dimensions dimensions, List<Aventurier> aventuriers, List<Obstacle> obstacles) {
        this.dimensions = dimensions;
        int nbCasesLargeur = dimensions.getLargeur().getNbCases();
        int nbCasesHauteur = dimensions.getHauteur().getNbCases();
        initPlan(nbCasesLargeur, nbCasesHauteur);
        placerAventuriers(aventuriers);
        placerObstacles(obstacles);
    }

    private void initPlan(int nbCasesLargeur, int nbCasesHauteur) {
        plan = new TypeAxe[nbCasesLargeur][nbCasesHauteur];
        for (int indexAxeHorizontale = 0; indexAxeHorizontale < nbCasesLargeur; indexAxeHorizontale++) {
            for (int indexAxeVerticale = 0; indexAxeVerticale < nbCasesHauteur; indexAxeVerticale++) {
                plan[indexAxeHorizontale][indexAxeVerticale] = TypeAxe.PLAINE;
            }
        }
    }

    private void placerObstacles(List<Obstacle> obstacles) {
        obstacles.forEach(obstacle ->
        {
            int positionDepartVerticale = obstacle.getAxe().getAxeVerticale().getNumCase();
            int positionDepartHorizontale = obstacle.getAxe().getAxeHorizontale().getNumCase();
            plan[positionDepartHorizontale][positionDepartVerticale] = TypeAxe.OBSTACLE;
        });
    }

    private void placerAventuriers(List<Aventurier> aventuriers) {
        aventuriers.forEach(aventurier ->
        {
            int positionDepartVerticale = aventurier.getPositionDepart().getAxeVerticale().getNumCase();
            int positionDepartHorizontale = aventurier.getPositionDepart().getAxeHorizontale().getNumCase();
            plan[positionDepartHorizontale][positionDepartVerticale] = TypeAxe.AVENTURIER;
        });
    }

    public Largeur getLargeur() {
        return dimensions.getLargeur();
    }

    public Hauteur getHauteur() {
        return dimensions.getHauteur();
    }

    public TypeAxe getAxe(int axeHorizontale, int axeVerticale) {
        return plan[axeHorizontale][axeVerticale];
    }
}
