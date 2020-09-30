package com.github.siberianintegrationsystems.restApp.controller;

import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.service.journal.question.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/question")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("create")
    public QuestionsItemDTO create(@RequestBody QuestionsItemDTO dto) {
        return questionService.createQuestion(dto);
    }

    @PutMapping("edit")
    public void edit(@RequestBody QuestionsItemDTO dto)
    {
        questionService.editQuestion(dto);
    }
}
