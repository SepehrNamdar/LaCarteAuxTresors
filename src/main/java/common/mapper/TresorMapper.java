package common.mapper;

import common.dto.ElementDTO;
import model.element.Element;

import static model.carte.TypeAxe.TRESOR;

public class TresorMapper extends ElementMapper {
    @Override
    public void mapThis(Element elt) {
        super.setType(TRESOR.getName());
        super.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
        super.setAxeVertical(elt.getAxe().getAxeVertical());
        super.setNbTresor(elt.getNbTresor());
    }
}
