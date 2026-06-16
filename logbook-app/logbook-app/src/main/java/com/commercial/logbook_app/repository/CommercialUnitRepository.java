package com.commercial.logbook_app.repository;

import com.commercial.logbook_app.model.CommercialUnit;
import com.commercial.logbook_app.model.enums.CommercialUnitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommercialUnitRepository extends JpaRepository<CommercialUnit, Integer> {
    List<CommercialUnit> findByStatus(CommercialUnitStatus status);
}
