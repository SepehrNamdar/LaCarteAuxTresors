package common.dto;

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
}
