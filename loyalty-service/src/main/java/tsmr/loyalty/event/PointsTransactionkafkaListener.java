package tsmr.loyalty.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tsmr.loyalty.entity.Member;
import tsmr.loyalty.entity.PointsTransaction;
import tsmr.loyalty.repository.MemberRepository;
import tsmr.loyalty.service.PointsTransactionService;

@Service
public class PointsTransactionKafkaListener {

    private final PointsTransactionService pointsTransactionService;
    private final MemberRepository memberRepository;

    public PointsTransactionKafkaListener(PointsTransactionService pointsTransactionService, MemberRepository memberRepository) {
        this.pointsTransactionService = pointsTransactionService;
        this.memberRepository = memberRepository;
    }

    @KafkaListener(topics = "loyalty-topic", groupId = "loyalty-service-group")
    @Transactional
    public void listenToPointsTransaction(String message) {
        try {
            // Parse the message to extract necessary details
            // Let's assume the message is a simple string in the format:
            // "memberId:points"
            String[] parts = message.split(":");
            Long memberId = Long.parseLong(parts[0]);
            Integer points = Integer.parseInt(parts[1]);

            // Find the member
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));

            // Create a PointsTransaction object
            PointsTransaction pointsTransaction = new PointsTransaction();
            pointsTransaction.setMember(member);

            // Create transaction with points change
            pointsTransactionService.createTransaction(pointsTransaction, points);

            // You could log the successful transaction or further process it
            System.out.println("Transaction processed for Member ID " + memberId + " with " + points + " points.");
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid format, member not found, etc.)
            System.err.println("Failed to process Kafka message: " + e.getMessage());
        }
    }
}
