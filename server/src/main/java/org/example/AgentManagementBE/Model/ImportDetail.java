package org.example.AgentManagementBE.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(ImportDetail.ImportDetailID.class)
public class ImportDetail implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "importReceiptID", nullable = false)
    @JsonIgnore
    private ImportReceipt importReceipt;

    @Id
    @ManyToOne
    @JoinColumn(name = "productID", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(name = "quantityImport", nullable = false)
    private int quantityImport;

    @Column(name = "importPrice", nullable = false)
    private int importPrice;

    @Column(name = "intoMoney", nullable = false)
    private int intoMoney;

    @Column(name = "unit", columnDefinition = "VARCHAR(50)")
    private String unit;

    // --- Constructors ---
    public ImportDetail() {
    }

    public ImportDetail(ImportReceipt importReceipt, Product product, int quantityImport, int importPrice, int intoMoney, String unit) {
        this.importReceipt = importReceipt;
        this.product = product;
        this.quantityImport = quantityImport;
        this.importPrice = importPrice;
        this.intoMoney = intoMoney;
        this.unit = unit;
    }

    // --- Getters & Setters ---

    public ImportReceipt getImportReceipt() {
        return importReceipt;
    }

    public void setImportReceipt(ImportReceipt importReceipt) {
        this.importReceipt = importReceipt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityImport() {
        return quantityImport;
    }

    public void setQuantityImport(int quantityImport) {
        this.quantityImport = quantityImport;
    }

    public int getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(int importPrice) {
        this.importPrice = importPrice;
    }

    public int getIntoMoney() {
        return intoMoney;
    }

    public void setIntoMoney(int intoMoney) {
        this.intoMoney = intoMoney;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // --- Composite Key Class ---
    public static class ImportDetailID implements Serializable {
        private int importReceipt;
        private int product;

        public ImportDetailID() {
        }

        public ImportDetailID(int importReceipt, int product) {
            this.importReceipt = importReceipt;
            this.product = product;
        }

        public int getImportReceipt() {
            return importReceipt;
        }

        public void setImportReceipt(int importReceipt) {
            this.importReceipt = importReceipt;
        }

        public int getProduct() {
            return product;
        }

        public void setProduct(int product) {
            this.product = product;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ImportDetailID)) return false;
            ImportDetailID that = (ImportDetailID) o;
            return importReceipt == that.importReceipt &&
                   product == that.product;
        }

        @Override
        public int hashCode() {
            return Objects.hash(importReceipt, product);
        }
    }
}
