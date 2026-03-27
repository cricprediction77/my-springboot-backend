package com.crictpredict.predictbe.controller;

import com.crictpredict.predictbe.entity.PaymentDetails;
import com.crictpredict.predictbe.service.PaymentService;
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

    // ✅ Save payment
    @PostMapping
    public ResponseEntity<?> savePayment(@RequestBody PaymentDetails payment) {
        try {
            paymentService.savePayment(payment);
            return ResponseEntity.ok("Payment submitted successfully ✅");
        } catch (RuntimeException e) {

            if ("UTR_ALREADY_EXISTS".equals(e.getMessage())) {
                return ResponseEntity.badRequest()
                        .body("This UTR is already registered with another user ❌");
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
}