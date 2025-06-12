package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // dùng IDENTITY cho tương thích PostgreSQL SERIAL
    @Column(name = "productID")
    private int productID;

    @Column(name = "productName", nullable = false, length = 255)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "unit", nullable = false)
    private Unit unit;

    @Column(name = "importPrice", nullable = false)
    private int importPrice;

    @Column(name = "exportPrice")
    private int exportPrice;

    @Column(name = "inventoryQuantity")
    private int inventoryQuantity;

    public Product() {}

    public Product(String productName, Unit unit, int importPrice) {
        this.productName = productName;
        this.unit = unit;
        this.importPrice = importPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(int importPrice) {
        this.importPrice = importPrice;
    }

    public int getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(int exportPrice) {
        this.exportPrice = exportPrice;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }
}
