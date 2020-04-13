package use_case;

import model.carte.Axe;
import model.element.Aventurier;
import org.junit.jupiter.api.Test;

import static model.element.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AventurierCanChangeDirection {

    @Test
    void toLeft() {
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, NORD, "GGGG");

        laura.turnLeft();
        assertThat(laura.getCurrentOrientation()).isEqualTo(OUEST);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        laura.turnLeft();
        assertThat(laura.getCurrentOrientation()).isEqualTo(SUD);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        laura.turnLeft();
        assertThat(laura.getCurrentOrientation()).isEqualTo(EST);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        laura.turnLeft();
        assertThat(laura.getCurrentOrientation()).isEqualTo(NORD);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);
    }

    @Test
    void toRight() {
        Axe axeDepart = new Axe(1, 1);
        Aventurier laura = new Aventurier("Laura", axeDepart, NORD, "DDDD");

        laura.turnRight();
        assertThat(laura.getCurrentOrientation()).isEqualTo(EST);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        laura.turnRight();
        assertThat(laura.getCurrentOrientation()).isEqualTo(SUD);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        laura.turnRight();
        assertThat(laura.getCurrentOrientation()).isEqualTo(OUEST);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);

        laura.turnRight();
        assertThat(laura.getCurrentOrientation()).isEqualTo(NORD);
        assertThat(laura.getAxe()).isEqualTo(axeDepart);
    }
}
