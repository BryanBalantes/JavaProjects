package com.commercial.logbook_app.repository;

import com.commercial.logbook_app.model.TenantPaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenantPaymentRecordRepository extends JpaRepository<TenantPaymentRecord, Integer> {

//    // Get all records of a user
//    List<TenantPaymentRecord> findByAssignee_Id(int userId);

    @Query("SELECT r FROM TenantPaymentRecord r WHERE r.assignee.id = :userId")
    List<TenantPaymentRecord> findByUserId(@Param("userId") int userId);

    // Get records by user + status
    List<TenantPaymentRecord> findByAssignee_IdAndStatus(int userId, String status);
}