package edu.uth.tiemchungjava.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vaccination_bookings")
public class VaccinationBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalTime bookingTime;

    @Column(nullable = false)
    private String vaccineType;

    @Column
    private String underlyingConditions;

    // Enum cho giới tính
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // Constructors
    public VaccinationBooking() {}

    // Constructor đầy đủ
    public VaccinationBooking(String fullName, Gender gender, Integer age,
                              String phoneNumber, String email,
                              LocalDate bookingDate, LocalTime bookingTime,
                              String vaccineType, String underlyingConditions) {
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.vaccineType = vaccineType;
        this.underlyingConditions = underlyingConditions;
    }

    // Getters and Setters (bỏ qua để tiết kiệm không gian)

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }
    public String getVaccineType() {
        return vaccineType;
    }
    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }
    public String getUnderlyingConditions() {
        return underlyingConditions;
    }
    public void setUnderlyingConditions(String underlyingConditions) {
        this.underlyingConditions = underlyingConditions;
    }
}