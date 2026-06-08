package LLD.standard.coffee_shop;

public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " with Sugar";
    }

    @Override
    public int getPrice() {
        return coffee.getPrice() + 2;
    }
}
