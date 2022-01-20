package com.Vijay.Gluer.Model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserRegistration")
public class UserRegistration {
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Id
    @Column(name = "email",nullable = false)
    private String Email;
    private String Name;

    private String password;
    private String confirmpass;
    private String Country;
    private String Validated;
    private String encryptor;

    public UserRegistration(String email, String name, String password, String confirmpass, String country, String validated, String encryptor) {
        Email = email;
        Name = name;
        this.password = password;
        this.confirmpass = confirmpass;
        Country = country;
        Validated = validated;
        this.encryptor = encryptor;
    }

    public String getEncryptor() {
        return encryptor;
    }

    public void setEncyptor(String encryptor) {
        this.encryptor = encryptor;
    }

    //    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }



    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public UserRegistration() {
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidated() {
        return Validated;
    }

    public void setValidated(String validated) {
        Validated = validated;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }


    @Override
    public String toString() {
        return "UserRegistration{" +
                "Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", password='" + password + '\'' +
                ", ConfirmPassword='" + confirmpass + '\'' +
                ", Country='" + Country + '\'' +
                '}';
    }
}
