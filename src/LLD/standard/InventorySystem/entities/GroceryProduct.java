package LLD.standard.InventorySystem.entities;

import LLD.standard.InventorySystem.enums.ProductCategory;

import java.util.Date;

public class GroceryProduct extends Product {

    private Date expiryDate;
    private boolean refrigerated;

    public GroceryProduct(String sku, String name, double price, int quantity) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(ProductCategory.GROCERY);
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }
}
