package com.github.siberianintegrationsystems.restApp.service.journal;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalFilterItem;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalRequestDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.journalDTOS.JournalResultDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.questionDTOS.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.sessionDTOS.SessionItemDTO;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.JournalRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.data.SessionRepository;
import com.github.siberianintegrationsystems.restApp.entity.BaseEntity;
import com.github.siberianintegrationsystems.restApp.entity.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    public static final String QUESTIONS_JOURNAL_ID = "questions";
    public static final String SESSIONS_JOURNAL_ID = "sessions";

    private final JournalRepository journalRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SessionRepository sessionRepository;

    public JournalServiceImpl(JournalRepository journalRepository,
                              QuestionRepository questionRepository,
                              AnswerRepository answerRepository, SessionRepository sessionRepository) {
        this.journalRepository = journalRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Journal getJournalEntity(String id) {
        return journalRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Predicate<QuestionsItemDTO> applyFilters(List<JournalFilterItem> filterItems) {
        return questionsItemDTO -> {
            boolean saveItem = true;
            try {
                for (JournalFilterItem journalFilterItem : filterItems) {
                    switch (journalFilterItem.type) {
                        case "single-select":
                            if (questionsItemDTO.answers.size() != Integer.parseInt(journalFilterItem.value)) {
                                saveItem = false;
                            }
                            break;
                        default:
                            saveItem = true;
                            break;
                    }
                }
            } catch (NumberFormatException ignored) {
            }
            return saveItem;
        };

    }

    @Override
    public JournalResultDTO getJournal(
            String id, JournalRequestDTO req) {

        PageRequest pageRequest = PageRequest.of((req.page - 1), req.pageSize);

        List<? extends JournalItemDTO> collection;
        switch (id) {
            case QUESTIONS_JOURNAL_ID:
                collection = getCollection(
                        req.search,
                        questionRepository::findByNameContainingIgnoreCase,
                        q -> new QuestionsItemDTO(
                                q,
                                answerRepository.findByQuestion(q))).
                        stream().
                        filter(applyFilters(req.filters)).
                        collect(Collectors.toList());
                break;

            case SESSIONS_JOURNAL_ID:
                collection = getCollection(
                        req.search,
                        sessionRepository::findByNameContainingIgnoreCase,
                        s -> new SessionItemDTO(s.getName(),
                                s.getDate(),
                                s.getPercent()
                        ));
                break;
            default:
                throw new RuntimeException();
        }
        int itemsCount = collection.size();

        return new JournalResultDTO(itemsCount, getPageInsides(pageRequest, collection));
    }

    private <T extends BaseEntity, U extends JournalItemDTO> List<U> getCollection(
            String search,
            Function<String, List<T>> finder,
            Function<T, U> mapper
    ) {
        return finder.apply(search)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    private <U extends JournalItemDTO> List<U> getPageInsides(
            Pageable pageRequest,
            List<U> items
    ) {
        long start = pageRequest.getOffset();
        long end = (start + pageRequest.getPageSize()) > items.size() ? items.size() : (start + pageRequest.getPageSize());

        Page<U> page = new PageImpl<>(items.subList((int) start, (int) end), pageRequest, items.size());
        return page.getContent();
    }

}
