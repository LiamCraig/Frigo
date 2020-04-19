package com.xstudioo.myfrigo;

public class Products {

    private String name;
    private String expiry_dte;
    private String purchase_dte;

    public Products(String name, String expiry_dte, String purchase_dte) {
        this.name = name;
        this.expiry_dte = expiry_dte;
        this.purchase_dte = purchase_dte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiry_dte() {
        return expiry_dte;
    }

    public void setExpiry_dte(String expiry_dte) {
        this.expiry_dte = expiry_dte;
    }

    public String getPurchase_dte() {
        return purchase_dte;
    }

    public void setPurchase_dte(String purchase_dte) {
        this.purchase_dte = purchase_dte;
    }
}
