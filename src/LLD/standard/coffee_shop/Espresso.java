package LLD.standard.coffee_shop;

public class Espresso implements Coffee {

    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Espresso Coffee";
    }
}
