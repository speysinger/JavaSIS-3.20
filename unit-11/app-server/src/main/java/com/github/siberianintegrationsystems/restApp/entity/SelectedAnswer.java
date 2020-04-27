package com.github.siberianintegrationsystems.restApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class SelectedAnswer extends BaseEntity {

    @JoinColumn(name = "answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    public SelectedAnswer(Answer answer, Session session) {
        this.answer = answer;
        this.session = session;
    }
}
