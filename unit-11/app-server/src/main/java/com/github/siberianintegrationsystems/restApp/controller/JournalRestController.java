package com.github.siberianintegrationsystems.restApp.controller;

import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalEntityDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalRequestDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalResultDTO;
import com.github.siberianintegrationsystems.restApp.service.journal.JournalService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/journal")
public class JournalRestController {

    private final JournalService journalService;

    public JournalRestController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("{id}")
    public JournalEntityDTO getJournalEntity(@PathVariable String id){
        return new JournalEntityDTO(journalService.getJournalEntity(id));
    }

    @PutMapping("{id}/rows")
    public JournalResultDTO getJournal(@PathVariable String id,
                                    @RequestBody JournalRequestDTO req) {

        return journalService.getJournal(id, req);
    }
}
