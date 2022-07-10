package com.payment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @Description Entity class for the payment data
 * @Date 07/07/2022
 * @Author Deepak Kumar
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "giganet_payment")
public class GiganetPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @NotNull
    private Long outputIndex;
    @NotBlank
    @NotNull
    private UUID transactionId;
    private UUID activityUUID;
    private UUID batchUUID;
    private String claimNumber;
    private UUID claimUUID;
    private String createdBy;
    private String createdByParty;
    private String createdOn;
    private String date;
    private String description;
    private String modifiedBy;
    private String modifiedOn;
    private String modifiedByParty;
    private Double originalBalance;
    private Double paidAmount;
    private Long sequence;
    private Double totalPaid;
    private String type;
    private UUID paymentUUID;
    private String actualPaymentReceivedDate;
    private String approvedBy;
    private String approvedDate;
    private String bankName;
    private String chequeAccountHolderName;
    private String chequeOrCCAccountNo;
    private String chequeOrCCDueDate;
    private String chequeOrCCNo;
    private String chequeOrCCProcessingDate;
    private String chequeOrCCReceivedDate;
    private String client;
    private String details;
    private String gatewayStatus;
    private String merchant;
    private Double netPaymentAfterDeduction;
    private Long paymentBatchId;
    private String paymentMode;
    private String paymentTransactionId;
    private String paymentTxnId;
    private boolean processed;
    private String processedDate;
    private String recoveryAgent;
    private String settledDate;
    private String status;
    private String subordinate;
    private Double totalVendorFee;
    private String vendor;
    private String vendorPaymentProcessingDueDate;
    private String vendorPaymentType;
    private UUID documentUUID;


}
