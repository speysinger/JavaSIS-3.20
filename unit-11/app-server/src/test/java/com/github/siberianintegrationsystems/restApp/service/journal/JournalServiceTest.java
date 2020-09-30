package com.github.siberianintegrationsystems.restApp.service.journal;

import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalRequestDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalResultDTO;
import com.github.siberianintegrationsystems.restApp.data.JournalRepository;
import com.github.siberianintegrationsystems.restApp.entity.Journal;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
public class JournalServiceTest {

    @Autowired
    private JournalService journalService;

    @Autowired
    private JournalRepository journalRepository;

    @Test
    public void getJournalEntity() {
        Journal journal = journalRepository.findById("sessions").orElseThrow(RuntimeException::new);
        Assert.assertEquals("Сессии", journal.getName());
    }

    @Test
    public void getJournal() {
        String id = "questions";
        JournalRequestDTO journalRequestDTO = new JournalRequestDTO("2+2", 1, 1, Collections.emptyList());
        JournalResultDTO journalResultDTO = journalService.getJournal(id, journalRequestDTO);

        Assert.assertEquals(1, journalResultDTO.getItems().size());
    }
}