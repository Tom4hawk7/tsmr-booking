package tsmr.loyalty.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="pointstransaction")
public class PointsTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "memberid", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="redemptionid")
    private Redemption redemption;

    @Column(nullable = false, name="transactiondate")
    private LocalDate transactionDate = LocalDate.now();

    @Column(nullable = false, name="transactiontype")
    private String transactionType;

    @Column(nullable = false, name="pointschange")
    private Integer pointsChange;

    @Column(nullable = false, name="pointsbalanceafter")
    private Integer pointsBalanceAfter;

    @Column(nullable = false, name="description")
    private String description;

    //Constructors
    public PointsTransaction() {}

    public PointsTransaction(Member member, Redemption redemption, String transactionType,
                             Integer pointsChange, Integer pointsBalanceAfter, String description) {
        this.member = member;
        this.redemption = redemption;
        this.transactionType = transactionType;
        this.pointsChange = pointsChange;
        this.pointsBalanceAfter = pointsBalanceAfter;
        this.description = description;
        this.transactionDate = LocalDate.now();
    }

    //Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Redemption getRedemption() {
        return redemption;
    }

    public void setRedemption(Redemption redemption) {
        this.redemption = redemption;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getPointsChange() {
        return pointsChange;
    }

    public void setPointsChange(Integer pointsChange) {
        this.pointsChange = pointsChange;
    }

    public Integer getPointsBalanceAfter() {
        return pointsBalanceAfter;
    }

    public void setPointsBalanceAfter(Integer pointsBalanceAfter) {
        this.pointsBalanceAfter = pointsBalanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
