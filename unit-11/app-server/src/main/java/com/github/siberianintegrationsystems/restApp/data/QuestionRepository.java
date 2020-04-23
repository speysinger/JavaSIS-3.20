package com.github.siberianintegrationsystems.restApp.data;

import com.github.siberianintegrationsystems.restApp.entity.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository
        extends CrudRepository<Question, Long> {

    List<Question> findByNameContainingIgnoreCase(String search);

    List<Question> findAll();

    @Modifying
    @Query("update Question t set t.name = :name where t.id = :id")
    void updateQuestion(@Param("id") Long id, @Param("name") String name);

}
