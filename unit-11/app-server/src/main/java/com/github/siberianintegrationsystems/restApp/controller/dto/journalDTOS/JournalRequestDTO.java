package com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS;

import java.util.List;

public class JournalRequestDTO {
    public String search;
    public int page;
    public int pageSize;
    public List<JournalFilterItem> filters;

    public JournalRequestDTO() {

    }

    public JournalRequestDTO(String search, int page, int pageSize, List<JournalFilterItem> filters)
    {
        this.search = search;
        this.page = page;
        this.pageSize = pageSize;
        this.filters = filters;
    }
}
