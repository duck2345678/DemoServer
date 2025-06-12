package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DebtReportID implements Serializable {
    private int month;
    private int year;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    public DebtReportID() {}

    public DebtReportID(int month, int year, Agent agent) {
        this.month = month;
        this.year = year;
        this.agent = agent;
    }

    // Getters and Setters
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public Agent getAgent() { return agent; }
    public void setAgent(Agent agent) { this.agent = agent; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DebtReportID)) return false;
        DebtReportID that = (DebtReportID) o;
        return month == that.month &&
               year == that.year &&
               Objects.equals(agent.getAgentID(), that.agent.getAgentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year, agent.getAgentID());
    }
}
