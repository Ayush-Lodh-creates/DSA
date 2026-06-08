package LLD.standard.coffee_shop;

public class Cappuccino implements Coffee{

    @Override
    public int getPrice() {
        return 15;
    }

    @Override
    public String getDescription() {
        return "Cappuccino coffee";
    }
}
