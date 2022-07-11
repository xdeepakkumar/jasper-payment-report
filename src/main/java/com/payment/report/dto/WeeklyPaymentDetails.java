package com.payment.report.dto;

import java.util.ArrayList;
import java.util.List;

public class WeeklyPaymentDetails {
    private String date;
    List<DailyPaymentDetails> dailyPaymentDetailsList = new ArrayList<>();
}
