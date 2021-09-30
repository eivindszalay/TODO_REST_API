package model;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private String description;
    private Boolean completed;
    private String urlId;

    public Todo() {
        this.summary = "Empty todo.";
        this.description = "This is an empty todo.";
        this.completed = false;
        this.urlId = "1000";
    }

    public Todo(String summary, String description, Boolean completed, String urlId) {
        this.summary = summary;
        this.description = description;
        this.completed = completed;
        this.urlId = urlId;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public Long getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo [summary=" + summary + ", description=" + description
                + ", completed=" + completed + "]";
    }

    String toJson() {

        Gson gson = new Gson();

        String jsonInString = gson.toJson(this);

        return jsonInString;
    }

}