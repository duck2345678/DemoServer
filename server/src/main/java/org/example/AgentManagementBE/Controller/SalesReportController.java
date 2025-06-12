package org.example.AgentManagementBE.Controller;

import org.example.AgentManagementBE.Model.SalesReport;
import org.example.AgentManagementBE.Model.SalesReportDetail;
import org.example.AgentManagementBE.Service.SalesReportService;
import org.example.AgentManagementBE.Service.SalesReportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salesReport")
public class SalesReportController {
    @Autowired
    private final SalesReportService salesReportService;

    @Autowired
    private final SalesReportDetailService salesReportDetailService;

    public SalesReportController(SalesReportService salesReportService, SalesReportDetailService salesReportDetailService) {
        this.salesReportService = salesReportService;
        this.salesReportDetailService = salesReportDetailService;
    }

    @GetMapping("/getSalesReportByMonthAndYear")
    public ResponseEntity<Map<String, Object>> getSalesReportByMonthAndYear(@RequestBody Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            int month = request.get("month");
            int year = request.get("year");

            if (month < 1 || month > 12) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Tháng không hợp lệ (phải từ 1-12)");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
            if (year < 2000 || year > 2100) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Năm không hợp lệ");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }

            List<SalesReportDetail> salesReportDetailList = salesReportDetailService.getSalesReportDetail(month, year);
            if (salesReportDetailList.isEmpty()) {
                salesReportDetailList = salesReportDetailService.createSalesReportDetail(month, year);
            }
            
            response.put("code", 200);
            response.put("status", "success");
            response.put("message", "Lấy báo cáo doanh số thành công");
            response.put("data", salesReportDetailList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/createSalesReport")
    public ResponseEntity<Map<String, Object>> createSalesReport(@RequestBody Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            int month = request.get("month");
            int year = request.get("year");

            if (month < 1 || month > 12) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Tháng không hợp lệ (phải từ 1-12)");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
            if (year < 2000 || year > 2100) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Năm không hợp lệ");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
            
            SalesReport createdReport = salesReportService.createSalesReport(month, year);
            if (createdReport != null) {
                response.put("code", 201);
                response.put("status", "success");
                response.put("message", "Tạo báo cáo doanh số thành công");
                response.put("data", createdReport);
                return ResponseEntity.status(201).body(response);
            } else {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Không thể tạo báo cáo doanh số");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping("/updateSalesReport")
    public ResponseEntity<Map<String, Object>> updateSalesReport(@RequestBody Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            int month = request.get("month");
            int year = request.get("year");

            if (month < 1 || month > 12) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Tháng không hợp lệ (phải từ 1-12)");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
            if (year < 2000 || year > 2100) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Năm không hợp lệ");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }

            SalesReport updatedReport = salesReportService.updateSalesReport(month, year);
            if (updatedReport != null) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Cập nhật báo cáo doanh số thành công");
                response.put("data", updatedReport);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy báo cáo doanh số để cập nhật");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }
}