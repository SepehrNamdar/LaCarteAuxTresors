package model.carte;

import model.element.Element;
import model.element.Tresor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static model.carte.TypeAxe.*;

public class Carte {
    private final Dimensions dimensions;
    private final List<Element> elements;
    private TypeAxe[][] plan;
    private static List<Element> tresors;
    private static List<Element> aventuriers;

    public Carte(Dimensions dimensions, List<Element> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        tresors = new ArrayList<>();
        aventuriers = new ArrayList<>();
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
            if (TRESOR.equals(eltToPlaceType)) {
                tresors.add(element);
            } else if (AVENTURIER.equals(eltToPlaceType)) {
                aventuriers.add(element);
            }
        });
    }

    private void checkElementAxe(Axe axe) {
        if (isOutOfCarte(axe)) {
            throw new CanNotPlaceElementInMap(axe);
        }
    }

    public static List<Element> getTresors() {
        return tresors;
    }

    public static List<Element> getAventuriers() {
        return aventuriers;
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

    public void avancer(Element element) {
        if (element.canMove()) {
            Axe initialAxeAventurier = element.getAxe();
            element.avancer();
            if (isMoved(element)) {
                updatePlan(element, initialAxeAventurier);
            } else {
                element.setAxe(initialAxeAventurier);
            }
        }
    }

    private void updatePlan(Element element, Axe initialAventurierAxe) {
        if (!getAxe(initialAventurierAxe.getAxeHorizontal(), initialAventurierAxe.getAxeVertical()).equals(TRESOR)) {
            plan[initialAventurierAxe.getAxeHorizontal()][initialAventurierAxe.getAxeVertical()] = PLAINE;
        }
        Axe aventurierAxe = element.getAxe();
        if (!getAxe(aventurierAxe.getAxeHorizontal(), aventurierAxe.getAxeVertical()).equals(TRESOR)) {
            plan[aventurierAxe.getAxeHorizontal()][aventurierAxe.getAxeVertical()] = element.getType();
        } else {
            List<Element> e = getElement(aventurierAxe);
            for (Element elt : e) {
                if (elt instanceof Tresor) {
                    Tresor t = (Tresor) elt;
                    t.reduceNbTresor();
                    if (t.getNbTresor() >= 0) {
                        element.increaseNbTresor();
                    }
                }
            }
        }
    }

    private List<Element> getElement(Axe axe) {
        return elements.stream().filter(elt -> elt.getAxe().equals(axe)).collect(Collectors.toList());
    }

    private boolean isMoved(Element aventurier) {
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

    public List<Element> getElements() {
        return elements;
    }
}
