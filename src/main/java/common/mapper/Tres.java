package common.mapper;

import model.element.Aventurier;
import model.element.Element;

import static model.carte.TypeAxe.AVENTURIER;

public class Tres extends ElementMapper {

    @Override
    public void mapThis(Element elt) {
        super.setType(AVENTURIER.getName());
        super.setAxeHorizontal(elt.getAxe().getAxeHorizontal());
        super.setAxeVertical(elt.getAxe().getAxeVertical());
        super.setNbTresor(elt.getNbTresor());
        Aventurier aventurier = (Aventurier) elt;
        super.setName(aventurier.getName());
        super.setOrientation(aventurier.getCurrentOrientation().name());
    }
}
