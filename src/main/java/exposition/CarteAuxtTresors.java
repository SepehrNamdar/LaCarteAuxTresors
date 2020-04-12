package exposition;

import application.DimensionDTO;
import common.ElementRequest;
import common.ElementResponse;

import java.util.List;

public interface CarteAuxtTresors {

    void play(DimensionDTO dimensions, List<ElementRequest> elements);

    String[][] getCarte();

    List<ElementResponse> getElements();
}
