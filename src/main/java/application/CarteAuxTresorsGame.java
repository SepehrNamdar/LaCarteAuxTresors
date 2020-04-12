package application;

import common.dto.DimensionDTO;
import common.dto.ElementDTO;
import common.mapper.ElementMapper;
import exposition.CarteAuxtTresors;
import model.carte.Axe;
import model.carte.Carte;
import model.carte.Dimensions;
import model.element.*;

import java.util.*;

import static model.element.Orientation.*;

public class CarteAuxTresorsGame implements CarteAuxtTresors {

    private Carte carte;
    private final Map<Aventurier, String> aventuriersMovements = new HashMap<>();

    @Override
    public void play(final DimensionDTO dimensionDTO, final List<ElementDTO> elementsDto) {
        List<Element> elements = new ArrayList<>();
        for (ElementDTO elt : elementsDto) {
            String type = elt.getType();
            int axeHorizontal = elt.getAxeHorizontal();
            int axeVertical = elt.getAxeVertical();
            if ("M".equals(type)) {
                elements.add(new Montagne(new Axe(axeHorizontal, axeVertical)));
            } else if ("T".equals(type)) {
                elements.add(new Tresor(new Axe(axeHorizontal, axeVertical), elt.getNbTresor()));
            } else if ("A".equals(type)) {
                Orientation aventurierOrientation = SUD;
                String orientationDto = elt.getOrientation();
                if ("S".equals(orientationDto)) {
                    aventurierOrientation = SUD;
                } else if ("N".equals(orientationDto)) {
                    aventurierOrientation = NORD;
                } else if ("O".equals(orientationDto)) {
                    aventurierOrientation = OUEST;
                } else if ("E".equals(orientationDto)) {
                    aventurierOrientation = EST;
                }
                Axe positionDepart = new Axe(axeHorizontal, axeVertical);
                Aventurier aventurier =
                        new Aventurier(elt.getName(), positionDepart, aventurierOrientation);
                elements.add(aventurier);
                aventuriersMovements.put(aventurier, elt.getMouvements());
            }
        }

        Dimensions dimensions = new Dimensions(dimensionDTO.getLargeur(), dimensionDTO.getHauteur());
        carte = new Carte(dimensions, elements);

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
    public List<ElementDTO> getElements() {
        List<ElementDTO> elts = new ArrayList<>();
        carte.getElements().forEach(elt -> elts.add(ElementMapper.map(elt)));
        return elts;
    }

}
