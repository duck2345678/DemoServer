package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ExportReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exportReceiptID")
    private int exportReceiptID;

    @CreatedDate
    @Column(name = "dateReceipt", nullable = false, updatable = false)
    private Date dateReceipt;

    @ManyToOne
    @JoinColumn(name = "agentID", nullable = false)
    private Agent agent;

    @Column(name = "totalMoney")
    private int totalMoney;

    @Column(name = "paymentAmount")
    private int paymentAmount;

    @Column(name = "remainAmount")
    private int remainAmount;

    @OneToMany(mappedBy = "exportReceipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExportDetail> exportDetails = new ArrayList<>();

    // --- Constructors ---
    public ExportReceipt() {
    }

    public ExportReceipt(Agent agent, int totalMoney, int paymentAmount, int remainAmount) {
        this.agent = agent;
        this.totalMoney = totalMoney;
        this.paymentAmount = paymentAmount;
        this.remainAmount = remainAmount;
    }

    public ExportReceipt(int exportReceiptID) {
        this.exportReceiptID = exportReceiptID;
    }

    // --- Getters & Setters ---

    public int getExportReceiptID() {
        return exportReceiptID;
    }

    public void setExportReceiptID(int exportReceiptID) {
        this.exportReceiptID = exportReceiptID;
    }

    public Date getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(Date dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(int remainAmount) {
        this.remainAmount = remainAmount;
    }

    public List<ExportDetail> getExportDetails() {
        return exportDetails;
    }

    public void setExportDetails(List<ExportDetail> exportDetails) {
        this.exportDetails = exportDetails;
    }
}
