package com.kabaldin.controller.DAO.entity;

import java.util.Objects;

public class Feedback {
    private int id;
    private String name;
    private String dataTime;
    private String feedback;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback1 = (Feedback) o;
        return id == feedback1.id &&
                rating == feedback1.rating &&
                Objects.equals(name, feedback1.name) &&
                Objects.equals(dataTime, feedback1.dataTime) &&
                Objects.equals(feedback, feedback1.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dataTime, feedback, rating);
    }
}
