package tsmr.loyalty.service;

import org.springframework.stereotype.Service;
import tsmr.loyalty.entity.Member;
import tsmr.loyalty.entity.PointsTransaction;
import tsmr.loyalty.repository.PointsTransactionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PointsTransactionService {

    private final PointsTransactionRepository pointsTransactionRepository;

    public PointsTransactionService(PointsTransactionRepository pointsTransactionRepository) {
        this.pointsTransactionRepository = pointsTransactionRepository;
    }

    public List<PointsTransaction> getAllTransactions() {
        return pointsTransactionRepository.findAll();
    }

    public PointsTransaction getTransactionById(Long id) {
        Optional<PointsTransaction> transaction = pointsTransactionRepository.findById(id);
        return transaction.orElse(null); // or throw an exception if preferred
    }

    public PointsTransaction createTransaction(PointsTransaction transaction, Integer points) {
        if (points == null) {
            throw new IllegalArgumentException("Points value cannot be null");
        }

        transaction.setPointsChange(points);

        if (points > 0) {
            transaction.setDescription("Points Added: " + points);
        } else if (points < 0) {
            transaction.setDescription("Points Removed: " + Math.abs(points));
        } else {
            transaction.setDescription("No Points Change");
        }

        if (transaction.getMember() == null) {
            throw new IllegalArgumentException("Transaction must have a member assigned");
        }

        if (transaction.getTransactionType() == null) {
            transaction.setTransactionType(points > 0 ? "ADD" : "DEDUCT");
        }

        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDate.now());
        }

        Member member = transaction.getMember();
        int newBalance = (member.getPointsBalance() != null ? member.getPointsBalance() : 0) + points;
        transaction.setPointsBalanceAfter(newBalance);
        member.setPointsBalance(newBalance);

        return pointsTransactionRepository.save(transaction);
    }

    public PointsTransaction updateTransaction(Long id, PointsTransaction updatedTransaction) {
        Optional<PointsTransaction> existing = pointsTransactionRepository.findById(id);
        if (existing.isPresent()) {
            PointsTransaction transaction = existing.get();
            transaction.setMember(updatedTransaction.getMember());
            transaction.setRedemption(updatedTransaction.getRedemption());
            transaction.setTransactionType(updatedTransaction.getTransactionType());
            transaction.setPointsChange(updatedTransaction.getPointsChange());
            transaction.setPointsBalanceAfter(updatedTransaction.getPointsBalanceAfter());
            transaction.setDescription(updatedTransaction.getDescription());
            transaction.setTransactionDate(updatedTransaction.getTransactionDate());
            return pointsTransactionRepository.save(transaction);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteTransaction(Long id) {
        pointsTransactionRepository.deleteById(id);
    }

    public List<PointsTransaction> getTransactionsByMember(Member member) {
        return pointsTransactionRepository.findAll()
                .stream()
                .filter(t -> t.getMember().equals(member))
                .toList();
    }
}
