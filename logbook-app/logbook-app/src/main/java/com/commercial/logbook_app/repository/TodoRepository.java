package com.commercial.logbook_app.repository;

import com.commercial.logbook_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {}
