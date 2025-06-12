package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
public class AgentType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "agentTypeID")
    private int agentTypeID;

    @Column(name = "agentTypeName", nullable = false, length = 255)
    private String agentTypeName;

    @Column(name = "maximumDebt", nullable = false)
    private int maximumDebt;

    public AgentType() {}

    public AgentType(String agentTypeName, int maximumDebt) {
        this.agentTypeName = agentTypeName;
        this.maximumDebt = maximumDebt;
    }

    public int getAgentTypeID() {
        return agentTypeID;
    }

    public void setAgentTypeID(int agentTypeID) {
        this.agentTypeID = agentTypeID;
    }

    public String getAgentTypeName() {
        return agentTypeName;
    }

    public void setAgentTypeName(String agentTypeName) {
        this.agentTypeName = agentTypeName;
    }

    public int getMaximumDebt() {
        return maximumDebt;
    }

    public void setMaximumDebt(int maximumDebt) {
        this.maximumDebt = maximumDebt;
    }
}
