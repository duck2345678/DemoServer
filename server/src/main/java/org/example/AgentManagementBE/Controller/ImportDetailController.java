package org.example.AgentManagementBE.Controller;

import org.example.AgentManagementBE.Model.ImportDetail;
import org.example.AgentManagementBE.Model.Product;
import org.example.AgentManagementBE.Service.ImportDetailService;
import org.example.AgentManagementBE.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/importDetail")
public class ImportDetailController {
    @Autowired
    private final ImportDetailService importDetailService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ImportDetailController(ImportDetailService importDetailService, ProductService productService) {
        this.importDetailService = importDetailService;
        this.productService = productService;
    }

    @GetMapping("/importDetailbyImportReceiptID")
    public ResponseEntity<Map<String, Object>> getImportDetailByImportReceiptID(@RequestParam int importReceiptID) {
        Map<String, Object> response = ImportDetailService.getImportDetailByImportReceiptID(importReceiptID);
        return ResponseEntity.status((Integer) response.get("code")).body(response);
    }

    @GetMapping("/importDetailbyProductID")
    public ResponseEntity<Map<String, Object>> getImportDetailByProductID(@RequestParam int productID) {
        Map<String, Object> response = ImportDetailService.getImportDetailByProductID(productID);
        return ResponseEntity.status((Integer) response.get("code")).body(response);
    }

    @GetMapping("/importDetailbyImportReceiptIDandProductID")
    public ResponseEntity<Map<String, Object>> getImportDetailByImportReceiptIDAndProductID(
            @RequestParam int importReceiptID, 
            @RequestParam int productID) {
        Map<String, Object> response = ImportDetailService.getImportDetailByImportReceiptIDAndProductID(importReceiptID, productID);
        return ResponseEntity.status((Integer) response.get("code")).body(response);
    }

    @PostMapping("/createImportDetail")
    public ResponseEntity<Map<String, Object>> createImportDetail(@RequestBody List<ImportDetail> newImportDetailList) {
        for (ImportDetail newImportDetail : newImportDetailList) {
            Product p = productService.getProductById(newImportDetail.getProductID().getProductID());
            if (p == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("code", 400);
                response.put("status", "BAD_REQUEST");
                response.put("message", "Product not found with ID: " + newImportDetail.getProductID());
                response.put("data", null);
                return ResponseEntity.status(400).body(response);
            }
            
            newImportDetail.setImportPrice(p.getImportPrice());
            newImportDetail.setIntoMoney(newImportDetail.getImportPrice() * newImportDetail.getQuantityImport());
            newImportDetail.setUnit(p.getUnit().getUnitID());
            
            Map<String, Object> response = ImportDetailService.createImportDetail(newImportDetail);
            if ((Integer) response.get("code") != 201) {
                return ResponseEntity.status((Integer) response.get("code")).body(response);
            }
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("code", 201);
        response.put("status", "CREATED");
        response.put("message", "All import details created successfully");
        response.put("data", newImportDetailList);
        return ResponseEntity.status(201).body(response);
    }
}