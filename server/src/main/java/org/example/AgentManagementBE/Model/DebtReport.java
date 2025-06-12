package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
public class DebtReport {

    @EmbeddedId
    private DebtReportID debtReportID;

    @Column(name = "firstDebt")
    private int firstDebt;

    @Column(name = "lastDebt")
    private int lastDebt;

    @Column(name = "arisenDebt")
    private int arisenDebt;

    public DebtReport() {}

    public DebtReport(DebtReportID debtReportID, int firstDebt, int lastDebt, int arisenDebt) {
        this.debtReportID = debtReportID;
        this.firstDebt = firstDebt;
        this.lastDebt = lastDebt;
        this.arisenDebt = arisenDebt;
    }

    public DebtReportID getDebtReportID() {
        return debtReportID;
    }

    public void setDebtReportID(DebtReportID debtReportID) {
        this.debtReportID = debtReportID;
    }

    public int getFirstDebt() {
        return firstDebt;
    }

    public void setFirstDebt(int firstDebt) {
        this.firstDebt = firstDebt;
    }

    public int getLastDebt() {
        return lastDebt;
    }

    public void setLastDebt(int lastDebt) {
        this.lastDebt = lastDebt;
    }

    public int getArisenDebt() {
        return arisenDebt;
    }

    public void setArisenDebt(int arisenDebt) {
        this.arisenDebt = arisenDebt;
    }
}
