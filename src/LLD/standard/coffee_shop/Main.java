package LLD.standard.coffee_shop;

public class Main {

    public static void main(String[] args) {

        Coffee coffee = new Cappuccino();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new CreamDecorator(coffee);

        System.out.println("Coffee Description: " + coffee.getDescription());
        System.out.println("Total Price: " + coffee.getPrice());
    }
}
