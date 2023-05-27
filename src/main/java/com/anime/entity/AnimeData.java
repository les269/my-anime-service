package com.anime.entity;

import javax.persistence.*;

@Entity
@Table(name="animeData")
@IdClass(AnimeDataId.class)
public class AnimeData {
    @Id
    private String officeName;

    @Id
    private String category;

    @Id
    private String type;

    private String value;


    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
