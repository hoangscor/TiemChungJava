package edu.uth.tiemchungjava.dto;
public class VaccineDTO {
    private String name;
    private String description;
    private String ageGroup;
    private Integer price;
    private String img_url;

    public VaccineDTO(String name, String description, String ageGroup, Integer price, String imgUrl) {
        this.name = name;
        this.description = description;
        this.ageGroup = ageGroup;
        this.price = price;
        this.img_url= img_url;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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