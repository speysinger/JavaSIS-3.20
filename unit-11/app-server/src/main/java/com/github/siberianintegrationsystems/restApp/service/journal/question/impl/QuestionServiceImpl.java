package com.github.siberianintegrationsystems.restApp.service.journal.question.impl;

import com.github.siberianintegrationsystems.restApp.controller.dto.answerDTOS.AnswerItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.service.journal.question.QuestionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question(dto.name);
        questionRepository.save(question);

        saveAnswers(dto.answers, question);
        return new QuestionsItemDTO(question,
                answerRepository.findByQuestion(question));
    }

    @Override
    public void editQuestion(QuestionsItemDTO dto) {
        Question question = new Question(dto.name);
        question.setId(Long.parseLong(dto.id));
        questionRepository.updateQuestion(question.getId(), question.getName());

        saveAnswers(dto.answers, question);
    }

    @Override
    public void saveAnswers(List<AnswerItemDTO> answerItemDTOList, Question question) {
        for (AnswerItemDTO answerDTO : answerItemDTOList) {
            Answer answer = new Answer(answerDTO.answerText, question, answerDTO.isCorrect);
            answerRepository.save(answer);
        }
    }
}
