package com.anime.domain;

public class AnimeDataRequest {
    private String officialName;
    private String type;
    private String value;
    private boolean watched;

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
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

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(String name,boolean watched) {
        this.watched = watched;
    }
}
