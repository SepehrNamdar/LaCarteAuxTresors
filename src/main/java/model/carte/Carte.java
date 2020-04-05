package model.carte;

import model.element.Aventurier;
import model.element.Element;
import model.element.Tresor;

import java.util.List;
import java.util.stream.Collectors;

import static model.carte.TypeAxe.*;

public class Carte {
    private Dimensions dimensions;
    private List<Element> elements;
    private TypeAxe[][] plan;

    public Carte(Dimensions dimensions, List<Element> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        initPlan();
        placer(this.elements);
    }

    private void initPlan() {
        int carteLargeur = dimensions.getLargeur();
        int carteHauteur = dimensions.getHauteur();
        plan = new TypeAxe[carteLargeur][carteHauteur];
        for (int indexAxeVertical = 0; indexAxeVertical < carteHauteur; indexAxeVertical++) {
            for (int indexAxeHorizontal = 0; indexAxeHorizontal < carteLargeur; indexAxeHorizontal++) {
                plan[indexAxeHorizontal][indexAxeVertical] = PLAINE;
            }
        }
    }

    private void placer(List<Element> elements) {
        elements.forEach(element ->
        {
            checkElementAxe(element.getAxe());
            int axeHorizontal = element.getAxe().getAxeHorizontal();
            int axeVertical = element.getAxe().getAxeVertical();
            TypeAxe occupyingAxe = plan[axeHorizontal][axeVertical];
            TypeAxe eltToPlaceType = element.getType();
            if (occupyingAxe == PLAINE) {
                plan[axeHorizontal][axeVertical] = eltToPlaceType;
            } else {
                throw new CanNotPlaceElementInMap(eltToPlaceType, element.getAxe(), eltToPlaceType);
            }
        });
    }

    private void checkElementAxe(Axe axe) {
        if (isOutOfCarte(axe)) {
            throw new CanNotPlaceElementInMap(axe);
        }
    }

    public TypeAxe getAxe(int axeHorizontal, int axeVertical) {
        return plan[axeHorizontal][axeVertical];
    }

    public int getLargeur() {
        return dimensions.getLargeur();
    }

    public int getHauteur() {
        return dimensions.getHauteur();
    }

    public void avancer(Aventurier aventurier) {
        Axe initialAxeAventurier = aventurier.getAxe();
        aventurier.move();
        if (isMoved(aventurier)) {
            updatePlan(aventurier, initialAxeAventurier);
        } else {
            aventurier.setAxe(initialAxeAventurier);
        }
    }

    private void updatePlan(Aventurier aventurier, Axe initialAventurierAxe) {
        if (!getAxe(initialAventurierAxe.getAxeHorizontal(), initialAventurierAxe.getAxeVertical()).equals(TRESOR)) {
            plan[initialAventurierAxe.getAxeHorizontal()][initialAventurierAxe.getAxeVertical()] = PLAINE;
        }
        Axe aventurierAxe = aventurier.getAxe();
        if (!getAxe(aventurierAxe.getAxeHorizontal(), aventurierAxe.getAxeVertical()).equals(TRESOR)) {
            plan[aventurierAxe.getAxeHorizontal()][aventurierAxe.getAxeVertical()] = aventurier.getType();
        } else {
            List<Element> e = getElement(aventurierAxe);
            for (Element elt : e) {
                if (elt instanceof Tresor) {
                    Tresor t = (Tresor) elt;
                    t.reduceNbTresor();
                    if (t.getNbTresor() > 0) {
                        aventurier.increaseNbtresor();
                    }
                }
            }
        }
    }

    private List<Element> getElement(Axe axe) {
        return elements.stream().filter(elt -> elt.getAxe().equals(axe)).collect(Collectors.toList());
    }

    private boolean isMoved(Aventurier aventurier) {
        return !isOutOfCarte(aventurier.getAxe()) && !isObstacle(aventurier.getAxe());
    }

    private boolean isOutOfCarte(Axe axe) {
        return axe.getAxeVertical() >= dimensions.getHauteur() ||
                axe.getAxeHorizontal() >= dimensions.getLargeur() ||
                axe.getAxeHorizontal() < 0 || axe.getAxeVertical() < 0;
    }

    private boolean isObstacle(Axe axe) {
        return plan[axe.getAxeHorizontal()][axe.getAxeVertical()] == MONTAGNE;
    }

    public void turnLeft(Aventurier aventurier) {
        aventurier.turnLeft();
    }

    public void turnRight(Aventurier laura) {
        laura.turnRight();
    }

    public List<Element> getElements() {
        return elements;
    }
}
