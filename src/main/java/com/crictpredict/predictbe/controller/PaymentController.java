package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.entity.PaymentDetails;
import com.crictpredict.predictbe.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> savePayment(
            @RequestBody PaymentDetails payment,
            HttpServletRequest request
    ) {
        try {
            String ip = getClientIp(request);

            paymentService.savePayment(payment, ip);

            return ResponseEntity.ok("Payment submitted successfully ✅");

        } catch (RuntimeException e) {

            if ("UTR_ALREADY_EXISTS".equals(e.getMessage())) {
                return ResponseEntity.badRequest()
                        .body("This UTR is already registered ❌");
            }

            if ("TERMS_NOT_ACCEPTED".equals(e.getMessage())) {
                return ResponseEntity.badRequest()
                        .body("Please accept Terms & Conditions ❗");
            }

            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    // ✅ Get all payments (Admin)
    @GetMapping
    public List<PaymentDetails> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // ✅ Mark as verified
    @PutMapping("/{id}/verify")
    public String verifyPayment(@PathVariable Long id) {
        paymentService.markAsVerified(id);
        return "Payment verified ✅";
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "Payment deleted ❌";
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0];
        }

        ip = request.getHeader("X-Real-IP");
        if (ip != null && !ip.isEmpty()) {
            return ip;
        }

        return request.getRemoteAddr();
    }
}