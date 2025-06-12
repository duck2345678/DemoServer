package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng IDENTITY thay cho SEQUENCE với PostgreSQL
    @Column(name = "agentid") // viết thường để khớp Flyway
    private int agentID;

    @Column(name = "agentname", nullable = false)
    private String agentName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "agenttypeid")
    private AgentType agentTypeID;

    @ManyToOne
    @JoinColumn(name = "districtid")
    private District districtID;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "receptiondate")
    private Date receptionDate;

    @Column(name = "debtmoney")
    private int debtMoney;

    // Constructors
    public Agent() {}

    public Agent(int agentID, String agentName) {
        this.agentID = agentID;
        this.agentName = agentName;
    }

    public Agent(int agentID, String agentName, String address, String phone, String email,
                 AgentType agentTypeID, District districtID, Date receptionDate, int debtMoney) {
        this.agentID = agentID;
        this.agentName = agentName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.agentTypeID = agentTypeID;
        this.districtID = districtID;
        this.receptionDate = receptionDate;
        this.debtMoney = debtMoney;
    }

    public Agent(String agentName, String address, String phone, String email,
                 AgentType agentTypeID, District districtID, Date receptionDate, int debtMoney) {
        this.agentName = agentName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.agentTypeID = agentTypeID;
        this.districtID = districtID;
        this.receptionDate = receptionDate;
        this.debtMoney = debtMoney;
    }

    // Getters & Setters
    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AgentType getAgentTypeID() {
        return agentTypeID;
    }

    public void setAgentTypeID(AgentType agentTypeID) {
        this.agentTypeID = agentTypeID;
    }

    public District getDistrictID() {
        return districtID;
    }

    public void setDistrictID(District districtID) {
        this.districtID = districtID;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public int getDebtMoney() {
        return debtMoney;
    }

    public void setDebtMoney(int debtMoney) {
        this.debtMoney = debtMoney;
    }
}
