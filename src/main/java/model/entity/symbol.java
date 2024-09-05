package main.java.model.entity;

public class symbol {
    private String nameSymbol;
    private String price;

    public String getNameSymbol() {
        return nameSymbol;
    }

    public void setNameSymbol(String nameSymbol) {
        this.nameSymbol = nameSymbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tracker{" +
                "symbol='" + nameSymbol + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
