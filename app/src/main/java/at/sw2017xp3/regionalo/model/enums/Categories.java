package at.sw2017xp3.regionalo.model.enums;

public enum Categories{
    MEAT("Fleisch", 0),
    VEGETABLE("Gemüse", 1),
    FRUIT("Obst", 2),
    MILKPRODUCTS("Milchprodukte", 3),
    CEREALS("Getreide", 4),
    OTHERS("Sonstiges", 5);

    private String stringValue;
    private int intValue;

    Categories(String stringValue, int i) {
        this.stringValue = stringValue;
        this.intValue = i;
    }

    public static Categories fromInt(int i){
        for (Categories category : Categories.values()){
            if(category.intValue == i)
                return category;
        }
    }

    @Override
    public String toString(){
        return stringValue;
    }
}

