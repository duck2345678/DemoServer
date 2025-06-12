package org.example.AgentManagementBE.Model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(SalesReportDetail.SalesReportDetailID.class)
@Table(name = "SalesReportDetail")
public class SalesReportDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "agentID", referencedColumnName = "agentID")
    private Agent agentID;

    @Id
    @ManyToOne
    @JoinColumn(name = "salesReportID", referencedColumnName = "salesReportID")
    private SalesReport salesReportID;

    @Column(name = "quantityExport")
    private int quantityExport;

    @Column(name = "totalValue")
    private int totalValue;

    @Column(name = "proportion")
    private double proportion;

    public SalesReportDetail() {
    }

    public SalesReportDetail(Agent agentID, SalesReport salesReportID, int quantityExport, int totalValue, double proportion) {
        this.agentID = agentID;
        this.salesReportID = salesReportID;
        this.quantityExport = quantityExport;
        this.totalValue = totalValue;
        this.proportion = proportion;
    }

    // Getters & Setters...

    public static class SalesReportDetailID implements Serializable {
        private int agentID;
        private int salesReportID;

        public SalesReportDetailID() {
        }

        public SalesReportDetailID(int agentID, int salesReportID) {
            this.agentID = agentID;
            this.salesReportID = salesReportID;
        }

        // Getters & Setters...

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SalesReportDetailID that = (SalesReportDetailID) o;

            if (agentID != that.agentID) return false;
            return salesReportID == that.salesReportID;
        }

        @Override
        public int hashCode() {
            int result = agentID;
            result = 31 * result + salesReportID;
            return result;
        }
    }
}
