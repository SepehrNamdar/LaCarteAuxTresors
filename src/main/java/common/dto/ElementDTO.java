package common.dto;

import java.util.Objects;

public class ElementDTO {
    private String type;
    private String name;
    private int axeHorizontal;
    private int axeVertical;
    private int nbTresor;
    private String orientation;
    private String mouvements;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public int getAxeHorizontal() {
        return axeHorizontal;
    }

    public void setAxeHorizontal(int axeHorizontal) {
        this.axeHorizontal = axeHorizontal;
    }

    public int getAxeVertical() {
        return axeVertical;
    }

    public void setAxeVertical(int axeVertical) {
        this.axeVertical = axeVertical;
    }

    public int getNbTresor() {
        return nbTresor;
    }

    public void setNbTresor(int nbTresor) {
        this.nbTresor = nbTresor;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getMouvements() {
        return mouvements;
    }

    public void setMouvements(String mouvements) {
        this.mouvements = mouvements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementDTO that = (ElementDTO) o;
        return axeHorizontal == that.axeHorizontal &&
                axeVertical == that.axeVertical &&
                nbTresor == that.nbTresor &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(orientation, that.orientation) &&
                Objects.equals(mouvements, that.mouvements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, axeHorizontal, axeVertical, nbTresor, orientation, mouvements);
    }
}
