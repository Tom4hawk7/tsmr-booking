package tsmr.loyalty.repository;

import tsmr.loyalty.entity.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedemptionRepository extends JpaRepository<Redemption,Long> {
}
