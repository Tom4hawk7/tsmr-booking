package tsmr.loyalty.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long redemptionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberid")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rewardid")
    private Reward reward;

    @Column(nullable = false, name="pointsused")
    private Integer pointsUsed;

    @Column(nullable = false, name = "status")
    private String status;

    @Column(nullable = false, name="redeemdate")
    private LocalDate redeemDate = LocalDate.now();

    // Constructors
    public Redemption() {}

    public Redemption(Member member, Reward reward, Integer pointsUsed, String status) {
        this.member = member;
        this.reward = reward;
        this.pointsUsed = pointsUsed;
        this.status = status;
        this.redeemDate = LocalDate.now();
    }

    // Getters and Setters

    public Long getRedemptionId() {
        return redemptionId;
    }

    public void setRedemptionId(Long redemptionId) {
        this.redemptionId = redemptionId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public Integer getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(LocalDate redeemDate) {
        this.redeemDate = redeemDate;
    }
}


