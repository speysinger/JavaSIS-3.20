package com.github.siberianintegrationsystems.restApp;

import com.github.siberianintegrationsystems.restApp.data.JournalRepository;
import com.github.siberianintegrationsystems.restApp.entity.Journal;
import com.github.siberianintegrationsystems.restApp.service.journal.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RestAppApplication {

    @Autowired
    private JournalRepository journalRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestAppApplication.class, args);
    }

    @PostConstruct
    private void initData() {
        Journal journal = new Journal();
        journal.setId(JournalServiceImpl.QUESTIONS_JOURNAL_ID);
        journal.setName("Вопросы");
        journal.setDefaultPageSize(15L);
        journalRepository.save(journal);

        Journal journal2 = new Journal();
        journal2.setId(JournalServiceImpl.SESSIONS_JOURNAL_ID);
        journal2.setName("Сессии");
        journal2.setDefaultPageSize(15L);
        journalRepository.save(journal2);
    }
}
