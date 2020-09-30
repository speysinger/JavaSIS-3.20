package com.github.siberianintegrationsystems.restApp.controller;

import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionResultDTO;
import com.github.siberianintegrationsystems.restApp.service.journal.session.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/session")
public class SessionRestController {
    private final SessionService sessionService;

    public SessionRestController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("")
    public String getTestResults(@RequestBody SessionResultDTO dto) {
        double percent = sessionService.getPercent(dto).doubleValue();
        sessionService.saveSessionInfo(dto, percent);
        return String.valueOf(percent);
    }

    @GetMapping("questions-new")
    public List<QuestionsItemDTO> getNewQuestions() {
        return sessionService.getQuestions();
    }
}
