CREATE TABLE ExportDetail (
    exportReceiptID INT NOT NULL,
    productID INT NOT NULL,
    quantityExport INT,
    exportPrice INT,
    intoMoney INT,
    PRIMARY KEY (exportReceiptID, productID),
    CONSTRAINT fk_exportdetail_exportreceipt FOREIGN KEY (exportReceiptID)
        REFERENCES ExportReceipt(exportReceiptID)
        ON DELETE CASCADE,
    CONSTRAINT fk_exportdetail_product FOREIGN KEY (productID)
        REFERENCES Product(productID)
        ON DELETE CASCADE
);
