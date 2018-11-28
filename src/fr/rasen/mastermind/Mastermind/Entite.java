package fr.rasen.mastermind.Mastermind;

public enum Entite {

    ORDI("ordinateur"),
    HUMAIN("humain");

    private String type = "";

    Entite(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}

