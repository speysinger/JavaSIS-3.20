package com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS;

import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionQuestionAnswer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnsweredQuestionDTO {
    public String id;
    public List<SessionQuestionAnswer> answersList;
}
