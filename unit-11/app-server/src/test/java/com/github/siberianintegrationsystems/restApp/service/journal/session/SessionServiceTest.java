package com.github.siberianintegrationsystems.restApp.service.journal.session;

import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.AnsweredQuestionDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionQuestionAnswer;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionResultDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;


public class SessionServiceTest{

    @Autowired
    private SessionService sessionService;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void getPercent() {

        SessionResultDTO sessionResultDTO = new SessionResultDTO();
        Question question0 = new Question("A+B");
        Question question1 = new Question("A+C");

        Answer answer0 = new Answer("DA0", question0, true);
        Answer answer1 = new Answer("NET0", question0, false);
        Answer answer2 = new Answer("DA1", question0, true);
        Answer answer3 = new Answer("NET1", question0, false);

        question0 = questionRepository.save(question0);
        question1 = questionRepository.save(question1);

        answer0 = answerRepository.save(answer0);
        answer1 = answerRepository.save(answer1);
        answer2 = answerRepository.save(answer2);
        answer3 = answerRepository.save(answer3);

        SessionQuestionAnswer sessionQuestionAnswer0 = new SessionQuestionAnswer(String.valueOf(answer0.getId()), true);
        SessionQuestionAnswer sessionQuestionAnswer1 = new SessionQuestionAnswer(String.valueOf(answer1.getId()), false);
        SessionQuestionAnswer sessionQuestionAnswer2 = new SessionQuestionAnswer(String.valueOf(answer2.getId()), false);
        SessionQuestionAnswer sessionQuestionAnswer3 = new SessionQuestionAnswer(String.valueOf(answer3.getId()), true);

        AnsweredQuestionDTO answeredQuestionDTO0 = new AnsweredQuestionDTO();
        answeredQuestionDTO0.setId(String.valueOf(question0.getId()));
        answeredQuestionDTO0.setAnswersList(Arrays.asList(sessionQuestionAnswer0, sessionQuestionAnswer1));

        AnsweredQuestionDTO answeredQuestionDTO1 = new AnsweredQuestionDTO();
        answeredQuestionDTO0.setId(String.valueOf(question1.getId()));
        answeredQuestionDTO0.setAnswersList(Arrays.asList(sessionQuestionAnswer2, sessionQuestionAnswer3));

        sessionResultDTO.setQuestionsList(Arrays.asList(answeredQuestionDTO0, answeredQuestionDTO1));

        Number percent = sessionService.getPercent(sessionResultDTO);

        Assert.assertEquals(50, percent);
    }

}