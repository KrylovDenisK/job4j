package ru.job4j.parser;

import java.time.LocalDateTime;

public class Vacancy {
    private String name;
    private String description;
    private String link;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    private LocalDateTime dateTime;

    public Vacancy(String name, String description, String link, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.dateTime = dateTime;
    }

    public Vacancy(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
