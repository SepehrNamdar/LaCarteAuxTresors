package application;

import common.dto.DimensionDTO;
import common.dto.ElementDTO;
import common.mapper.ElementMapper;
import exposition.CarteAuxtTresors;
import model.carte.Carte;
import model.carte.Dimensions;
import model.element.*;

import java.util.*;

public class CarteAuxTresorsGame implements CarteAuxtTresors {

    private Carte carte;

    @Override
    public void play(final DimensionDTO dimensionDTO, final List<ElementDTO> elementsDto) {
        final Map<Element, String> aventuriersMovements = new HashMap<>();
        List<Element> elements = new ArrayList<>();
        for (ElementDTO elt : elementsDto) {
            elements.add(ElementMapper.map(aventuriersMovements, elt));
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
