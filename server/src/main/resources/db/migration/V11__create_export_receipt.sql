CREATE TABLE ExportReceipt (
    exportReceiptID INT PRIMARY KEY AUTO_INCREMENT,
    dateReceipt DATE NOT NULL,
    agentID INT NOT NULL,
    totalMoney INT,
    paymentAmount INT,
    remainAmount INT,
    CONSTRAINT fk_exportreceipt_agent FOREIGN KEY (agentID)
        REFERENCES Agent(agentID)
        ON DELETE CASCADE
);
