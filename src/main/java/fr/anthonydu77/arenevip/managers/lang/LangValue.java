package fr.anthonydu77.arenevip.managers.lang;

/**
 * Created by Anthonydu77 03/06/2021 inside the package - fr.anthonydu77.rankup.managers.lang
 */

public enum LangValue {
    PLAYER("player"),
    KILLER("killer"),
    AMOUNT("amount"),
    PRICE("price");

    private final String name;

    LangValue(String name) {
        this.name = name;
    }

    public String toName() {
        return "{" + name + "}";
    }
}
