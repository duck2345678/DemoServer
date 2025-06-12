CREATE TABLE SalesReport (
    salesReportID INT PRIMARY KEY AUTO_INCREMENT, -- hoặc SERIAL nếu là PostgreSQL
    monthtime INT NOT NULL,
    yeartime INT NOT NULL,
    totalRevenue INT
);
