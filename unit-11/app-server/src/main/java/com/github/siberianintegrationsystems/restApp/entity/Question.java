package com.github.siberianintegrationsystems.restApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Question extends BaseEntity {

    @Column
    private String name;

    public Question() {
    }

    public Question(String name) {
        this.name = name;
    }
}
