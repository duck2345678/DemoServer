package org.example.AgentManagementBE.Service;

import org.example.AgentManagementBE.Model.ImportReceipt;
import org.example.AgentManagementBE.Repository.ImportReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportReceiptService {
    private static ImportReceiptRepository importReceiptRepository;

    @Autowired
    public ImportReceiptService(ImportReceiptRepository importReceiptRepository) {
        this.importReceiptRepository = importReceiptRepository;
    }

    public static ImportReceipt getImportReceiptById(int importReceiptID) {
        return importReceiptRepository.getImportReceiptById(importReceiptID);
    }

    public static ImportReceipt getAllImportReceiptByImportDate(String dateReceipt) {
        return importReceiptRepository.getAllImportReceiptByDateReceipt(dateReceipt);
    }

    public static int createImportReceipt(ImportReceipt newImportReceipt) {
        try {
            ImportReceipt savedImportReceipt = importReceiptRepository.save(newImportReceipt);
            return savedImportReceipt.getImportReceiptID();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}