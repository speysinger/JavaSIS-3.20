package com.github.siberianintegrationsystems.restApp.data;

import com.github.siberianintegrationsystems.restApp.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, String> {

    Page<Session> findByNameContainingIgnoreCase(String search, Pageable pageable);
}
