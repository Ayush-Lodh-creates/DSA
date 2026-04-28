package LLD.standard.InventorySystem.entities;

import LLD.standard.InventorySystem.enums.ProductCategory;

public class ElectronicsProduct extends Product {

    private String brand;
    private int warrantyPeriod;

    public ElectronicsProduct(String sku, String name, double price, int quantity) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(ProductCategory.ELECTRONICS);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
