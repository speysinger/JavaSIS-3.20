package com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS;

import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalItemDTO;

import java.util.Date;

public class SessionItemDTO extends JournalItemDTO {
    public String name;
    public Date insertDate;
    public Number result;

    public SessionItemDTO(String name, Date insertDate, Number result) {
        this.name = name;
        this.insertDate = insertDate;
        this.result = result;
    }
}
