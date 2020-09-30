package com.github.siberianintegrationsystems.restApp.service.journal.session;

import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.AnsweredQuestionDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionResultDTO;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Session;

import java.util.List;

public interface SessionService {
    List<QuestionsItemDTO> getQuestions();
    Number getPercent(SessionResultDTO dto);
    double getQuestionsPercent(AnsweredQuestionDTO dto);
    Session saveSessionInfo(SessionResultDTO dto, double percent);
    boolean contain(String id, List<Answer> correctAnswers);
    void saveSelectedAnswers(Session session, List<AnsweredQuestionDTO> answeredQuestionDTOS);
}
