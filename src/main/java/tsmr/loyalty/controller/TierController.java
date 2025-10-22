package tsmr.loyalty.controller;

import org.springframework.web.bind.annotation.*;
import tsmr.loyalty.entity.Tier;
import tsmr.loyalty.service.TierService;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
public class TierController {

    private final TierService tierService;

    public TierController(TierService tierService) {
        this.tierService = tierService;
    }

    @GetMapping
    public List<Tier> getAllTiers() {
        return tierService.getAllTiers();
    }

    @GetMapping("/{id}")
    public Tier getTierById(@PathVariable Long id) {
        return tierService.getTierById(id);
    }

    @PostMapping
    public Tier createTier(@RequestBody Tier tier) {
        return tierService.createTier(tier);
    }

    @PutMapping("/{id}")
    public Tier updateTier(@PathVariable Long id, @RequestBody Tier tier) {
        return tierService.updateTier(id, tier);
    }

    @DeleteMapping("/{id}")
    public void deleteTier(@PathVariable Long id) {
        tierService.deleteTier(id);
    }
}
