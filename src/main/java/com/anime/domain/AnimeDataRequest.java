package com.anime.domain;

public class AnimeDataRequest {
    private String officeName;
    private String type;
    private String value;
    private boolean watched;

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
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
