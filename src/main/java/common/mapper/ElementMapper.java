package common.mapper;

import common.dto.ElementDTO;
import model.carte.TypeAxe;
import model.element.*;

import java.util.Map;

import static model.carte.TypeAxe.*;

public abstract class ElementMapper {

    public static ElementDTO map(Element elt) {
        ElementDTO eltDTO = new ElementDTO();
        TypeAxe type = elt.getType();
        if (MONTAGNE.equals(type)) {
            ElementMapper montagneMapper = new MontagneMapper();
            eltDTO = montagneMapper.mapThis(elt);
        } else if (TRESOR.equals(type)) {
            ElementMapper tresorMapper = new TresorMapper();
            eltDTO = tresorMapper.mapThis(elt);
        } else if (AVENTURIER.equals(type)) {
            ElementMapper aventurierMapper = new AventurierMapper();
            eltDTO = aventurierMapper.mapThis(elt);
        }
        return eltDTO;
    }

    public static Element map(Map<Element, String> aventuriersMovements, ElementDTO eltDto) {
        Element element = null;
        String type = eltDto.getType();
        if (MONTAGNE.getName().equals(type)) {
            ElementMapper montagneMapper = new MontagneMapper();
            element = montagneMapper.mapThis(eltDto);
        } else if (TRESOR.getName().equals(type)) {
            ElementMapper tresorMapper = new TresorMapper();
            element = tresorMapper.mapThis(eltDto);
        } else if (AVENTURIER.getName().equals(type)) {
            ElementMapper aventurierMapper = new AventurierMapper();
            element = aventurierMapper.mapThis(eltDto);
            aventuriersMovements.put(element, eltDto.getMouvements());
        }
        return element;
    }

    protected abstract ElementDTO mapThis(Element elt);
    protected abstract Element mapThis(ElementDTO eltDto);

}
