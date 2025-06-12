package org.example.AgentManagementBE.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin") // Khớp tên bảng Flyway đã tạo
public class Admin {

    @Id
    @Column(name = "useremail", nullable = false) // Khớp chính xác tên cột đã tạo qua Flyway
    private String userEmail;

    @Column(name = "password", nullable = false)
    private String password;

    public Admin() {
    }

    public Admin(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
