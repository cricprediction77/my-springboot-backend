package com.crictpredict.predictbe.service;

import com.crictpredict.predictbe.entity.PaymentDetails;
import com.crictpredict.predictbe.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void savePayment(PaymentDetails payment, String userIp) {

        // 🔥 CHECK DUPLICATE UTR
        if (paymentRepository.existsByUtrId(payment.getUtrId())) {
            throw new RuntimeException("UTR_ALREADY_EXISTS");
        }

        // 🔥 TERMS VALIDATION
        if (!Boolean.TRUE.equals(payment.getTermsAccepted())) {
            throw new RuntimeException("TERMS_NOT_ACCEPTED");
        }

        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setTermsAcceptedAt(LocalDateTime.now());

        // ✅ SAVE USER IP
        payment.setUserIp(userIp);

        paymentRepository.save(payment);
    }

    public List<PaymentDetails> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void markAsVerified(Long id) {
        PaymentDetails payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus("VERIFIED");
        paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}