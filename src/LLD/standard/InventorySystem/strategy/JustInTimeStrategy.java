package LLD.standard.InventorySystem.strategy;

import LLD.standard.InventorySystem.entities.Product;

public class JustInTimeStrategy implements ReplenishmentStrategy {

    @Override
    public void replenish(Product product) {
        System.out.println("Applying Just-In-Time strategy for product: " + product.getName());
    }
}
