package model.carte;

public enum TypeAxe {
    AVENTURIER("A"), MONTAGNE("M"), TRESOR("T"), PLAINE(".");

    private final String name;

    TypeAxe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
