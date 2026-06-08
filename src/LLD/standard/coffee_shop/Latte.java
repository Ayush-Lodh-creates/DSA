package LLD.standard.coffee_shop;

public class Latte implements Coffee {

    @Override
    public int getPrice() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Latte Coffee";
    }
}
