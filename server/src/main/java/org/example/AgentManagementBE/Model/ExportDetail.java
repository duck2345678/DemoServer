package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(ExportDetail.ExportDetailID.class)
public class ExportDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "exportReceiptID", nullable = false)
    private ExportReceipt exportReceiptID;

    @Id
    @ManyToOne
    @JoinColumn(name = "productID", nullable = false)
    private Product productID;

    @Column(name = "quantityExport", nullable = false)
    private int quantityExport;

    @Column(name = "exportPrice", nullable = false)
    private int exportPrice;

    @Column(name = "intoMoney", nullable = false)
    private int intoMoney;

    public ExportDetail() {
    }

    public ExportDetail(ExportReceipt exportReceiptID, Product productID, int quantityExport, int exportPrice, int intoMoney) {
        this.exportReceiptID = exportReceiptID;
        this.productID = productID;
        this.quantityExport = quantityExport;
        this.exportPrice = exportPrice;
        this.intoMoney = intoMoney;
    }

    public ExportReceipt getExportReceiptID() {
        return exportReceiptID;
    }

    public void setExportReceiptID(ExportReceipt exportReceiptID) {
        this.exportReceiptID = exportReceiptID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public int getQuantityExport() {
        return quantityExport;
    }

    public void setQuantityExport(int quantityExport) {
        this.quantityExport = quantityExport;
    }

    public int getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(int exportPrice) {
        this.exportPrice = exportPrice;
    }

    public int getIntoMoney() {
        return intoMoney;
    }

    public void setIntoMoney(int intoMoney) {
        this.intoMoney = intoMoney;
    }

    // ✅ Composite Key Class
    public static class ExportDetailID implements Serializable {
        private int exportReceiptID;
        private int productID;

        public ExportDetailID() {
        }

        public ExportDetailID(int exportReceiptID, int productID) {
            this.exportReceiptID = exportReceiptID;
            this.productID = productID;
        }

        public int getExportReceiptID() {
            return exportReceiptID;
        }

        public void setExportReceiptID(int exportReceiptID) {
            this.exportReceiptID = exportReceiptID;
        }

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        // ✅ Cần override để JPA xử lý composite key đúng
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ExportDetailID)) return false;
            ExportDetailID that = (ExportDetailID) o;
            return exportReceiptID == that.exportReceiptID &&
                   productID == that.productID;
        }

        @Override
        public int hashCode() {
            return Objects.hash(exportReceiptID, productID);
        }
    }
}
