package LLD.standard.InventorySystem.strategy;

import LLD.standard.InventorySystem.entities.Product;

public class BulkOrderStrategy implements ReplenishmentStrategy {

    @Override
    public void replenish(Product product) {
        System.out.println("Applying Bulk Order strategy for product: " + product.getName());
    }
}
