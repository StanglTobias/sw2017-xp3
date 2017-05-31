package at.sw2017xp3.regionalo.model.enums;

public enum ProductSorting {
    ALPHABETICAL("Alphabetisch", 0),
    PRICE("Preis", 1),
    POPULARITY("Beliebtheit", 2);

    private String stringValue;
    private int intValue;

    ProductSorting(String stringValue, int i) {
        this.stringValue = stringValue;
        this.intValue = i;
    }

    public int GetInt()
    {
        return  intValue;
    }

    public static ProductSorting fromInt(int i){
        for (ProductSorting category : ProductSorting.values()){
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

