package at.sw2017xp3.regionalo.model.enums;

public enum Categories{
    MEAT("Fleisch", 1),
    VEGETABLE("Gemüse", 2),
    FRUIT("Obst", 3),
    MILKPRODUCTS("Milchprodukte", 4),
    CEREALS("Getreide", 5),
    OTHERS("Sonstiges", 6);

    private String stringValue;
    private int intValue;

    Categories(String stringValue, int i) {
        this.stringValue = stringValue;
        this.intValue = i;
    }

    public int GetInt()
    {
        return  intValue;
    }

    public static Categories fromInt(int i){
        for (Categories category : Categories.values()){
            if(category.intValue == i)
                return category;
        }
        return  null;
    }

    @Override
    public String toString(){
        return stringValue;
    }
}

