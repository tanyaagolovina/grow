package com.tetianaholovina.weatherforecast.model.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Plan {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String status;

    public Plan(){}

    public Plan(Integer id, User user, Event event) {
        this.id = id;
        this.user = user;
        this.event = event;
    }


    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
