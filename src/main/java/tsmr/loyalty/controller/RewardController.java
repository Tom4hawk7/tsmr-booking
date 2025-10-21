package tsmr.loyalty.controller;

import org.springframework.web.bind.annotation.*;
import tsmr.loyalty.entity.Reward;
import tsmr.loyalty.service.RewardService;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    @GetMapping("/{id}")
    public Reward getRewardById(@PathVariable Long id) {
        return rewardService.getRewardById(id);
    }

    @PostMapping
    public Reward createReward(@RequestBody Reward reward) {
        return rewardService.createReward(reward);
    }

    @PutMapping("/{id}")
    public Reward updateReward(@PathVariable Long id, @RequestBody Reward reward) {
        return rewardService.updateReward(id, reward);
    }

    @DeleteMapping("/{id}")
    public void deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
    }
}
