package model.carte;

import java.util.Objects;

public class Axe {
    private final int axeHorizontal;
    private final int axeVertical;

    public Axe(final int axeHorizontal, final int axeVertical) {
        this.axeHorizontal = axeHorizontal;
        this.axeVertical = axeVertical;
    }

    public int getAxeHorizontal() {
        return axeHorizontal;
    }

    public int getAxeVertical() {
        return axeVertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Axe axe = (Axe) o;
        return axeHorizontal == axe.axeHorizontal &&
                axeVertical == axe.axeVertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(axeHorizontal, axeVertical);
    }
}
