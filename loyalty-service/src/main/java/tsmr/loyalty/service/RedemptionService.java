package tsmr.loyalty.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tsmr.loyalty.entity.Member;
import tsmr.loyalty.entity.Reward;
import tsmr.loyalty.entity.Redemption;
import tsmr.loyalty.entity.PointsTransaction;
import tsmr.loyalty.repository.MemberRepository;
import tsmr.loyalty.repository.RewardRepository;
import tsmr.loyalty.repository.RedemptionRepository;
import tsmr.loyalty.repository.PointsTransactionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RedemptionService {

    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;
    private final RedemptionRepository redemptionRepository;
    private final PointsTransactionRepository pointsTransactionRepository;

    public RedemptionService(MemberRepository memberRepository,
                             RewardRepository rewardRepository,
                             RedemptionRepository redemptionRepository,
                             PointsTransactionRepository pointsTransactionRepository) {
        this.memberRepository = memberRepository;
        this.rewardRepository = rewardRepository;
        this.redemptionRepository = redemptionRepository;
        this.pointsTransactionRepository = pointsTransactionRepository;
    }

    public List<Redemption> getAllRedemptions() {
        return redemptionRepository.findAll();
    }

    public Redemption getRedemptionById(Long id) {
        Optional<Redemption> redemption = redemptionRepository.findById(id);
        return redemption.orElse(null); // or throw an exception
    }

    public Redemption createRedemption(Redemption redemption) {
        return redemptionRepository.save(redemption);
    }

    public Redemption updateRedemption(Long id, Redemption updatedRedemption) {
        Optional<Redemption> existing = redemptionRepository.findById(id);
        if (existing.isPresent()) {
            Redemption redemption = existing.get();
            redemption.setMember(updatedRedemption.getMember());
            redemption.setReward(updatedRedemption.getReward());
            redemption.setPointsUsed(updatedRedemption.getPointsUsed());
            redemption.setStatus(updatedRedemption.getStatus());
            redemption.setRedeemDate(updatedRedemption.getRedeemDate());
            return redemptionRepository.save(redemption);
        } else {
            return null;
        }
    }

    public void deleteRedemption(Long id) {
        redemptionRepository.deleteById(id);
    }

    public List<Redemption> getRedemptionsByMember(Member member) {
        return redemptionRepository.findAll()
                .stream()
                .filter(r -> r.getMember().equals(member))
                .toList();
    }

    public List<Redemption> getRedemptionsByReward(Reward reward) {
        return redemptionRepository.findAll()
                .stream()
                .filter(r -> r.getReward().equals(reward))
                .toList();
    }

    @Transactional
    public Redemption redeemReward(Long memberId, Long rewardId) throws Exception {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Reward not found"));

        if(reward.getAvailability() <= 0){
            throw new IllegalArgumentException("Reward availability must be greater than 0");
        }

        if (member.getPointsBalance() < reward.getRewardCost()) {
            throw new IllegalStateException("Not enough points to redeem this reward");
        }

        // Create redemption
        Redemption redemption = new Redemption(member, reward, reward.getRewardCost(), "REDEEMED");
        redemptionRepository.save(redemption);

        // Deduct points
        member.setPointsBalance(member.getPointsBalance() - reward.getRewardCost());
        memberRepository.save(member);

        reward.setAvailability(reward.getAvailability() - 1);
        rewardRepository.save(reward);

        // Create points transaction
        PointsTransaction transaction = new PointsTransaction(
                member,
                redemption,
                "REDEMPTION",
                -reward.getRewardCost(),
                member.getPointsBalance(),
                "Redeemed reward: " + reward.getRewardCost()
        );
        pointsTransactionRepository.save(transaction);

        return redemption;
    }

}
