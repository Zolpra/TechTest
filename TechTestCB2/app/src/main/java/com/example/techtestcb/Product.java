package com.example.techtestcb;

public class Product {
    //attrbts
    private String GTIN;
    private String DLC;
    private String longGTIN;

    //cnstrctrs
    public Product(String GTIN, String DLC){
        setGTIN(GTIN);
        setDLC(DLC);
        setLongGTIN(GTIN + DLC);
    }

    // sttr/gttr

    public void setGTIN(String GTIN) {
        this.GTIN = GTIN;
    }

    public void setDLC(String DLC) {
        this.DLC = DLC;
    }

    public void setLongGTIN(String longGTIN) {
        this.longGTIN = longGTIN;
    }


    public String getGTIN() {
        return GTIN;
    }

    public String getDLC() {
        return this.DLC;
    }

    public String getLongGTIN() {
        return longGTIN;
    }

    //mthds
    public String beautyDate(){
        String beautyDate = this.getDLC().substring(0,2) + "/" + this.getDLC().substring(2,4) + "/" + this.getDLC().substring(4,6);
        return beautyDate;
    }

    public String productFullInfo(){
        String fullInfo = "DLC: " + this.beautyDate() + "\nGTIN: " + this.getLongGTIN();
        return fullInfo;
    }
}
