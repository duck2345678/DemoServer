@Embeddable
public class DebtReportID implements Serializable {

    @Column(name = "monthTime")
    private int month;

    @Column(name = "yearTime")
    private int year;

    @Column(name = "agentID")
    private int agentID;

    public DebtReportID() {}

    public DebtReportID(int month, int year, int agentID) {
        this.month = month;
        this.year = year;
        this.agentID = agentID;
    }

    public int getMonth() { return month; }

    public int getYear() { return year; }

    public int getAgentID() { return agentID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DebtReportID)) return false;
        DebtReportID that = (DebtReportID) o;
        return month == that.month &&
               year == that.year &&
               agentID == that.agentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year, agentID);
    }
}
