package ru.site;

public class Product {
    private int price;
    private String name;
    private int guaranteeYear;
    private int priceWithOutGuarantee;




    public Product(int price, int priceWithOutGuarantee, String name, int guaranteeYear) {
        this.price = price;
        this.name = name;
        this.guaranteeYear = guaranteeYear;
        this.priceWithOutGuarantee = priceWithOutGuarantee;
    }

    public int getPriceWithOutGuarantee() {
        return priceWithOutGuarantee;
    }

    public void setPriceWithOutGuarantee(int priceWithOutGuarantee) {
        this.priceWithOutGuarantee = priceWithOutGuarantee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuaranteeYear() {
        return guaranteeYear;
    }

    public void setGuaranteeYear(int guaranteeYear) {
        this.guaranteeYear = guaranteeYear;
    }
}
