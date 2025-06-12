package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "personID") // mã người dùng
    private int personID;

    @Column(name = "personLastName", nullable = false, length = 255) // họ
    private String personLastName;

    @Column(name = "personName", nullable = false, length = 255) // tên
    private String personName;

    @Column(name = "personSDT", nullable = false, length = 255) // số điện thoại
    private String personSDT;

    @Column(name = "personEmail", nullable = false, unique = true, length = 255) // email
    private String personEmail;

    @Column(name = "personPassword", nullable = false, length = 255) // mật khẩu
    private String personPassword;

    // Constructor không tham số
    public Person() {
    }

    // Constructor có tham số
    public Person(String personLastName, String personName, String personSDT, String personEmail, String personPassword) {
        this.personLastName = personLastName;
        this.personName = personName;
        this.personSDT = personSDT;
        this.personEmail = personEmail;
        this.personPassword = personPassword;
    }

    // Getter
    public int getPersonID() {
        return personID;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonSDT() {
        return personSDT;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    // Setter
    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonSDT(String personSDT) {
        this.personSDT = personSDT;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }
}
