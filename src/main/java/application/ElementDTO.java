package application;

import java.util.Objects;

public class ElementDTO {
    private String type;
    private String nom;
    private int axeHorizontale;
    private int axeVerticale;
    private int nbTresor;
    private String orientation;
    private String mouvements;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public int getAxeHorizontal() {
        return axeHorizontale;
    }

    public void setAxeHorizontal(int axeHorizontale) {
        this.axeHorizontale = axeHorizontale;
    }

    public int getAxeVertical() {
        return axeVerticale;
    }

    public void setAxeVertical(int axeVerticale) {
        this.axeVerticale = axeVerticale;
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
        return axeHorizontale == that.axeHorizontale &&
                axeVerticale == that.axeVerticale &&
                nbTresor == that.nbTresor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(axeHorizontale, axeVerticale, nbTresor);
    }
}