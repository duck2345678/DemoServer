package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.ExportDetail;
import org.example.AgentManagementBE.Repository.ExportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExportDetailService {
    private final ExportDetailRepository exportDetailRepository;
    private final ProductService productService;

    @Autowired
    public ExportDetailService(ExportDetailRepository exportDetailRepository, ProductService productService) {
        this.exportDetailRepository = exportDetailRepository;
        this.productService = productService;
    }

    public List<ExportDetail> getExportDetailByExportReceiptID(int exportReceiptId) {
        try {
            return exportDetailRepository.getExportDetailByExportReceiptID(exportReceiptId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving export details by receipt ID: " + e.getMessage());
        }
    }

    public List<ExportDetail> getExportDetailByProductID(int productId) {
        try {
            return exportDetailRepository.getExportDetailByProductID(productId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving export details by product ID: " + e.getMessage());
        }
    }

    public ExportDetail getExportDetailByExportReceiptIDAndProductID(int exportReceiptId, int productId) {
        try {
            return exportDetailRepository.getExportDetailByExportReceiptIDAndProductID(exportReceiptId, productId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving export detail: " + e.getMessage());
        }
    }

    public boolean createExportDetail(ExportDetail newExportDetail) {
        try {
            if (newExportDetail == null) {
                throw new IllegalArgumentException("Export detail cannot be null");
            }

            if (newExportDetail.getProductID() == null) {
                throw new IllegalArgumentException("Product ID is required");
            }

            if (newExportDetail.getQuantityExport() <= 0) {
                throw new IllegalArgumentException("Export quantity must be greater than 0");
            }

            if (!updateProduct(newExportDetail)) {
                throw new RuntimeException("Failed to update product inventory");
            }

            exportDetailRepository.save(newExportDetail);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error creating export detail: " + e.getMessage());
        }
    }

    private boolean updateProduct(ExportDetail exportDetail) {
        try {
            return productService.downInventoryQuantity(exportDetail.getProductID(), exportDetail.getQuantityExport());
        } catch (Exception e) {
            throw new RuntimeException("Error updating product inventory: " + e.getMessage());
        }
    }
}