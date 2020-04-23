package com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS;

import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.AnsweredQuestionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionResultDTO {
    String name;
    List<AnsweredQuestionDTO> questionsList;
}
