package com.tetianaholovina.weatherforecast.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

    @Id
    private Integer id;

    private String title;

    private String address;

    private String datetime;

    private String forecast;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Plan> usersForEvent = new HashSet<>();

    public Event(){}

    public Event(Integer id, String title, String address, String datetime) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}
