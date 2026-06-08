package LLD.standard.coffee_shop;

public class CreamDecorator extends CoffeeDecorator {

    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " with Cream";
    }

    @Override
    public int getPrice() {
        return coffee.getPrice() + 3;
    }
}
