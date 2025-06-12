package org.example.AgentManagementBE.Controller;

import org.example.AgentManagementBE.Model.ExportDetail;
import org.example.AgentManagementBE.Model.Product;
import org.example.AgentManagementBE.Model.ExportReceipt;
import org.example.AgentManagementBE.Service.ExportDetailService;
import org.example.AgentManagementBE.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exportDetail")
public class ExportDetailController {
    @Autowired
    private final ExportDetailService exportDetailService;
    @Autowired
    private final ProductService productService;

    public ExportDetailController(ExportDetailService exportDetailService, ProductService productService) {
        this.exportDetailService = exportDetailService;
        this.productService = productService;
    }

    @GetMapping("/getExportDetailByReceiptId")
    public ResponseEntity<Map<String, Object>> getExportDetailsByReceiptId(@RequestParam int exportReceiptId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ExportDetail> exportDetailList = exportDetailService.getExportDetailByExportReceiptID(exportReceiptId);
            if (!exportDetailList.isEmpty()) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Lấy danh sách chi tiết phiếu xuất hàng thành công");
                response.put("data", exportDetailList);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy chi tiết phiếu xuất hàng");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi lấy danh sách chi tiết phiếu xuất hàng: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/getExportDetailByProductId")
    public ResponseEntity<Map<String, Object>> getExportDetailsByProductId(@RequestParam int productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ExportDetail> exportDetailList = exportDetailService.getExportDetailByProductID(productId);
            if (!exportDetailList.isEmpty()) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Lấy danh sách chi tiết phiếu xuất hàng theo sản phẩm thành công");
                response.put("data", exportDetailList);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy chi tiết phiếu xuất hàng theo sản phẩm");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi lấy danh sách chi tiết phiếu xuất hàng: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/getExportDetailByReceiptAndProduct")
    public ResponseEntity<Map<String, Object>> getExportDetailByReceiptAndProduct(
            @RequestParam int exportReceiptId,
            @RequestParam int productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            ExportDetail exportDetail = exportDetailService.getExportDetailByExportReceiptIDAndProductID(exportReceiptId, productId);
            if (exportDetail != null) {
                response.put("code", 200);
                response.put("status", "success");
                response.put("message", "Tìm thấy chi tiết phiếu xuất hàng thành công");
                response.put("data", exportDetail);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 404);
                response.put("status", "error");
                response.put("message", "Không tìm thấy chi tiết phiếu xuất hàng");
                response.put("data", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tìm chi tiết phiếu xuất hàng: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/addExportDetail")
    public ResponseEntity<Map<String, Object>> createExportDetails(@RequestBody Object requestBody) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ExportDetail> newExportDetailList = new ArrayList<>();
            
            // Convert single object to list if needed
            if (requestBody instanceof Map) {
                newExportDetailList.add(convertMapToExportDetail((Map<String, Object>) requestBody));
            } else if (requestBody instanceof List) {
                List<Map<String, Object>> requestList = (List<Map<String, Object>>) requestBody;
                for (Map<String, Object> item : requestList) {
                    newExportDetailList.add(convertMapToExportDetail(item));
                }
            } else {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Invalid request body format");
                response.put("data", null);
                return ResponseEntity.status(400).body(response);
            }

            List<ExportDetail> errorExportDetail = new ArrayList<>();
            
            for (ExportDetail newExportDetail : newExportDetailList) {
                Product p = productService.getProductById(newExportDetail.getProductID().getProductID());
                if (p == null) {
                    errorExportDetail.add(newExportDetail);
                    continue;
                }
                newExportDetail.setExportPrice(p.getExportPrice());
                if (!exportDetailService.createExportDetail(newExportDetail)) {
                    errorExportDetail.add(newExportDetail);
                }
            }
            
            if (!errorExportDetail.isEmpty()) {
                response.put("code", 400);
                response.put("status", "error");
                response.put("message", "Không thể tạo một số chi tiết phiếu xuất hàng");
                response.put("data", errorExportDetail);
                return ResponseEntity.badRequest().body(response);
            }
            
            response.put("code", 201);
            response.put("status", "success");
            response.put("message", "Tạo phiếu xuất chi tiết thành công");
            response.put("data", "Đã tạo " + newExportDetailList.size() + " chi tiết phiếu xuất");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "error");
            response.put("message", "Lỗi khi tạo chi tiết phiếu xuất: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    private ExportDetail convertMapToExportDetail(Map<String, Object> map) {
        ExportDetail exportDetail = new ExportDetail();
        
        // Convert exportReceiptID
        Map<String, Object> exportReceiptMap = (Map<String, Object>) map.get("exportReceiptID");
        ExportReceipt exportReceipt = new ExportReceipt();
        exportReceipt.setExportReceiptID((Integer) exportReceiptMap.get("exportReceiptID"));
        exportDetail.setExportReceiptID(exportReceipt);
        
        // Convert productID
        Map<String, Object> productMap = (Map<String, Object>) map.get("productID");
        Product product = new Product();
        product.setProductID((Integer) productMap.get("productID"));
        exportDetail.setProductID(product);
        
        // Set other fields
        exportDetail.setQuantityExport((Integer) map.get("quantityExport"));
        exportDetail.setExportPrice((Integer) map.get("exportPrice"));
        exportDetail.setIntoMoney((Integer) map.get("intoMoney"));
        
        return exportDetail;
    }
}
