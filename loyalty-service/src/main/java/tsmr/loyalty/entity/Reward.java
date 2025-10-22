package tsmr.loyalty.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    @Column(nullable = false, name="rewardtype")
    private String rewardType;

    @Column(nullable = false, name="rewardcost")
    private Integer rewardCost;

    @Column(nullable = false, name="availability")
    private Integer availability;

    @Column(nullable = false, name="validfrom")
    private LocalDate validFrom;

    @Column(nullable = false, name = "validto")
    private LocalDate validTo;

    // Constructors
    public Reward() {}

    public Reward(String rewardType, Integer rewardCost, Integer availability, LocalDate validFrom, LocalDate validTo) {
        this.rewardType = rewardType;
        this.rewardCost = rewardCost;
        this.availability = availability;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    //Getters and Setters
    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardCost() {
        return rewardCost;
    }

    public void setRewardCost(Integer rewardCost) {
        this.rewardCost = rewardCost;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }
}
