package LLD.standard.coffee_shop;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " with Milk";
    }

    @Override
    public int getPrice() {
        return coffee.getPrice() + 5;
    }
}
