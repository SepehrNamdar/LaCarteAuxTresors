package common.mapper;

import model.element.Element;

import static model.carte.TypeAxe.MONTAGNE;

public class MontagneMapper extends ElementMapper {
    @Override
    public void mapThis(Element elt) {
        super.setType(MONTAGNE.getName());
        super.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
        super.setAxeVertical(elt.getAxe().getAxeVertical());
    }
}
