package org.example.AgentManagementBE.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Parameter {

    @Id
    @Column(name = "parameterName", nullable = false) // tên tham số
    private String parameterName;

    @Column(name = "parameterValue", nullable = false) // giá trị tham số
    private int parameterValue;

    public Parameter() {
    }

    public Parameter(String parameterName, int parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public int getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(int parameterValue) {
        this.parameterValue = parameterValue;
    }
}
