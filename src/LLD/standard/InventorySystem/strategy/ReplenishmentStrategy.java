package LLD.standard.InventorySystem.strategy;

import LLD.standard.InventorySystem.entities.Product;

public interface ReplenishmentStrategy {

    void replenish(Product product);
}
