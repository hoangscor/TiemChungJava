package edu.uth.tiemchungjava.models;

import jakarta.persistence.*;

@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String ageGroup;
    private Integer price;
    public Vaccine() {}
    public Vaccine(String name, String description, String ageGroup, Integer price) {
        this.name = name;
        this.description = description;
        this.ageGroup = ageGroup;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
