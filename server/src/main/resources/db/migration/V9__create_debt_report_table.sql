CREATE TABLE DebtReport (
    monthTime INT NOT NULL,
    yearTime INT NOT NULL,
    agentID INT NOT NULL,
    firstDebt INT,
    lastDebt INT,
    arisenDebt INT,
    PRIMARY KEY (monthTime, yearTime, agentID),
    FOREIGN KEY (agentID) REFERENCES Agent(agentID)
);
