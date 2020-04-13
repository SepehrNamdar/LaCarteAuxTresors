package application;

import common.dto.DimensionDTO;
import common.dto.ElementDTO;
import exposition.CarteAuxtTresors;
import model.carte.Carte;
import model.carte.Dimensions;
import model.element.*;

import java.util.*;

import static common.mapper.ElementMapper.getAventuriers;
import static common.mapper.ElementMapper.map;

public class CarteAuxTresorsGame implements CarteAuxtTresors {

    private Carte carte;

    @Override
    public void play(final DimensionDTO dimensionDTO, final List<ElementDTO> elementsDto) {
        Dimensions dimensions = new Dimensions(dimensionDTO.getLargeur(), dimensionDTO.getHauteur());
        List<Element> elements = new ArrayList<>();
        elementsDto.forEach(eltDto -> elements.add(map(eltDto)));

        carte = new Carte(dimensions, elements);

        getAventuriers().forEach(Element::move);
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
