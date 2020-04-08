package exposition;

import application.DimensionDTO;
import application.ElementDTO;

import java.util.List;

public interface CarteAuxtTresors {

    void play(DimensionDTO dimensions, List<ElementDTO> elements);

    String[][] getCarte();

    List<ElementDTO> getElements();
}
