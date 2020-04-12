package common.mapper;

import common.dto.ElementDTO;
import model.element.Element;

public abstract class ElementMapper extends ElementDTO {
    public abstract void map(Element elt);
}
