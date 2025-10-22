package tsmr.loyalty.repository;

import tsmr.loyalty.entity.PointsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsTransactionRepository extends JpaRepository<PointsTransaction,Long> {
}
