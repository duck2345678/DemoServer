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
    @JoinColumn(name = "importReceiptID")
    @JsonIgnore
    private ImportReceipt importReceiptID;

    @Id
    @ManyToOne
    @JoinColumn(name = "productID") // mã sản phẩm
    @JsonIgnore
    private Product productID;

    @Column(name = "quantityImport") // số lượng nhập
    private int quantityImport;

    @Column(name = "importPrice") // giá nhập
    private int importPrice;

    @Column(name = "intoMoney") // thành tiền
    private int intoMoney;

    @Column(name = "unit", columnDefinition = "VARCHAR(50)") // đơn vị tính
    private String unit;

    public ImportDetail() {
    }

    public ImportDetail(ImportReceipt importReceiptID, Product productID, int quantityImport, int importPrice, int intoMoney, String unit) {
        this.importReceiptID = importReceiptID;
        this.productID = productID;
        this.quantityImport = quantityImport;
        this.importPrice = importPrice;
        this.intoMoney = intoMoney;
        this.unit = unit;
    }

    public ImportReceipt getImportReceiptID() {
        return importReceiptID;
    }

    public void setImportReceiptID(ImportReceipt importReceiptID) {
        this.importReceiptID = importReceiptID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
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

    // Composite key class
    public static class ImportDetailID implements Serializable {
        private int importReceiptID;
        private int productID;

        public ImportDetailID() {
        }

        public ImportDetailID(int importReceiptID, int productID) {
            this.importReceiptID = importReceiptID;
            this.productID = productID;
        }

        public int getImportReceiptID() {
            return importReceiptID;
        }

        public void setImportReceiptID(int importReceiptID) {
            this.importReceiptID = importReceiptID;
        }

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ImportDetailID)) return false;
            ImportDetailID that = (ImportDetailID) o;
            return importReceiptID == that.importReceiptID &&
                   productID == that.productID;
        }

        @Override
        public int hashCode() {
            return Objects.hash(importReceiptID, productID);
        }
    }
}
