package com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionQuestionAnswer {
    String id;
    boolean isSelected;

    public SessionQuestionAnswer(String id, boolean isSelected)
    {
        this.id = id;
        this.isSelected = isSelected;
    }
}
