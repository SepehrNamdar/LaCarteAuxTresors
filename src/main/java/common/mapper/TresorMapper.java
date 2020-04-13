package common.mapper;

import common.dto.ElementDTO;
import model.carte.Axe;
import model.element.Element;
import model.element.Tresor;

import static model.carte.TypeAxe.TRESOR;

public class TresorMapper extends ElementMapper {
    @Override
    public ElementDTO mapElementToElementDto(Element elt) {
        ElementDTO eltDto = new ElementDTO();
        eltDto.setType(TRESOR.getName());
        eltDto.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
        eltDto.setAxeVertical(elt.getAxe().getAxeVertical());
        eltDto.setNbTresor(elt.getNbTresor());
        return eltDto;
    }

    @Override
    protected Element mapElementDtoToElement(ElementDTO eltDto) {
        Axe axe = new Axe(eltDto.getAxeHorizontal(), eltDto.getAxeVertical());
        int nbTresor = eltDto.getNbTresor();
        return new Tresor(axe, nbTresor);
    }
}
