package application;

import common.dto.DimensionDTO;
import common.dto.ElementDTO;
import common.mapper.ElementMapper;
import exposition.CarteAuxtTresors;
import model.carte.Carte;
import model.carte.Dimensions;
import model.element.*;

import java.util.*;

import static common.mapper.ElementMapper.map;

public class CarteAuxTresorsGame implements CarteAuxtTresors {

    private Carte carte;

    @Override
    public void play(final DimensionDTO dimensionDTO, final List<ElementDTO> elementsDto) {
        List<Element> elements = new ArrayList<>();
        Dimensions dimensions = new Dimensions(dimensionDTO.getLargeur(), dimensionDTO.getHauteur());
        elementsDto.forEach(eltDto -> elements.add(map(eltDto)));
        carte = new Carte(dimensions, elements);

        ElementMapper.getSequencesMovement().forEach((element, sequencesMovement) -> {
            String[] movements = sequencesMovement.split("");
            for (String movement : movements) {
                if ("A".equals(movement)) {
                    carte.avancer(element);
                } else if ("D".equals(movement)) {
                    carte.turnRight(element);
                } else if ("G".equals(movement)) {
                    carte.turnLeft(element);
                }
            }
        });
    }

    @Override
    public List<ElementDTO> getElements() {
        List<ElementDTO> elts = new ArrayList<>();
        if (carte != null) {
            carte.getElements().forEach(elt -> elts.add(map(elt)));
        }
        return elts;
    }

}
