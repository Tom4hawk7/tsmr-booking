package tsmr.loyalty.entity;

import jakarta.persistence.*;

@Entity
public class Tier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tierId;

    @Column(nullable = false, name="tiername")
    private String tierName;

    @Column(nullable = false, name="minpoints")
    private Integer minPoints;

    @Column(columnDefinition = "TEXT", name="benefits")
    private String benefits;

    //Constructors
    public Tier() {}

    public Tier(String tierName, Integer minPoints, String benefits) {
        this.tierName = tierName;
        this.minPoints = minPoints;
        this.benefits = benefits;
    }

    //Getters and Setters
    public Long getTierId() {
        return tierId;
    }

    public void setTierId(Long tierId) {
        this.tierId = tierId;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public Integer getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(Integer minPoints) {
        this.minPoints = minPoints;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}
