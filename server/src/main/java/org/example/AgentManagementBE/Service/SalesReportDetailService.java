package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.SalesReport;
import org.example.AgentManagementBE.Model.SalesReportDetail;
import org.example.AgentManagementBE.Model.Agent;
import org.example.AgentManagementBE.Model.ExportReceipt;
import org.example.AgentManagementBE.Repository.SalesReportRepository;
import org.example.AgentManagementBE.Repository.SalesReportDetailRepository;
import org.example.AgentManagementBE.Repository.AgentRepository;
import org.example.AgentManagementBE.Repository.ExportReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesReportDetailService {
    private final SalesReportDetailRepository salesReportDetailRepository;
    private final ExportReceiptRepository exportReceiptRepository;
    private final SalesReportRepository salesReportRepository;
    private final AgentRepository agentRepository;

    @Autowired
    public SalesReportDetailService(SalesReportDetailRepository salesReportDetailRepository,
                         ExportReceiptRepository exportReceiptRepository,
                         SalesReportRepository salesReportRepository,
                         AgentRepository agentRepository) {
        this.salesReportDetailRepository = salesReportDetailRepository;
        this.exportReceiptRepository = exportReceiptRepository;
        this.salesReportRepository = salesReportRepository;
        this.agentRepository = agentRepository;
    }

    public List<SalesReportDetail> getSalesReportDetailByAgentId(int agentID) {
        try {
            List<SalesReportDetail> details = salesReportDetailRepository.getSalesReportDetailByAgentID(agentID);
            if (details == null || details.isEmpty()) {
                throw new RuntimeException("Không tìm thấy chi tiết báo cáo cho agent ID: " + agentID);
            }
            return details;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy chi tiết báo cáo theo agent: " + e.getMessage());
        }
    }

    public SalesReportDetail getSalesReportDetailByAgentIdAndSalesReportId(int agentID, int salesReportID) {
        try {
            SalesReportDetail detail = salesReportDetailRepository.getSalesReportDetailByAgentIDAndSalesReportID(agentID, salesReportID);
            if (detail == null) {
                throw new RuntimeException("Không tìm thấy chi tiết báo cáo cho agent ID: " + agentID + " và sales report ID: " + salesReportID);
            }
            return detail;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy chi tiết báo cáo: " + e.getMessage());
        }
    }

    @Transactional
    public List<SalesReportDetail> createSalesReportDetail(int month, int year) {
        try {
            SalesReport salesReport = salesReportRepository.getSalesReportByMonthAndYear(month, year);
            if (salesReport == null) {
                throw new RuntimeException("Không tìm thấy báo cáo doanh số cho tháng " + month + "/" + year);
            }

            List<Agent> agentList = agentRepository.getAllAgentIdAndName();
            if (agentList == null || agentList.isEmpty()) {
                throw new RuntimeException("Không tìm thấy agent nào");
            }

            int totalMoney = exportReceiptRepository.getTotalMoneyByMonthAndYear(month, year);
            List<SalesReportDetail> salesReportDetailList = new ArrayList<>();

            for (Agent agent : agentList) {
                Integer sum1 = exportReceiptRepository.getTotalMoneyByMonthAndYearOfAgent(agent.getAgentID(), month, year);
                if (sum1 == null) {
                    sum1 = 0;
                }
                double proportion = totalMoney > 0 ? (double) sum1 / totalMoney : 0;
                
                SalesReportDetail salesReportDetail = new SalesReportDetail(
                    agent,
                    salesReport,
                    exportReceiptRepository.getQuantityExportByMonthAndYearOrderByAgentID(month, year, agent.getAgentID()),
                    sum1,
                    proportion
                );
                salesReportDetailRepository.save(salesReportDetail);
                salesReportDetailList.add(salesReportDetail);
            }
            return salesReportDetailList;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo chi tiết báo cáo: " + e.getMessage());
        }
    }

    @Transactional
    public List<SalesReportDetail> updateSalesReportDetail(int month, int year) {
        try {
            SalesReport salesReport = salesReportRepository.getSalesReportByMonthAndYear(month, year);
            if (salesReport == null) {
                throw new RuntimeException("Không tìm thấy báo cáo doanh số cho tháng " + month + "/" + year);
            }

            List<SalesReportDetail> salesReportDetailList = salesReportDetailRepository.getSalesReportDetailBySalesReportID(salesReport.getSalesReportID());
            if (salesReportDetailList == null || salesReportDetailList.isEmpty()) {
                throw new RuntimeException("Không tìm thấy chi tiết báo cáo để cập nhật");
            }

            int totalMoney = exportReceiptRepository.getTotalMoneyByMonthAndYear(month, year);
            for (SalesReportDetail salesReportDetail : salesReportDetailList) {
                Integer sum1 = exportReceiptRepository.getTotalMoneyByMonthAndYearOfAgent(salesReportDetail.getAgentID().getAgentID(), month, year);
                if (sum1 == null) {
                    sum1 = 0;
                }
                double proportion = totalMoney > 0 ? (double) sum1 / totalMoney : 0;
                
                salesReportDetail.setQuantityExport(exportReceiptRepository.getQuantityExportByMonthAndYearOrderByAgentID(month, year, salesReportDetail.getAgentID().getAgentID()));
                salesReportDetail.setTotalValue(sum1);
                salesReportDetail.setProportion(proportion);
                salesReportDetailRepository.save(salesReportDetail);
            }
            return salesReportDetailList;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật chi tiết báo cáo: " + e.getMessage());
        }
    }

    public List<SalesReportDetail> getSalesReportDetail(int month, int year) {
        try {
            SalesReport salesReport = salesReportRepository.getSalesReportByMonthAndYear(month, year);
            if (salesReport == null) {
                throw new RuntimeException("Không tìm thấy báo cáo doanh số cho tháng " + month + "/" + year);
            }
            
            List<Agent> agentList = agentRepository.getAllAgent();
            if (agentList == null || agentList.isEmpty()) {
                throw new RuntimeException("Không tìm thấy agent nào");
            }
            
            List<SalesReportDetail> salesReportDetailList = new ArrayList<>();
            int totalMoney = exportReceiptRepository.getTotalMoneyByMonthAndYear(month, year);
            
            for (Agent agent : agentList) {
                Integer sum1 = exportReceiptRepository.getTotalMoneyByMonthAndYearOfAgent(agent.getAgentID(), month, year);
                if (sum1 == null) {
                    sum1 = 0;
                }
                double proportion = totalMoney > 0 ? (double) sum1 / totalMoney : 0;
                
                SalesReportDetail salesReportDetail = new SalesReportDetail(
                    agent,
                    salesReport,
                    exportReceiptRepository.getQuantityExportByMonthAndYearOrderByAgentID(month, year, agent.getAgentID()),
                    sum1,
                    proportion
                );
                salesReportDetailRepository.save(salesReportDetail);
                salesReportDetailList.add(salesReportDetail);
            }
            
            return salesReportDetailList;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy chi tiết báo cáo: " + e.getMessage());
        }
    }
}
