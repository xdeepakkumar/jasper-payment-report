package com.payment.report.repository;

import com.payment.report.entity.GiganetPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentReportRepository extends JpaRepository<GiganetPayment, Long> {
    /**
     * @Description find payment by date
     * @param fromDate as String
     * @return GiganetPayment
     */
    List<GiganetPayment> findAllByCreatedOn(String fromDate);
}
