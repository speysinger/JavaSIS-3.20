package com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS;

import com.github.siberianintegrationsystems.restApp.entity.Journal;

public class JournalEntityDTO {
    public String id;
    public String name;
    public Long defaultPageSize;

    public JournalEntityDTO(Journal journal) {
        this.id = journal.getId();
        this.name = journal.getName();
        this.defaultPageSize = journal.getDefaultPageSize();
    }
}
