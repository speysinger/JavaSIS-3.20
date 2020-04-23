package com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS;

import lombok.Getter;

import java.util.List;

@Getter
public class JournalResultDTO {
    public int total;
    public List<? extends JournalItemDTO> items;

    public JournalResultDTO(int total, List<? extends JournalItemDTO> item) {
        this.total = total;
        this.items = item;
    }
}
