package com.openclassrooms.mediscreen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Given name is mandatory")
    @Size(max = 25, message = "Name must not exceed 25 characters")
    private String given;
    @Column
    @NotBlank(message = "Family name is mandatory")
    @Size(max = 25, message = "Family name must not exceed 25 characters")
    private String family;
    @Column
    @NotBlank(message = "Sex is mandatory")
    @Pattern(regexp = "^(M|F|Other)$", message = "Sex must be Male(M), Female(F), or Other")
    private String sex;
    @Column
    @NotBlank(message = "Address is mandatory")
    @Size(max = 100, message = "Address must not exceed 100 characters")
    private String address;
    @Column
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must be in the format 100-222-3333")
    private String phone;
    @Column
    @Past(message = "Date of birth must be in the past")
    private Date dob;

    public Patient(String given, String family, String sex, String address, String phone, Date dob) {
        this.given = given;
        this.family = family;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

}
