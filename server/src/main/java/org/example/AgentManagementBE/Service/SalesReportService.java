package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.SalesReport;
import org.example.AgentManagementBE.Model.ExportReceipt;
import org.example.AgentManagementBE.Repository.SalesReportRepository;
import org.example.AgentManagementBE.Repository.ExportReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalesReportService {
    private final SalesReportRepository salesReportRepository;
    private final ExportReceiptRepository exportReceiptRepository;

    @Autowired
    public SalesReportService(SalesReportRepository salesReportRepository, ExportReceiptRepository exportReceiptRepository) {
        this.salesReportRepository = salesReportRepository;
        this.exportReceiptRepository = exportReceiptRepository;
    }

    @Transactional
    public SalesReport createSalesReport(int month, int year) {
        try {
            List<ExportReceipt> exportReceiptList = exportReceiptRepository.getAllExportReceiptByMonthAndYearOfDateReceipt(month, year);
            if (exportReceiptList == null || exportReceiptList.isEmpty()) {
                throw new RuntimeException("Không tìm thấy phiếu xuất nào trong tháng " + month + "/" + year);
            }

            SalesReport salesReport = new SalesReport(month, year);
            int totalMoney = exportReceiptRepository.getTotalMoneyByMonthAndYear(month, year);
            
            salesReport.setTotalRevenue(totalMoney);
            return salesReportRepository.save(salesReport);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo báo cáo doanh số: " + e.getMessage());
        }
    }

    @Transactional
    public SalesReport updateSalesReport(int month, int year) {
        try {
            SalesReport salesReport = salesReportRepository.getSalesReportByMonthAndYear(month, year);
            if (salesReport == null) {
                throw new RuntimeException("Không tìm thấy báo cáo doanh số cho tháng " + month + "/" + year);
            }

            List<ExportReceipt> exportReceiptList = exportReceiptRepository.getAllExportReceiptByMonthAndYearOfDateReceipt(month, year);
            if (exportReceiptList == null || exportReceiptList.isEmpty()) {
                throw new RuntimeException("Không tìm thấy phiếu xuất nào trong tháng " + month + "/" + year);
            }

            int totalMoney = exportReceiptRepository.getTotalMoneyByMonthAndYear(month, year);
            salesReport.setTotalRevenue(totalMoney);
            return salesReportRepository.save(salesReport);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật báo cáo doanh số: " + e.getMessage());
        }
    }
}