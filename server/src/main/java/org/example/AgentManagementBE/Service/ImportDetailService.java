package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.ImportDetail;
import org.example.AgentManagementBE.Model.ImportReceipt;
import org.example.AgentManagementBE.Repository.ImportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImportDetailService {
    private static ImportDetailRepository importDetailRepository;
    private static ProductService productService;

    @Autowired
    public ImportDetailService(ImportDetailRepository importDetailRepository, ProductService productService) {
        this.importDetailRepository = importDetailRepository;
        this.productService = productService;
    }

    public static Map<String, Object> getImportDetailByImportReceiptID(int importReceiptID) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ImportDetail> importDetails = importDetailRepository.getImportDetailByImportReceiptID(importReceiptID);
            if (!importDetails.isEmpty()) {
                response.put("code", 200);
                response.put("status", "SUCCESS");
                response.put("message", "Import details retrieved successfully");
                response.put("data", importDetails);
            } else {
                response.put("code", 404);
                response.put("status", "NOT_FOUND");
                response.put("message", "No import details found for receipt ID: " + importReceiptID);
                response.put("data", null);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "ERROR");
            response.put("message", "Error retrieving import details: " + e.getMessage());
            response.put("data", null);
        }
        return response;
    }

    public static Map<String, Object> getImportDetailByProductID(int productID) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ImportDetail> importDetails = importDetailRepository.getImportDetailByProductID(productID);
            if (!importDetails.isEmpty()) {
                response.put("code", 200);
                response.put("status", "SUCCESS");
                response.put("message", "Import details retrieved successfully");
                response.put("data", importDetails);
            } else {
                response.put("code", 404);
                response.put("status", "NOT_FOUND");
                response.put("message", "No import details found for product ID: " + productID);
                response.put("data", null);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "ERROR");
            response.put("message", "Error retrieving import details: " + e.getMessage());
            response.put("data", null);
        }
        return response;
    }

    public static Map<String, Object> getImportDetailByImportReceiptIDAndProductID(int importReceiptID, int productID) {
        Map<String, Object> response = new HashMap<>();
        try {
            ImportDetail importDetail = importDetailRepository.getImportDetailByImportReceiptIDAndProductID(importReceiptID, productID);
            if (importDetail != null) {
                response.put("code", 200);
                response.put("status", "SUCCESS");
                response.put("message", "Import detail retrieved successfully");
                response.put("data", importDetail);
            } else {
                response.put("code", 404);
                response.put("status", "NOT_FOUND");
                response.put("message", "Import detail not found for receipt ID: " + importReceiptID + " and product ID: " + productID);
                response.put("data", null);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "ERROR");
            response.put("message", "Error retrieving import detail: " + e.getMessage());
            response.put("data", null);
        }
        return response;
    }
    
    public static Map<String, Object> createImportDetail(ImportDetail newImportDetail) {
        Map<String, Object> response = new HashMap<>();
        try {
            ImportReceipt existingImportReceipt = ImportReceiptService.getImportReceiptById(newImportDetail.getImportReceiptID().getImportReceiptID());
            if (existingImportReceipt != null) {
                importDetailRepository.save(newImportDetail);
                updateProduct(newImportDetail);
                response.put("code", 201);
                response.put("status", "CREATED");
                response.put("message", "Import detail created successfully");
                response.put("data", newImportDetail);
            } else {
                response.put("code", 404);
                response.put("status", "NOT_FOUND");
                response.put("message", "Import receipt not found with ID: " + newImportDetail.getImportReceiptID().getImportReceiptID());
                response.put("data", null);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("status", "ERROR");
            response.put("message", "Error creating import detail: " + e.getMessage());
            response.put("data", null);
        }
        return response;
    }
    
    private static void updateProduct(ImportDetail importDetail) {
        productService.upInventoryQuantity(importDetail.getProductID(), importDetail.getQuantityImport());
    }
}