package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.*;
import org.example.AgentManagementBE.Repository.DebtReportRepository;
import org.example.AgentManagementBE.Repository.SalesReportRepository;
import org.example.AgentManagementBE.Repository.ExportReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;

@Service
public class ExportReceiptService {
    private final ExportReceiptRepository exportReceiptRepository;
    private final AgentService agentService;
    private final DebtReportRepository debtReportRepository;
    private final SalesReportRepository salesReportRepository;
    private final Calendar calendar;

    @Autowired
    public ExportReceiptService(ExportReceiptRepository exportReceiptRepository, 
                              AgentService agentService, 
                              DebtReportRepository debtReportRepository, 
                              SalesReportRepository salesReportRepository) {
        this.exportReceiptRepository = exportReceiptRepository;
        this.agentService = agentService;
        this.debtReportRepository = debtReportRepository;
        this.salesReportRepository = salesReportRepository;
        this.calendar = Calendar.getInstance();
    }

    public ExportReceipt getExportReceiptById(int exportReceiptId) {
        try {
            return exportReceiptRepository.getExportReceiptById(exportReceiptId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving export receipt: " + e.getMessage());
        }
    }

    public ExportReceipt getAllExportReceiptByExportDate(String dateReceipt) {
        try {
            return exportReceiptRepository.getAllExportReceiptByDateReceipt(dateReceipt);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving export receipt by date: " + e.getMessage());
        }
    }

    public int getQuantityExportByMonthAndYearOfExportDate(int month, int year) {
        try {
            return exportReceiptRepository.getQuantityExportByMonthAndYearOfDateReceipt(month, year);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving export quantity: " + e.getMessage());
        }
    }
    
    public int createExportReceipt(ExportReceipt newExportReceipt) {
        try {
            // Validate agent exists
            if (newExportReceipt.getAgentID() == null) {
                throw new IllegalArgumentException("Agent ID is required");
            }

            // Validate required fields
            if (newExportReceipt.getDateReceipt() == null) {
                throw new IllegalArgumentException("Date receipt is required");
            }

            if (newExportReceipt.getTotalMoney() <= 0) {
                throw new IllegalArgumentException("Total money must be greater than 0");
            }

            if (newExportReceipt.getPaymentAmount() < 0) {
                throw new IllegalArgumentException("Payment amount cannot be negative");
            }

            if (newExportReceipt.getRemainAmount() < 0) {
                throw new IllegalArgumentException("Remain amount cannot be negative");
            }

            // Validate total money equals payment + remain
            if (newExportReceipt.getTotalMoney() != (newExportReceipt.getPaymentAmount() + newExportReceipt.getRemainAmount())) {
                throw new IllegalArgumentException("Total money must equal payment amount plus remain amount");
            }
            
            // Check if updating debt book was successful
            ResponseEntity<?> debtUpdateResponse = agentService.updateDebtBook(
                newExportReceipt.getRemainAmount(), 
                newExportReceipt.getAgentID().getAgentID()
            );
            
            if (!debtUpdateResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to update debt book");
            }

            calendar.setTime(newExportReceipt.getDateReceipt());
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            updateArisenDebt(month, year, newExportReceipt.getRemainAmount(), newExportReceipt.getAgentID());
            updateSales(month, year, newExportReceipt.getTotalMoney());
            
            ExportReceipt savedReceipt = exportReceiptRepository.save(newExportReceipt);
            return savedReceipt.getExportReceiptID();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error creating export receipt: " + e.getMessage());
        }
    }   

    private void updateArisenDebt(int month, int year, int arisenDebt, Agent agentID) {
        try {
            DebtReport existingDebtReport = debtReportRepository.getDebtReportByAgent(month, year, agentID.getAgentID());
            if (existingDebtReport != null) {
                int oldArisenDebt = existingDebtReport.getArisenDebt();
                int newArisenDebt = oldArisenDebt + arisenDebt;
                existingDebtReport.setArisenDebt(newArisenDebt);
                debtReportRepository.save(existingDebtReport);
            } else {
                DebtReport newDebtReport = new DebtReport(new DebtReportID(month, year, agentID), 0, 0, arisenDebt);
                debtReportRepository.save(newDebtReport);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating arisen debt: " + e.getMessage());
        }
    }

    private void updateSales(int month, int year, int sales) {
        try {
            SalesReport existingSalesReport = salesReportRepository.getSalesReportByMonthAndYear(month, year);
            if (existingSalesReport != null) {
                int totalMoney = exportReceiptRepository.getTotalMoneyByMonthAndYear(month, year);    
                existingSalesReport.setTotalRevenue(totalMoney + sales);
                salesReportRepository.save(existingSalesReport);
            } else {
                salesReportRepository.save(new SalesReport(month, year, sales));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating sales: " + e.getMessage());
        }
    }
}
