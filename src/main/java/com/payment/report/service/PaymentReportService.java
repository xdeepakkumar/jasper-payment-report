package com.payment.report.service;

import com.payment.report.dto.ReportRequestDto;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

/**
 * @Description Service to generate both daily and weekly report
 * @Date 07/07/2022
 * @Author Deepak Kumar
 */
public interface PaymentReportService {

    /**
     * @Description Service to generate report
     * @param requestDto as Request
     * @return as String message
     */
    String getPaymentReport(ReportRequestDto requestDto) throws JRException, FileNotFoundException;
}
