package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

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

    public ImportReceipt() {
    }

    public ImportReceipt(int importReceiptID, int totalPrice) {
        this.importReceiptID = importReceiptID;
        this.totalPrice = totalPrice;
    }

    public ImportReceipt(int totalPrice) {
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
}
