package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;

@Entity
public class SalesReport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_report_seq")
    @SequenceGenerator(name = "sales_report_seq", sequenceName = "sales_report_sequence", allocationSize = 1)
    @Column(name = "salesReportID")
    private int salesReportID;

    @Column(name = "monthtime", nullable = false)
    private int month;

    @Column(name = "yeartime", nullable = false)
    private int year;

    @Column(name = "totalRevenue")
    private int totalRevenue;

    public SalesReport() {
    }

    public SalesReport(int month, int year, int totalRevenue) {
        this.month = month;
        this.year = year;
        this.totalRevenue = totalRevenue;
    }

    public SalesReport(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public int getSalesReportID() {
        return salesReportID;
    }

    public void setSalesReportID(int salesReportID) {
        this.salesReportID = salesReportID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
