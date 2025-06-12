@Entity
public class DebtReport {

    @EmbeddedId
    private DebtReportID debtReportID;

    // Map lại quan hệ bằng cách lấy agentID từ EmbeddedId
    @ManyToOne
    @JoinColumn(name = "agentID", insertable = false, updatable = false)
    private Agent agent;

    @Column(name = "firstDebt")
    private int firstDebt;

    @Column(name = "lastDebt")
    private int lastDebt;

    @Column(name = "arisenDebt")
    private int arisenDebt;

    public DebtReport() {}

    public DebtReport(DebtReportID debtReportID, int firstDebt, int lastDebt, int arisenDebt) {
        this.debtReportID = debtReportID;
        this.firstDebt = firstDebt;
        this.lastDebt = lastDebt;
        this.arisenDebt = arisenDebt;
    }

    public DebtReportID getDebtReportID() { return debtReportID; }

    public void setDebtReportID(DebtReportID debtReportID) { this.debtReportID = debtReportID; }

    public Agent getAgent() { return agent; }

    public void setAgent(Agent agent) { this.agent = agent; }

    public int getFirstDebt() { return firstDebt; }

    public void setFirstDebt(int firstDebt) { this.firstDebt = firstDebt; }

    public int getLastDebt() { return lastDebt; }

    public void setLastDebt(int lastDebt) { this.lastDebt = lastDebt; }

    public int getArisenDebt() { return arisenDebt; }

    public void setArisenDebt(int arisenDebt) { this.arisenDebt = arisenDebt; }
}
