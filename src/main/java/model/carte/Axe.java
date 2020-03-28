package model.carte;

public class Axe {
    private AxeHorizontale axeHorizontale;
    private AxeVerticale axeVerticale;

    public Axe(AxeHorizontale axeHorizontale, AxeVerticale axeVerticale) {
        this.axeHorizontale = axeHorizontale;
        this.axeVerticale = axeVerticale;
    }

    public AxeHorizontale getAxeHorizontale() {
        return axeHorizontale;
    }

    public AxeVerticale getAxeVerticale() {
        return axeVerticale;
    }
}
