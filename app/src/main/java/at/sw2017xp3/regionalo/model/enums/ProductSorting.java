package at.sw2017xp3.regionalo.model.enums;

public enum ProductSorting {
    ALPHABETICAL_ASC("Alphabetisch", 0),
    ALPHATEICAL_DESC("Aplhabetisch", 1),
    PRICE_ASC("Preis", 2),
    PRICE_DESC("Preis", 3),
    POPULARITY("Beliebtheit", 4);

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

