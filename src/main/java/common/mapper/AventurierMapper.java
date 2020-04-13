package common.mapper;

import common.dto.ElementDTO;
import model.carte.Axe;
import model.element.Aventurier;
import model.element.Element;
import model.element.Orientation;

import static model.carte.TypeAxe.AVENTURIER;
import static model.element.Orientation.*;
import static model.element.Orientation.EST;

public class AventurierMapper extends ElementMapper {

    @Override
    public ElementDTO mapElementToElementDto(Element elt) {
        ElementDTO eltDto = new ElementDTO();
        eltDto.setType(AVENTURIER.getName());
        eltDto.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
        eltDto.setAxeVertical(elt.getAxe().getAxeVertical());
        eltDto.setNbTresor(elt.getNbTresor());
        Aventurier aventurier = (Aventurier) elt;
        eltDto.setName(aventurier.getName());
        eltDto.setOrientation(aventurier.getCurrentOrientation().name());
        return eltDto;
    }

    @Override
    protected Element mapElementDtoToElement(ElementDTO eltDto) {
        Orientation orienttion = SUD;
        String orientationDto = eltDto.getOrientation();
        if (NORD.getName().equals(orientationDto)) {
            orienttion = NORD;
        } else if (OUEST.getName().equals(orientationDto)) {
            orienttion = OUEST;
        } else if (EST.getName().equals(orientationDto)) {
            orienttion = EST;
        }
        Axe axe = new Axe(eltDto.getAxeHorizontal(), eltDto.getAxeVertical());
        String name = eltDto.getName();
        String sequencesMouvement = eltDto.getMouvements();
        return new Aventurier(name, axe, orienttion, sequencesMouvement);
    }
}
