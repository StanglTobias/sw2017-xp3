package at.sw2017xp3.regionalo.model.enums;

public enum Transfer{
    DELIVERY("Zustellung", 0),
    YARDSALE("Selbstabholung", 1),
    SELFHARVEST("Selbst ernten", 2);

    private String stringValue;
    private int intValue;

    Transfer(String stringValue, int i) {
        this.stringValue = stringValue;
        this.intValue = i;
    }

    public int GetInt()
    {
        return  intValue;
    }

    @Override
    public String toString(){
        return stringValue;
    }
}