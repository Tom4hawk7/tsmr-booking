package tsmr.loyalty.service;

import org.springframework.stereotype.Service;
import tsmr.loyalty.entity.Tier;
import tsmr.loyalty.repository.TierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TierService {

    private final TierRepository tierRepository;

    public TierService(TierRepository tierRepository) {
        this.tierRepository = tierRepository;
    }

    public List<Tier> getAllTiers() {
        return tierRepository.findAll();
    }

    public Tier getTierById(Long id) {
        Optional<Tier> tier = tierRepository.findById(id);
        return tier.orElse(null);
    }

    public Tier createTier(Tier tier) {
        return tierRepository.save(tier);
    }

    public Tier updateTier(Long id, Tier updatedTier) {
        Optional<Tier> existing = tierRepository.findById(id);
        if (existing.isPresent()) {
            Tier tier = existing.get();
            tier.setTierName(updatedTier.getTierName());
            tier.setMinPoints(updatedTier.getMinPoints());
            tier.setBenefits(updatedTier.getBenefits());
            return tierRepository.save(tier);
        } else {
            return null;
        }
    }

    public void deleteTier(Long id) {
        tierRepository.deleteById(id);
    }
}
