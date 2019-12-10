package com.tetianaholovina.weatherforecast.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class User {
    @Id
    private String id;

    private String name;

    private String email;

    private LocalDateTime lastVisit;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Plan> plans = new TreeSet<>(new DateComparator());

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }
}

