package com.payment.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyPaymentDetails {
    private String claimNumber;
    private Double paidAmount;
}