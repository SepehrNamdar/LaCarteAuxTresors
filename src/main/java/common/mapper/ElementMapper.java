package common.mapper;

import common.dto.ElementDTO;
import model.carte.TypeAxe;
import model.element.Element;

import static model.carte.TypeAxe.*;

public abstract class ElementMapper extends ElementDTO {

    public static ElementDTO map(Element elt) {
        ElementDTO eltDTO = new ElementDTO();
        TypeAxe type = elt.getType();
        if (MONTAGNE.equals(type)) {
            ElementMapper montagneMapper = new MontagneMapper();
            montagneMapper.mapThis(elt);
            eltDTO = montagneMapper;
        } else if (TRESOR.equals(type)) {
            ElementMapper tresorMapper = new TresorMapper();
            tresorMapper.mapThis(elt);
            eltDTO = tresorMapper;
        } else if (AVENTURIER.equals(type)) {
            ElementMapper aventurierMapper = new Tres();
            aventurierMapper.mapThis(elt);
            eltDTO = aventurierMapper;
        }
        return eltDTO;
    }

    protected abstract void mapThis(Element elt);

}
