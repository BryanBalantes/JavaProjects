package com.commercial.logbook_app.repository;

import com.commercial.logbook_app.model.CommercialUnit;
import com.commercial.logbook_app.model.Lease;
import com.commercial.logbook_app.model.enums.LeaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Integer> {
    List<Lease> findByUser_Id(int userId);

    List<Lease> findByCommercialUnitAndStatus(CommercialUnit commercialUnit, LeaseStatus status);

    boolean existsByUser_IdAndStatus(int userId, LeaseStatus status);
}
