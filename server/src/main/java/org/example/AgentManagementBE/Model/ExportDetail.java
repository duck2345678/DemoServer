package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "ExportDetail")
public class ExportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exportDetailID")
    private Long exportDetailID; // 👈 Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exportReceiptID", nullable = false)
    private ExportReceipt exportReceipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID", nullable = false)
    private Product product;

    @Column(name = "quantityExport", nullable = false)
    private int quantityExport;

    @Column(name = "exportPrice", nullable = false)
    private int exportPrice;

    @Column(name = "intoMoney", nullable = false)
    private int intoMoney;

    // Constructors
    public ExportDetail() {
    }

    public ExportDetail(ExportReceipt exportReceipt, Product product, int quantityExport, int exportPrice, int intoMoney) {
        this.exportReceipt = exportReceipt;
        this.product = product;
        this.quantityExport = quantityExport;
        this.exportPrice = exportPrice;
        this.intoMoney = intoMoney;
    }

    // Getters and Setters
    public Long getExportDetailID() {
        return exportDetailID;
    }

    public void setExportDetailID(Long exportDetailID) {
        this.exportDetailID = exportDetailID;
    }

    public ExportReceipt getExportReceipt() {
        return exportReceipt;
    }

    public void setExportReceipt(ExportReceipt exportReceipt) {
        this.exportReceipt = exportReceipt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
