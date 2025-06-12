CREATE TABLE SalesReportDetail (
    agentID INT NOT NULL,
    salesReportID INT NOT NULL,
    quantityExport INT,
    totalValue INT,
    proportion DOUBLE,
    PRIMARY KEY (agentID, salesReportID),
    CONSTRAINT fk_agent_salesreportdetail FOREIGN KEY (agentID) REFERENCES Agent(agentID),
    CONSTRAINT fk_salesreport_salesreportdetail FOREIGN KEY (salesReportID) REFERENCES SalesReport(salesReportID)
);
