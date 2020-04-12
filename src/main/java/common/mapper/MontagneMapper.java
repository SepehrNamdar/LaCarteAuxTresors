package common.mapper;

import common.dto.ElementDTO;
import model.carte.Axe;
import model.element.Element;
import model.element.Montagne;

import static model.carte.TypeAxe.MONTAGNE;

public class MontagneMapper extends ElementMapper {

    @Override
    public ElementDTO mapThis(Element elt) {
        ElementDTO eltDto = new ElementDTO();
        eltDto.setType(MONTAGNE.getName());
        eltDto.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
        eltDto.setAxeVertical(elt.getAxe().getAxeVertical());
        return eltDto;
    }

    @Override
    protected Element mapThis(ElementDTO eltDto) {
        Axe axe = new Axe(eltDto.getAxeHorizontal(), eltDto.getAxeVertical());
        return new Montagne(axe);
    }
}
