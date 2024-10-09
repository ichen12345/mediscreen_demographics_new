package com.openclassrooms.mediscreen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String given;
    @Column
    private String family;
    @Column
    private String sex;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private Date dob;

    public Patient(String given, String family, String sex, String address, String phone, Date dob, String note) {
        this.given = given;
        this.family = family;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }
    public Patient(Long id, String sex, String address, String phone, Date dob, String note) {
        this.id = id;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

}
