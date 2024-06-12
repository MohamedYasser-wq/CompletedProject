package com.example.miniuper.Data;

public class ComplainsModel {

    String id;
    String Complains;
    String Rating;

    public ComplainsModel() {
    }

    public ComplainsModel(String id, String complains, String rating) {
        this.id = id;
        Complains = complains;
        Rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComplains() {
        return Complains;
    }

    public void setComplains(String complains) {
        Complains = complains;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
