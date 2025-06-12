package org.example.AgentManagementBE.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class DebtReportID implements Serializable {

    @Column(name = "monthTime")
    private int month;

    @Column(name = "yearTime")
    private int year;

    @ManyToOne
    @JoinColumn(name = "agentID")
    private Agent agentID;

    public DebtReportID() {}

    public DebtReportID(int month, int year, Agent agentID) {
        this.month = month;
        this.year = year;
        this.agentID = agentID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Agent getAgentID() {
        return agentID;
    }

    public void setAgentID(Agent agentID) {
        this.agentID = agentID;
    }
}
