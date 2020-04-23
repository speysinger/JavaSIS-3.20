package com.github.siberianintegrationsystems.restApp.service.journal.question;

import com.github.siberianintegrationsystems.restApp.controller.dto.answerDTOS.AnswerItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.entity.Question;

import java.util.List;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);

    void editQuestion(QuestionsItemDTO dto);
    void saveAnswers(List<AnswerItemDTO> answerItemDTOList, Question question);
}
