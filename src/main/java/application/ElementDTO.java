package application;

import client.AventurierDTO;
import client.ElementAbstract;
import client.MontagneDTO;
import client.TresorDTO;

import java.util.Objects;

import static client.FileHelper.*;

public class ElementDTO {
    private String type;
    private String nom;
    private int axeHorizontal;
    private int axeVertical;
    private int nbTresor;
    private String orientation;
    private String mouvements;

    public static StringBuilder createElementDTO(ElementDTO elt) {
        String eltType = elt.getType();
        StringBuilder result = new StringBuilder();
        switch (eltType) {
            case MONTAGNE:
                ElementAbstract montagneDTO = new MontagneDTO();
                result.append(montagneDTO.getLine(elt));
                break;
            case TRESOR:
                ElementAbstract tresorDTO = new TresorDTO();
                result.append(tresorDTO.getLine(elt));
                break;
            case AVENTURIER:
                ElementAbstract aventurierDTO = new AventurierDTO();
                result.append(aventurierDTO.getLine(elt));
                break;
        }
        return result;
    }

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
                nbTresor == that.nbTresor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(axeHorizontal, axeVertical, nbTresor);
    }
}
