package com.github.siberianintegrationsystems.restApp.service.journal;

import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalFilterItem;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalRequestDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalResultDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.entity.Journal;

import java.util.List;
import java.util.function.Predicate;

public interface JournalService {
    Journal getJournalEntity(String id);

    Predicate<QuestionsItemDTO> applyFilters(List<JournalFilterItem> filterItems);

    JournalResultDTO getJournal(String id, JournalRequestDTO req);
}
