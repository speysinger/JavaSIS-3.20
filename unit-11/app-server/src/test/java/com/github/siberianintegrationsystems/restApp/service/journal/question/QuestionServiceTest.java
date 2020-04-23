package com.github.siberianintegrationsystems.restApp.service.journal.question;

import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void testCreateQuestion() {
        Question question = new Question("1+1");
        question.setId(0L);
        QuestionsItemDTO questionsItemDTO = new QuestionsItemDTO(question, Collections.<Answer>emptyList());

        QuestionsItemDTO expectedQuestionDTO = questionService.createQuestion(questionsItemDTO);

        Assert.assertEquals(question.getName(), expectedQuestionDTO.name);
    }

    @Test
    public void testEditQuestion() {
        Question question = new Question("5+5");
        QuestionsItemDTO questionsItemDTO = new QuestionsItemDTO(question, new ArrayList<Answer>());

        QuestionsItemDTO expectedQuestionDTO = questionService.createQuestion(questionsItemDTO);

        question.setName("5+6");
        QuestionsItemDTO editedQuestionItemDTO = new QuestionsItemDTO(question, new ArrayList<Answer>());

        questionService.editQuestion(editedQuestionItemDTO);

        Assert.assertTrue(questionRepository.
                findAll().
                stream().
                anyMatch(question1 -> question1.
                        getName().
                        equals(question.getName())));
    }
}