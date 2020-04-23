package com.github.siberianintegrationsystems.restApp.service.journal.session.impl;

import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.AnsweredQuestionDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionQuestionAnswer;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionResultDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.data.Selected_AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.SessionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.entity.Selected_Answer;
import com.github.siberianintegrationsystems.restApp.entity.Session;
import com.github.siberianintegrationsystems.restApp.service.journal.session.SessionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final Selected_AnswerRepository selected_answerRepository;
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, Selected_AnswerRepository selected_answerRepository, SessionRepository sessionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.selected_answerRepository = selected_answerRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<QuestionsItemDTO> getQuestions() {

        List<Question> questions = questionRepository.findAll();
        int listSize = questions.size();

        if (listSize > 1) {
            Collections.shuffle(questions);
            return questions.
                    subList(0, listSize / 2).
                    stream().
                    map(question -> new QuestionsItemDTO(question, answerRepository.findByQuestion(question))).
                    collect(Collectors.toList());
        } else {
            return questions.
                    stream().
                    map(question -> new QuestionsItemDTO(question, answerRepository.findByQuestion(question))).
                    collect(Collectors.toList());
        }
    }

    @Override
    public Number getPercent(SessionResultDTO dto) {

        List<AnsweredQuestionDTO> answeredQuestionDTOS = dto.getQuestionsList();

        return (answeredQuestionDTOS.
                stream().
                mapToDouble(this::getQuestionsPercent).
                sum() / answeredQuestionDTOS.size()) * 100;
    }

    @Override
    public double getQuestionsPercent(AnsweredQuestionDTO dto) {
        Question question = new Question();
        question.setId(Long.parseLong(dto.getId()));

        double answersCount = 0;
        double correctAnswersCount = 0;
        double userCorrectAnswersCount = 0;
        double userWrongAnswersCount = 0;

        List<Answer> questionAnswers = answerRepository.findByQuestion(question);
        List<Answer> correctQuestionAnswers = questionAnswers.
                stream().
                filter(Answer::getIsCorrect).
                collect(Collectors.toList());

        answersCount = questionAnswers.size();
        correctAnswersCount = correctQuestionAnswers.size();

        for (SessionQuestionAnswer answer : dto.answersList) {
            if (answer.isSelected() && contain(answer.getId(), correctQuestionAnswers)) {
                userCorrectAnswersCount++;
            } else if (answer.isSelected() && !contain(answer.getId(), correctQuestionAnswers)) {
                userWrongAnswersCount++;
            }
        }

        if (correctAnswersCount == 1 && userWrongAnswersCount == 0) {
            return 1;
        } else if (correctAnswersCount == 1 && userWrongAnswersCount != 0) {
            return 0;
        } else {
            return Math.max(0, userCorrectAnswersCount / correctAnswersCount -
                    userWrongAnswersCount /
                            (answersCount - correctAnswersCount));
        }
    }

    @Override
    public Session saveSessionInfo(SessionResultDTO dto, double percent) {
        Session session = new Session(dto.getName(), percent, new Date());
        session = sessionRepository.save(session);

        saveSelectedAnswers(session, dto.getQuestionsList());
        return session;
    }

    @Override
    public boolean contain(String id, List<Answer> correctAnswers) {
        return correctAnswers.stream().anyMatch(answer -> answer.getId() == Long.parseLong(id));
    }

    @Override
    public void saveSelectedAnswers(Session session, List<AnsweredQuestionDTO> answeredQuestionDTOS) {
        for (AnsweredQuestionDTO answeredQuestionDTO : answeredQuestionDTOS) {
            for (SessionQuestionAnswer sessionQuestionAnswer : answeredQuestionDTO.answersList) {

                if (sessionQuestionAnswer.isSelected()) {
                    Answer answer = new Answer();
                    answer.setId(Long.parseLong(sessionQuestionAnswer.getId()));

                    Selected_Answer selected_answer = new Selected_Answer(answer, session);
                    selected_answerRepository.save(selected_answer);
                }
            }
        }
    }


}
