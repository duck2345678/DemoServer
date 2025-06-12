@Entity
@EntityListeners(AuditingEntityListener.class)
public class PaymentReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentReceiptID")
    private int paymentReceiptID;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "paymentDate", nullable = false, updatable = false)
    private Date paymentDate;

    @Column(name = "revenue", nullable = false)
    private int revenue;

    @ManyToOne
    @JoinColumn(name = "agentID", nullable = false)
    private Agent agentID;

    public PaymentReceipt() {}

    public PaymentReceipt(int revenue, Agent agentID) {
        this.revenue = revenue;
        this.agentID = agentID;
    }

    public int getPaymentReceiptID() {
        return paymentReceiptID;
    }

    public void setPaymentReceiptID(int paymentReceiptID) {
        this.paymentReceiptID = paymentReceiptID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public Agent getAgentID() {
        return agentID;
    }

    public void setAgentID(Agent agentID) {
        this.agentID = agentID;
    }

    @PrePersist
    protected void onCreate() {
        if (this.paymentDate == null) {
            this.paymentDate = new Date();
        }
    }
}
