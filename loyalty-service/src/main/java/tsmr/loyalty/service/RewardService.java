package tsmr.loyalty.service;

import org.springframework.stereotype.Service;
import tsmr.loyalty.entity.Reward;
import tsmr.loyalty.repository.RewardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Reward getRewardById(Long id) {
        Optional<Reward> reward = rewardRepository.findById(id);
        return reward.orElse(null); // or throw custom exception
    }

    public Reward createReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public Reward updateReward(Long id, Reward updatedReward) {
        Optional<Reward> existing = rewardRepository.findById(id);
        if (existing.isPresent()) {
            Reward reward = existing.get();
            reward.setRewardType(updatedReward.getRewardType());
            reward.setRewardCost(updatedReward.getRewardCost());
            reward.setAvailability(updatedReward.getAvailability());
            reward.setValidFrom(updatedReward.getValidFrom());
            reward.setValidTo(updatedReward.getValidTo());
            return rewardRepository.save(reward);
        } else {
            return null; // or throw exception
        }
    }

    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }
}
