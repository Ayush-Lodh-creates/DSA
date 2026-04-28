package LLD.standard.InventorySystem;

import LLD.standard.InventorySystem.entities.Product;
import LLD.standard.InventorySystem.entities.Warehouse;
import LLD.standard.InventorySystem.enums.ProductCategory;
import LLD.standard.InventorySystem.factory.ProductFactory;
import LLD.standard.InventorySystem.observer.InventoryObserver;
import LLD.standard.InventorySystem.observer.SupplierNotifier;
import LLD.standard.InventorySystem.singleton.InventoryManager;
import LLD.standard.InventorySystem.strategy.BulkOrderStrategy;
import LLD.standard.InventorySystem.strategy.JustInTimeStrategy;

public class InventoryServiceMain {

    public static void main(String[]  args) {
        InventoryManager inventoryManager = InventoryManager.getInstance();

        Warehouse warehouse1 = new Warehouse(1, "Warehouse1", "Blr");
        Warehouse warehouse2 = new Warehouse(2, "Warehouse2", "Hyd");
        inventoryManager.addWarehouse(warehouse1);
        inventoryManager.addWarehouse(warehouse2);

        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createProduct(ProductCategory.ELECTRONICS, "SKU123", "Laptop", 1000.0, 50);
        Product tShirt = productFactory.createProduct(ProductCategory.CLOTHING, "SKU456", "T-Shirt", 20.0, 200);
        Product apple = productFactory.createProduct(ProductCategory.GROCERY, "SKU789", "Apple", 1.0, 100);

        warehouse1.addProduct(laptop);
        warehouse1.addProduct(tShirt);
        warehouse2.addProduct(apple);

        inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());
        inventoryManager.performInventoryCheck();
        inventoryManager.setReplenishmentStrategy(new BulkOrderStrategy());
        inventoryManager.checkAndReplenish("SKU123");

        InventoryObserver observer = new SupplierNotifier("A", "a@gmail.com");
        inventoryManager.addObserver(observer);
        inventoryManager.notifyObservers(laptop);
        inventoryManager.notifyObservers(tShirt);
        inventoryManager.notifyObservers(apple);
    }
}
