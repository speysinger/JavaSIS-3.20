package com.github.siberianintegrationsystems.restApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Journal {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private Long defaultPageSize;
}
