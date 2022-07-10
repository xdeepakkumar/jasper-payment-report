package com.payment.report.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Request params from UI
 * @Author Deepak Kumar
 * @Date 07/07/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {

    @JsonProperty("toDate")
    private String toDate;
    @JsonProperty("fromDate")
    private String fromDate;
    @JsonProperty("formatType")
    private String formatType;
    @JsonProperty("reportType")
    private String reportType;

}
