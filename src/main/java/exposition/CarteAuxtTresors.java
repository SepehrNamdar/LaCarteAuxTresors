package exposition;

import common.dto.DimensionDTO;
import common.dto.ElementDTO;

import java.util.List;

public interface CarteAuxtTresors {

    void play(DimensionDTO dimensions, List<ElementDTO> elements);

    List<ElementDTO> getElements();
}
