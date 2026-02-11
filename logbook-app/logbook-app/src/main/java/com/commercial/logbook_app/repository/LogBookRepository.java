package com.commercial.logbook_app.repository;

import com.commercial.logbook_app.model.LogBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogBookRepository extends JpaRepository<LogBook, Integer> {
}
