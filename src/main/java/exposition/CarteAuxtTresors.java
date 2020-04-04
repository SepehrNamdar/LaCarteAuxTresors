package exposition;

import application.DimentionDTO;
import application.ElementDTO;

import java.util.List;

public interface CarteAuxtTresors {

    void initCarte(DimentionDTO dimenstions, List<ElementDTO> elements);

    String getCarte();
}
