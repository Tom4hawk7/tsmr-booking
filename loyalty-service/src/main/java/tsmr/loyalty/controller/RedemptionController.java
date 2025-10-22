package tsmr.loyalty.controller;

import org.springframework.web.bind.annotation.*;
import tsmr.loyalty.entity.Member;
import tsmr.loyalty.entity.Redemption;
import tsmr.loyalty.entity.Reward;
import tsmr.loyalty.service.MemberService;
import tsmr.loyalty.service.RedemptionService;
import tsmr.loyalty.service.RewardService;

import java.util.List;

@RestController
@RequestMapping("/api/redemptions")
public class RedemptionController {

    private final RedemptionService redemptionService;
    private final MemberService memberService;
    private final RewardService rewardService;

    public RedemptionController(RedemptionService redemptionService,
                                MemberService memberService,
                                RewardService rewardService) {
        this.redemptionService = redemptionService;
        this.memberService = memberService;
        this.rewardService = rewardService;
    }

    // 🔹 Get all redemptions
    @GetMapping
    public List<Redemption> getAllRedemptions() {
        return redemptionService.getAllRedemptions();
    }

    // 🔹 Get redemption by ID
    @GetMapping("/{id}")
    public Redemption getRedemptionById(@PathVariable Long id) {
        return redemptionService.getRedemptionById(id);
    }

    // 🔹 Create a new redemption
    @PostMapping
    public Redemption createRedemption(@RequestBody Redemption redemption) throws Exception {
        Member member = memberService.getMemberById(redemption.getMember().getMemberID());
        Reward reward = rewardService.getRewardById(redemption.getReward().getRewardId());

        if (member == null) throw new IllegalArgumentException("Member does not exist");
        if (reward == null) throw new IllegalArgumentException("Reward does not exist");

        return redemptionService.redeemReward(member.getMemberID(), reward.getRewardId());
    }

    // 🔹 Update a redemption
    @PutMapping("/{id}")
    public Redemption updateRedemption(@PathVariable Long id,
                                       @RequestBody Redemption redemption) {
        return redemptionService.updateRedemption(id, redemption);
    }

    // 🔹 Delete a redemption
    @DeleteMapping("/{id}")
    public void deleteRedemption(@PathVariable Long id) {
        redemptionService.deleteRedemption(id);
    }

    // 🔹 Get all redemptions for a specific member
    @GetMapping("/member/{memberId}")
    public List<Redemption> getRedemptionsByMember(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        if (member == null) throw new IllegalArgumentException("Member does not exist");
        return redemptionService.getRedemptionsByMember(member);
    }

    // 🔹 Get all redemptions for a specific reward
    @GetMapping("/reward/{rewardId}")
    public List<Redemption> getRedemptionsByReward(@PathVariable Long rewardId) {
        Reward reward = rewardService.getRewardById(rewardId);
        if (reward == null) throw new IllegalArgumentException("Reward does not exist");
        return redemptionService.getRedemptionsByReward(reward);
    }
}
