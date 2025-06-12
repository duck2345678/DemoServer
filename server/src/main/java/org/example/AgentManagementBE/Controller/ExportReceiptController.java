package org.example.AgentManagementBE.Controller;

import org.example.AgentManagementBE.Model.ExportReceipt;
import org.example.AgentManagementBE.Service.ExportReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/exportReceipt")
public class ExportReceiptController {
    private final ExportReceiptService exportReceiptService;

    @Autowired
    public ExportReceiptController(ExportReceiptService exportReceiptService) {
        this.exportReceiptService = exportReceiptService;
    }

    @GetMapping("/getExportReceiptById")
    public ResponseEntity<Map<String, Object>> getExportReceiptById(@RequestParam int exportReceiptId) {
        Map<String, Object> response = new HashMap<>();
        try {
            ExportReceipt exportReceipt = exportReceiptService.getExportReceiptById(exportReceiptId);
            if (exportReceipt != null) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Tìm thấy phiếu xuất hàng thành công");
                response.put("data", exportReceipt);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy phiếu xuất hàng");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tìm phiếu xuất hàng: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/getExportReceiptByDate")
    public ResponseEntity<Map<String, Object>> getExportReceiptsByDate(@RequestParam String dateReceipt) {
        Map<String, Object> response = new HashMap<>();
        try {
            ExportReceipt exportReceipt = exportReceiptService.getAllExportReceiptByExportDate(dateReceipt);
            if (exportReceipt != null) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Tìm thấy phiếu xuất hàng theo ngày thành công");
                response.put("data", exportReceipt);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy phiếu xuất hàng theo ngày");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tìm phiếu xuất hàng theo ngày: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/addExportReceipt")
    public ResponseEntity<Map<String, Object>> createExportReceipt(@RequestBody ExportReceipt newExportReceipt) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (newExportReceipt == null) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Dữ liệu phiếu xuất hàng không được để trống");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }

            int id = exportReceiptService.createExportReceipt(newExportReceipt);
            if (id != -1) {
                response.put("code", 201);
                response.put("status", "success");
                response.put("message", "Tạo phiếu xuất hàng mới thành công");
                response.put("data", id);
                return ResponseEntity.status(201).body(response);
            } else {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Không thể tạo phiếu xuất hàng mới. Vui lòng kiểm tra lại dữ liệu");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tạo phiếu xuất hàng: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }
}
