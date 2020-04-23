package com.github.siberianintegrationsystems.restApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseEntity {
    @Column
    String name;

    @Column
    Double percent;

    @Column
    Date date;

    public Session() {

    }

    public Session(String name, Double percent, Date date) {
        this.name = name;
        this.percent = percent;
        this.date = date;
    }


}
