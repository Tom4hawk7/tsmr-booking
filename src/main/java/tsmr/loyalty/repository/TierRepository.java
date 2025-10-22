package tsmr.loyalty.repository;

import tsmr.loyalty.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TierRepository extends JpaRepository<Tier,Long>{
    Optional<Tier> findByMinPoints(int minPoints);
}
