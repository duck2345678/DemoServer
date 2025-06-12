package org.example.AgentManagementBE.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DebtReportID implements Serializable 
{
    @Column(name = "monthTime")
    private int month;

    @Column(name = "yearTime")
    private int year;

    @ManyToOne
    @JoinColumn(name = "agentID")
    private Agent agentID;

    public DebtReportID() { }

    public DebtReportID(int month, int year, Agent agentID) 
    {
        this.month = month;
        this.year = year;
        this.agentID = agentID;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Agent getAgentID() {
        return agentID;
    }

    // ✅ Bắt buộc ghi đè equals và hashCode để JPA dùng composite key chính xác
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DebtReportID)) return false;
        DebtReportID that = (DebtReportID) o;
        return month == that.month &&
               year == that.year &&
               Objects.equals(agentID.getAgentID(), that.agentID.getAgentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year, agentID.getAgentID());
    }
}
