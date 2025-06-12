package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Unit") // Tên bảng trùng với tên trong Flyway
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tương thích với SERIAL trong PostgreSQL
    @Column(name = "unitID")
    private int unitID;

    @Column(name = "unitName", nullable = false, length = 255)
    private String unitName;

    // Constructors
    public Unit() {}

    public Unit(String unitName) {
        this.unitName = unitName;
    }

    // Getters & Setters
    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
