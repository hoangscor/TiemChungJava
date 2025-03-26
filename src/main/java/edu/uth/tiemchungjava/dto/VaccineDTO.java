package edu.uth.tiemchungjava.dto;
public class VaccineDTO {
    private String name;
    private String description;
    private String ageGroup;
    private Integer price;

    public VaccineDTO(String name, String description, String ageGroup, Integer price) {
        this.name = name;
        this.description = description;
        this.ageGroup = ageGroup;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}