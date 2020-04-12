package common.mapper;

import common.dto.ElementDTO;
import model.carte.TypeAxe;
import model.element.*;

import java.util.HashMap;
import java.util.Map;

import static model.carte.TypeAxe.*;

public abstract class ElementMapper {

    private static final Map<Element, String> sequencesMovement = new HashMap<>();

    public static ElementDTO map(Element elt) {
        ElementDTO eltDTO = new ElementDTO();
        TypeAxe type = elt.getType();
        if (MONTAGNE.equals(type)) {
            ElementMapper montagneMapper = new MontagneMapper();
            eltDTO = montagneMapper.mapElementToElementDto(elt);
        } else if (TRESOR.equals(type)) {
            ElementMapper tresorMapper = new TresorMapper();
            eltDTO = tresorMapper.mapElementToElementDto(elt);
        } else if (AVENTURIER.equals(type)) {
            ElementMapper aventurierMapper = new AventurierMapper();
            eltDTO = aventurierMapper.mapElementToElementDto(elt);
        }
        return eltDTO;
    }

    public static Element map(ElementDTO eltDto) {
        Element element = null;
        String type = eltDto.getType();
        if (MONTAGNE.getName().equals(type)) {
            ElementMapper montagneMapper = new MontagneMapper();
            element = montagneMapper.mapElementDtoToElement(eltDto);
        } else if (TRESOR.getName().equals(type)) {
            ElementMapper tresorMapper = new TresorMapper();
            element = tresorMapper.mapElementDtoToElement(eltDto);
        } else if (AVENTURIER.getName().equals(type)) {
            ElementMapper aventurierMapper = new AventurierMapper();
            element = aventurierMapper.mapElementDtoToElement(eltDto);
            sequencesMovement.put(element, eltDto.getMouvements());
        }
        return element;
    }

    protected abstract ElementDTO mapElementToElementDto(Element elt);
    protected abstract Element mapElementDtoToElement(ElementDTO eltDto);

    public static Map<Element, String> getSequencesMovement() {
        return sequencesMovement;
    }
}
