package com.payment.report.controller;

import com.payment.report.common.ReportConstants;
import com.payment.report.dto.ReportRequestDto;
import com.payment.report.service.PaymentReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

/**
 * @Title Payment Controller
 * @Description controller for the daily report and weekly report using jasper template, Jasper template is available in
 *              resources/report folder.
 * @Date 07/07/2020
 * @Author Deepak Kumar
 */

@RestController
@Slf4j
@RequestMapping("/reports")
public class PaymentReportController {

    /* logger **/
    public Logger logger = LoggerFactory.getLogger(PaymentReportController.class);

    /* PaymentReportService **/
    @Autowired
    private PaymentReportService paymentReportService;

    /**
     * @Description Method to generate report for both daily and weekly
     * @param requestDto as object of ReportRequestDto
     * @return as ResponseEntity
     */
    @GetMapping("/payment-report")
    public ResponseEntity<String> dailyPaymentReport(@RequestBody ReportRequestDto requestDto) throws JRException, FileNotFoundException {
        logger.info("daily payment report request received.");
        String response = paymentReportService.getPaymentReport(requestDto);
        if(response.equalsIgnoreCase(ReportConstants.SUCCESS)){
            return ResponseEntity.ok(ReportConstants.REPORT_SUCCESS_MESSAGE);
        }else {
            return ResponseEntity.internalServerError().body(ReportConstants.ERROR);
        }
    }

}
