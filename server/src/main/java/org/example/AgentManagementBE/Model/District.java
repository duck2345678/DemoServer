package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "districtID")
    private int districtID;

    @Column(name = "districtName", nullable = false, columnDefinition = "VARCHAR(255)")
    private String districtName;

    public District() {}

    public District(int districtID) {
        this.districtID = districtID;
    }

    public District(String districtName) {
        this.districtName = districtName;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
