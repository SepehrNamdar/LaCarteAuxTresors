package application;

import exposition.CarteAuxtTresors;
import model.carte.Axe;
import model.carte.Carte;
import model.carte.Dimensions;
import model.carte.TypeAxe;
import model.element.*;

import java.util.*;

import static application.ApplicationValidator.validate;
import static model.carte.TypeAxe.*;
import static model.element.Orientation.*;

public class CarteAuxTresorsGame implements CarteAuxtTresors {

    Carte carte;
    Map<Aventurier, String> aventuriersMovements = new HashMap<>();

    @Override
    public void play(final DimensionDTO dimensions, final List<ElementDTO> elementsDto) {
        validate(dimensions);
        List<Element> elements = new ArrayList<>();
        for (ElementDTO eltDto : elementsDto) {
            String type = eltDto.getType();
            int axeHorizontal = eltDto.getAxeHorizontal();
            int axeVertical = eltDto.getAxeVertical();
            if ("M".equals(type)) {
                elements.add(new Montagne(new Axe(axeHorizontal, axeVertical)));
            } else if ("T".equals(type)) {
                elements.add(new Tresor(new Axe(axeHorizontal, axeVertical), eltDto.getNbTresor()));
            } else if ("A".equals(type)) {
                Orientation aventurierOrientation = SUD;
                String orientationDto = eltDto.getOrientation();
                if ("S".equals(orientationDto)) {
                    aventurierOrientation = SUD;
                } else if ("N".equals(orientationDto)) {
                    aventurierOrientation = NORD;
                } else if ("O".equals(orientationDto)) {
                    aventurierOrientation = OUEST;
                } else if ("E".equals(orientationDto)) {
                    aventurierOrientation = EST;
                }
                Aventurier a = new Aventurier(eltDto.getNom(), new Axe(axeHorizontal, axeVertical), aventurierOrientation);
                elements.add(a);
                aventuriersMovements.put(a, eltDto.getMouvements());
            }
        }

        carte = new Carte(new Dimensions(dimensions.getLargeur(), dimensions.getHauteur()), elements);

        aventuriersMovements.forEach((aventurier, sequencesMovement) -> {
            String[] movements = sequencesMovement.split("");
            for (String movement : movements) {
                if ("A".equals(movement)) {
                    carte.avancer(aventurier);
                } else if ("D".equals(movement)) {
                    carte.turnRight(aventurier);
                } else if ("G".equals(movement)) {
                    carte.turnLeft(aventurier);
                }
            }
        });
    }

    @Override
    public String[][] getCarte() {
        List<Element> elements = this.carte.getElements();
        int hauteur = this.carte.getHauteur();
        int largeur = this.carte.getLargeur();
        String[][] carte = new String[largeur][hauteur];
        for (int indexAxeVertical = 0; indexAxeVertical < hauteur; indexAxeVertical++) {
            for (int indexAxeHorizontal = 0; indexAxeHorizontal < largeur; indexAxeHorizontal++) {
                carte[indexAxeHorizontal][indexAxeVertical] = ".";
            }
        }
        for (Element element : elements) {
            int axeHorizontal = element.getAxe().getAxeHorizontal();
            int axeVertical = element.getAxe().getAxeVertical();
            TypeAxe typeCase = element.getType();
            if (MONTAGNE.equals(typeCase)) {
                carte[axeHorizontal][axeVertical] = "M";
            } else if (TRESOR.equals(typeCase)) {
                Tresor t = (Tresor) element;
                carte[axeHorizontal][axeVertical] = "T" + "(" + t.getNbTresor() + ")";
            } else if (AVENTURIER.equals(typeCase)) {
                Aventurier a = (Aventurier) element;
                carte[axeHorizontal][axeVertical] = "A" + "(" + a.getName() + ")";
            }
        }
        return carte;
    }

    @Override
    public List<ElementDTO> getElements() {
        List<ElementDTO> elts= new ArrayList<>();
        carte.getElements().forEach(elt -> {
            TypeAxe type = elt.getType();
            if (MONTAGNE.equals(type)) {
                ElementDTO montagne = new ElementDTO();
                montagne.setType("M");
                montagne.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
                montagne.setAxeVertical(elt.getAxe().getAxeVertical());
                elts.add(montagne);
            } else if (TRESOR.equals(type)) {
                ElementDTO tresor = new ElementDTO();
                tresor.setType("T");
                tresor.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
                tresor.setAxeVertical(elt.getAxe().getAxeVertical());
                tresor.setNbTresor(elt.getNbTresor());
                elts.add(tresor);
            } else if (AVENTURIER.equals(type)) {
                ElementDTO aventurier = new ElementDTO();
                aventurier.setType("A");
                aventurier.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
                aventurier.setAxeVertical(elt.getAxe().getAxeVertical());
                aventurier.setNbTresor(elt.getNbTresor());
                Aventurier a = (Aventurier) elt;
                aventurier.setName(a.getName());
                aventurier.setOrientation(a.getCurrentOrientation().name());
                elts.add(aventurier);
            }
        });
        return elts;
    }
}
