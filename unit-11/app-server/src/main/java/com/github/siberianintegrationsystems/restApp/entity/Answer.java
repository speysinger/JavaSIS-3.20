package com.github.siberianintegrationsystems.restApp.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Answer extends BaseEntity {

    @Column
    private String name;

    @JoinColumn(name = "question_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @Column
    private Boolean isCorrect;

    public Answer() {
    }

    public Answer(String name, Question question, Boolean isCorrect) {
        this.name = name;
        this.question = question;
        this.isCorrect = isCorrect;
    }
}
