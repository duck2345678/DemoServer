package org.example.AgentManagementBE.Controller;

import org.example.AgentManagementBE.Model.ImportReceipt;
import org.example.AgentManagementBE.Service.ImportReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/importReceipt")
public class ImportReceiptController {
    @Autowired
    private final ImportReceiptService importReceiptService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ImportReceiptController(ImportReceiptService importReceiptService) {
        this.importReceiptService = importReceiptService;
    }

    @GetMapping("/importReceiptbyID")
    public ResponseEntity<Map<String, Object>> getImportReceiptById(@RequestParam int importReceiptID) {
        Map<String, Object> response = new HashMap<>();
        ImportReceipt importReceipt = ImportReceiptService.getImportReceiptById(importReceiptID);
        
        if (importReceipt != null) {
            response.put("code", 200);
            response.put("status", "SUCCESS");
            response.put("message", "Import receipt retrieved successfully");
            response.put("data", importReceipt);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 404);
            response.put("status", "NOT_FOUND");
            response.put("message", "Import receipt not found with id: " + importReceiptID);
            response.put("data", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/importReceiptbyImportDate")
    public ResponseEntity<Map<String, Object>> getAllImportReceiptByImportDate(@RequestParam String dateReceipt) {
        Map<String, Object> response = new HashMap<>();
        ImportReceipt importReceipt = ImportReceiptService.getAllImportReceiptByImportDate(dateReceipt);
        
        if (importReceipt != null) {
            response.put("code", 200);
            response.put("status", "SUCCESS");
            response.put("message", "Import receipt retrieved successfully");
            response.put("data", importReceipt);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 404);
            response.put("status", "NOT_FOUND");
            response.put("message", "No import receipt found for date: " + dateReceipt);
            response.put("data", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping("/addImportReceipt")
    public ResponseEntity<Map<String, Object>> createImportReceipt(@RequestBody ImportReceipt newImportReceipt) {
        Map<String, Object> response = new HashMap<>();
        int code = ImportReceiptService.createImportReceipt(newImportReceipt);
        
        if (code != -1) {
            response.put("code", 201);
            response.put("status", "CREATED");
            response.put("message", "Import receipt created successfully");
            response.put("data", code);
            return ResponseEntity.status(201).body(response);
        } else {
            response.put("code", 400);
            response.put("status", "BAD_REQUEST");
            response.put("message", "Failed to create import receipt");
            response.put("data", null);
            return ResponseEntity.status(400).body(response);
        }
    }
}