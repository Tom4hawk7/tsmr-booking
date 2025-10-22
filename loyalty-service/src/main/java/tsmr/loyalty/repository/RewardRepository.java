package tsmr.loyalty.repository;

import tsmr.loyalty.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward,Long>{
}
