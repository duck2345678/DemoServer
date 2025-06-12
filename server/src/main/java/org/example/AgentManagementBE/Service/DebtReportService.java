package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.DebtReport;
import org.example.AgentManagementBE.Model.DebtReportID;
import org.example.AgentManagementBE.Model.Agent;
import org.example.AgentManagementBE.Repository.DebtReportRepository;
import org.example.AgentManagementBE.Repository.AgentRepository;
import org.example.AgentManagementBE.Repository.ExportReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DebtReportService {
    private final DebtReportRepository debtReportRepository;
    private final AgentRepository agentRepository;
    private final ExportReceiptRepository exportReceiptRepository;

    @Autowired
    public DebtReportService(DebtReportRepository debtReportRepository,
                         AgentRepository agentRepository, 
                         ExportReceiptRepository exportReceiptRepository) {
        this.debtReportRepository = debtReportRepository;
        this.agentRepository = agentRepository;
        this.exportReceiptRepository = exportReceiptRepository;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void autoCreateDebtReport() {
        List<Agent> agentList = agentRepository.getAllAgent();
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();
        List<DebtReport> debtReportList = new ArrayList<>();
        for (Agent agent : agentList) {
            DebtReport debtReport = new DebtReport(
                    new DebtReportID(currentMonth, currentYear, agent),
                    0,
                    0,
                    0);

            debtReportList.add(debtReport);
        }

        debtReportRepository.saveAll(debtReportList);
    }

    public List<DebtReport> getDebtReport(int month, int year) {
        List<DebtReport> debtReportList = new ArrayList<>();
        List<Agent> agentList = agentRepository.getAllAgent();
        for (Agent agent : agentList) { 
            DebtReport debtReport = debtReportRepository.getDebtReportByAgent(month, year, agent.getAgentID());
            if (debtReport == null) {
                debtReport = new DebtReport(
                        new DebtReportID(month, year, agent),
                        0,
                        0,
                        0);
            } else {
                updateFirstDebt(debtReport);
                updateLastDebt(debtReport);
            }
            debtReportList.add(debtReport);
        }
        return debtReportList;
    }

    public void updateFirstDebt(DebtReport debtReport) {
        DebtReport existingDebtReport = debtReportRepository.getDebtReportByAgent(debtReport.getDebtReportID().getMonth() - 1, debtReport.getDebtReportID().getYear(), debtReport.getDebtReportID().getAgentID().getAgentID());
        Integer firstDebt = 0;
        if (existingDebtReport != null) {
            firstDebt = existingDebtReport.getLastDebt();
        }
        debtReport.setFirstDebt(firstDebt);
    }

    public void updateLastDebt(DebtReport debtReport) {
        Agent agent = agentRepository.getAgentById(debtReport.getDebtReportID().getAgentID().getAgentID());
        debtReport.setLastDebt(agent.getDebtMoney());
    }

    public DebtReport createDebtReport(DebtReport debtReport) {
        return debtReportRepository.save(debtReport);
    }
}
