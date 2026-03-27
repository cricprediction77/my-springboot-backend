package com.crictpredict.predictbe.repository;

import com.crictpredict.predictbe.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {
    boolean existsByUtrId(String utrId);
}
