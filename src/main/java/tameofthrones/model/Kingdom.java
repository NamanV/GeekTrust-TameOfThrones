package tameofthrones.model;

public enum Kingdom {
    SPACE("GORILLA"), AIR("OWL"), ICE("MAMMOTH"),
    WATER("OCTOPUS"), FIRE("DRAGON"), LAND("PANDA"),NONE("NOT_VALID");

    private String emblem;
    Kingdom(String emblem){
        this.emblem = emblem;
    }

    public int getCipherKey(){
        return this.emblem.length();
    }

    public String getEmblem(){
        return emblem;
    }
}
