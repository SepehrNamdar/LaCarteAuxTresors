package common.mapper;

import common.dto.ElementDTO;
import model.carte.TypeAxe;
import model.element.Element;

import java.util.ArrayList;
import java.util.List;

import static model.carte.TypeAxe.*;

public abstract class ElementMapper extends ElementDTO {
    protected abstract void map(Element elt);

    public static List<ElementDTO> getMapped(Element elt) {
        List<ElementDTO> elts = new ArrayList<>();
        TypeAxe type = elt.getType();
        if (MONTAGNE.equals(type)) {
            ElementMapper montagneMapper = new MontagneMapper();
            montagneMapper.map(elt);
            elts.add(montagneMapper);
        } else if (TRESOR.equals(type)) {
            ElementMapper tresorMapper = new TresorMapper();
            tresorMapper.map(elt);
            elts.add(tresorMapper);
        } else if (AVENTURIER.equals(type)) {
            ElementMapper aventurierMapper = new Tres();
            aventurierMapper.map(elt);
            elts.add(aventurierMapper);
        }
        return elts;
    }
}
