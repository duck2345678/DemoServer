package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "agentID") // mã đại lý
    private int agentId;

    @Column(name = "agentName", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String agentName;

    @Column(name = "address", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "agentTypeID") // mã loại đại lý
    private AgentType agentTypeId;

    @ManyToOne
    @JoinColumn(name = "districtID") // mã quận
    private District districtId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "receptionDate") // ngày tiếp nhận
    private Date receptionDate;

    @Column(name = "debtMoney") // số tiền nợ
    private int debtMoney;

    public Agent() {
    }

    public Agent(int agentId, String agentName) {
        this.agentId = agentId;
        this.agentName = agentName;
    }

    public Agent(int agentId, String agentName, String address, String phone, String email, AgentType agentTypeId, District districtId, Date receptionDate, int debtMoney) {
        this.agentId = agentId;
        this.agentName = agentName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.agentTypeId = agentTypeId;
        this.districtId = districtId;
        this.receptionDate = receptionDate;
        this.debtMoney = debtMoney;
    }

    public Agent(String agentName, String address, String phone, String email, AgentType agentTypeId, District districtId, Date receptionDate, int debtMoney) {
        this.agentName = agentName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.agentTypeId = agentTypeId;
        this.districtId = districtId;
        this.receptionDate = receptionDate;
        this.debtMoney = debtMoney;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
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

    public AgentType getAgentTypeId() {
        return agentTypeId;
    }

    public void setAgentTypeId(AgentType agentTypeId) {
        this.agentTypeId = agentTypeId;
    }

    public District getDistrictId() {
        return districtId;
    }

    public void setDistrictId(District districtId) {
        this.districtId = districtId;
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
