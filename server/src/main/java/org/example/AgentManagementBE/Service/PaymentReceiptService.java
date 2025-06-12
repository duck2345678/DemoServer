package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.DebtReport;
import org.example.AgentManagementBE.Model.Agent;
import org.example.AgentManagementBE.Model.PaymentReceipt;
import org.example.AgentManagementBE.Repository.DebtReportRepository;
import org.example.AgentManagementBE.Repository.AgentRepository;
import org.example.AgentManagementBE.Repository.PaymentReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PaymentReceiptService {
    private final PaymentReceiptRepository paymentReceiptRepository;
    private final AgentRepository agentRepository;
    private final AgentService agentService;
    private final DebtReportRepository debtReportRepository;

    public PaymentReceiptService(PaymentReceiptRepository paymentReceiptRepository, AgentRepository agentRepository, AgentService agentService, DebtReportRepository debtReportRepository) {
        this.paymentReceiptRepository = paymentReceiptRepository;
        this.agentRepository = agentRepository;
        this.agentService = agentService;
        this.debtReportRepository = debtReportRepository;
    }

    public Iterable<PaymentReceipt> getAllPaymentReceipt() {
        try {
            return paymentReceiptRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all payment receipts: " + e.getMessage());
        }
    }

    public Iterable<PaymentReceipt> getPaymentReceiptByID(int paymentReceiptId) {
        try {
            return paymentReceiptRepository.getPaymentReceiptByID(paymentReceiptId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving payment receipt by ID: " + e.getMessage());
        }
    }

    public Iterable<PaymentReceipt> getPaymentReceiptByAgentID(int agentId) {
        try {
            return paymentReceiptRepository.getPaymentReceiptByAgentID(agentId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving payment receipts by agent ID: " + e.getMessage());
        }
    }

    public boolean insertPaymentReceipt(PaymentReceipt paymentReceipt) {
        try {
            if (paymentReceipt == null) {
                throw new IllegalArgumentException("Payment receipt cannot be null");
            }

            if (paymentReceipt.getAgentID() == null) {
                throw new IllegalArgumentException("Agent ID is required");
            }

            if (paymentReceipt.getRevenue() <= 0) {
                throw new IllegalArgumentException("Payment amount must be greater than 0");
            }

            // Get existing agent from database
            Agent existingAgent = agentRepository.getAgentById(paymentReceipt.getAgentID().getAgentID());
            if (existingAgent == null) {
                throw new IllegalArgumentException("Agent not found");
            }

            int paymentAmount = paymentReceipt.getRevenue();
            int oldDebtMoney = existingAgent.getDebtMoney();
            
            if (oldDebtMoney < paymentAmount) {
                throw new IllegalArgumentException("Payment amount exceeds current debt");
            }

            // Update agent's debt
            existingAgent.setDebtMoney(oldDebtMoney - paymentAmount);
            agentRepository.save(existingAgent);

            // Save payment receipt
            paymentReceiptRepository.save(paymentReceipt);

            // Update debt report
            Date receiptDate = paymentReceipt.getPaymentDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(receiptDate);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            DebtReport existingDebtReport = debtReportRepository.getDebtReportByAgent(
                month, year, paymentReceipt.getAgentID().getAgentID());
            
            if (existingDebtReport != null) {
                int oldArisenDebt = existingDebtReport.getArisenDebt();
                int newArisenDebt = oldArisenDebt - paymentReceipt.getRevenue();
                existingDebtReport.setArisenDebt(newArisenDebt);
                debtReportRepository.save(existingDebtReport);
            }

            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error creating payment receipt: " + e.getMessage());
        }
    }
}
