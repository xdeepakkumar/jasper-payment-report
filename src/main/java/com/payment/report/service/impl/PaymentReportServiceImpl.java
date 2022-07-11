package com.payment.report.service.impl;

import com.payment.report.common.ReportConstants;
import com.payment.report.dto.DailyPaymentDetails;
import com.payment.report.dto.ReportRequestDto;
import com.payment.report.entity.GiganetPayment;
import com.payment.report.repository.PaymentReportRepository;
import com.payment.report.service.PaymentReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description PaymentReportService implementation class
 * @Author Deepak Kumar
 * @Date 07/07/2022
 */
@Service
public class PaymentReportServiceImpl implements PaymentReportService {

    /* PaymentReportRepository **/
    @Autowired
    private PaymentReportRepository paymentReportRepository;

    /**
     * @Description Get the payment report service
     * @param requestDto as Request
     * @return as String message
     */
    @Override
    public String getPaymentReport(ReportRequestDto requestDto) throws JRException, FileNotFoundException {

        String reportType = requestDto.getReportType();

        /* checking the request type, either weekly or daily **/
        if(reportType.equalsIgnoreCase("daily")){
            /* Getting the data from database **/
            return generateDailyReport(requestDto);

        } else if (reportType.equalsIgnoreCase("weekly")) {

            List<GiganetPayment> paymentList = paymentReportRepository.findAll();
            return generateWeeklyReport(paymentList, requestDto);

        }else {
            return HttpStatus.BAD_REQUEST.toString();
        }
    }

    /**
     * @Description Generating daily payment report by static data, so allByDate(argument name) is empty as of now and
     *              not using in the function.
     *
     * @param requestDto as ReportRequestDto
     * @return message as String
     */
    private String generateDailyReport(ReportRequestDto requestDto) throws FileNotFoundException, JRException {

        try {

            String createdOn = requestDto.getFromDate();

            List<GiganetPayment> paymentByCreatedOn = paymentReportRepository.findAllByCreatedOn(createdOn);
            List<DailyPaymentDetails> paymentDetailsList = new ArrayList<>();
            AtomicReference<Double> total = new AtomicReference<>(0.0);

            paymentByCreatedOn.forEach(payment-> {
                DailyPaymentDetails dailyPaymentDetails = new DailyPaymentDetails();
                dailyPaymentDetails.setPaidAmount(payment.getPaidAmount());
                dailyPaymentDetails.setClaimNumber(payment.getClaimNumber());
                total.set(payment.getPaidAmount());
                paymentDetailsList.add(dailyPaymentDetails);
            });

            /* getting the template path **/
            /* ResourceUtil to get template from the resource **/
            File file = ResourceUtils.getFile("classpath:reports\\DailyPaymentReport.jrxml");

            /* getting the absolute path **/
            /* Compile file with jasperCompileManager and taking the path **/
            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());

            /* passing the data returned by database **/
            /* Mapping the fields with JRBeanCollectionDataSource **/
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paymentDetailsList);

            /* This is basically used to add some comment like created by or some other stuff like that **/
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "GigaForce Admin");
            parameters.put("dateOfPayment", requestDto.getFromDate());
            parameters.put("reportNumber", "1");
            parameters.put("total", total.toString());
            parameters.put("dataSet", dataSource);

            /* Printing the data by passing the reports and optional params with dataSource **/
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            /* now working on report file type and defining the path **/
            /* HTML File Type **/
            String path = "D:\\gigaforce 1\\jasper report\\reports\\daily_payment_report\\";
            if(requestDto.getFormatType().equalsIgnoreCase("html")){
                JasperExportManager.exportReportToHtmlFile(print, path + "dailyPaymentReport.html");
            }
            /* PDF file Type **/
            if(requestDto.getFromDate().equalsIgnoreCase("pdf")){
                JasperExportManager.exportReportToPdfFile(print, path + "dailyPaymentReport.pdf");
            }

            return  ReportConstants.SUCCESS;

        }catch (FileNotFoundException | JRException e){
           e.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(ReportConstants.SOMETHING_WENT_WRONG);
        }
        return ReportConstants.ERROR;
    }

    /**
     * @Description Generating daily payment report by static data, so allByDate(argument name) is empty as of now and
     *              not using in the function.
     *
     * @param allByDate as List<GiganetPayment>
     * @param  requestDto as ReportRequestDto
     * @return message as String
     */
    private String generateWeeklyReport(List<GiganetPayment> allByDate, ReportRequestDto requestDto) {

        return null;
    }
}
