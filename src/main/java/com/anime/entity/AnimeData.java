package com.anime.entity;

import javax.persistence.*;

@Entity
@Table(name="animeData")
@IdClass(AnimeDataId.class)
public class AnimeData {
    @Id
    private String officialName;

    @Id
    private String category;

    @Id
    private String type;

    private String value;


    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
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
