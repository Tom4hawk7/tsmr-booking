package tsmr.loyalty.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberID;

    @Column(nullable = false, name="name")
    private String memberName ="Unkown Member";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tierid")
    private Tier tier;

    @Column(nullable = false, name="pointsbalance")
    private Integer pointsBalance = 0;

    @Column(nullable = false, name = "joindate")
    private LocalDate joinDate = LocalDate.now();

    @Column(nullable = false, name="status")
    private String status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointsTransaction> transactions;

    //Constructors

    public Member() {}

    public Member(String memberName, Tier tier, Integer pointsBalance, LocalDate joinDate, String status) {
        this.memberName = memberName;
        this.tier = tier;
        this.pointsBalance = pointsBalance;
        this.joinDate = joinDate;
        this.status = status;
    }

    //Getters and Setters
    public Long getMemberID() {
        return memberID;
    }

    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Tier getTier() {
        return tier;
    }
    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Integer getPointsBalance() {
        return pointsBalance;
    }
    public void setPointsBalance(Integer pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    //Helpers
    public void addPoints(int points) {
        this.pointsBalance += points;
    }

    public void deductPoints(int points) {
        if (this.pointsBalance >= points) {
            this.pointsBalance -= points;
        }
    }
}
