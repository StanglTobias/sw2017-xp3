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


    public static ProductSorting fromInt(int i){
        ProductSorting p = ProductSorting.ALPHABETICAL_ASC;
        for (ProductSorting category : ProductSorting.values()){
            if(category.intValue == i){
                category.intValue = i;
                p = category;
            }
        }
        return p;
    }

    @Override
    public String toString(){
        return stringValue;
    }
}

