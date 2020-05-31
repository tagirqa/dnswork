package ru.site;

public class Product {
    private int price;
    private String name;
    private int guaranteeYear;
    private int priceWithGuarantee;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPriceWithGuarantee() {
        return priceWithGuarantee;
    }

    public void setPriceWithGuarantee(int priceWithGuarantee) {
        this.priceWithGuarantee = priceWithGuarantee;
    }

    public Product(int price, String name, int guaranteeYear) {
        this.price = price;
        this.name = name;
        this.guaranteeYear = guaranteeYear;
        count = 1;

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
