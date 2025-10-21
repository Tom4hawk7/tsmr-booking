package tsmr.loyalty.repository;

import tsmr.loyalty.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberName(String memberName);
}
