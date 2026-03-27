package com.crictpredict.predictbe.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String utrId;

    private String amount;

    private String matchName;

    private String matchDate;

    private String status; // PENDING / VERIFIED

    private LocalDateTime createdAt;
}