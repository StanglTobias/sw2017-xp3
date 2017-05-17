package at.sw2017xp3.regionalo.model.enums;

public enum Seller{
    PRIVATE("Privat", 0),
    COMPANY("Firma", 1);

    private String stringValue;
    private int intValue;

    Seller(String stringValue, int i) {
        this.stringValue = stringValue;
        this.intValue = i;
    }

    @Override
    public String toString(){
        return stringValue;
    }
}
