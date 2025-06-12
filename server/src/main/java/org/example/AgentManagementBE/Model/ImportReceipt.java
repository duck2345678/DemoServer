package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ImportReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importReceiptID") // mã phiếu nhập
    private int importReceiptID;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "dateReceipt", nullable = false, updatable = false) // ngày lập phiếu
    private Date dateReceipt;

    @Column(name = "totalPrice", nullable = false) // tổng giá
    private int totalPrice;

    // Quan hệ 1-n với ImportDetail (nếu bạn muốn ánh xạ ngược)
    @OneToMany(mappedBy = "importReceipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImportDetail> importDetails;

    public ImportReceipt() {
    }

    public ImportReceipt(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ImportReceipt(int importReceiptID, int totalPrice) {
        this.importReceiptID = importReceiptID;
        this.totalPrice = totalPrice;
    }

    public int getImportReceiptID() {
        return importReceiptID;
    }

    public void setImportReceiptID(int importReceiptID) {
        this.importReceiptID = importReceiptID;
    }

    public Date getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(Date dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ImportDetail> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(List<ImportDetail> importDetails) {
        this.importDetails = importDetails;
    }
}
