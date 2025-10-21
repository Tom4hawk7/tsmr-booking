package tsmr.loyalty.controller;

import org.springframework.web.bind.annotation.*;
import tsmr.loyalty.dto.PointsTransactionRequest;
import tsmr.loyalty.entity.Member;
import tsmr.loyalty.entity.PointsTransaction;
import tsmr.loyalty.service.MemberService;
import tsmr.loyalty.service.PointsTransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/points-transactions")
public class PointsTransactionController {

    private final PointsTransactionService pointsTransactionService;
    private final MemberService memberService;

    public PointsTransactionController(PointsTransactionService pointsTransactionService,
                                       MemberService memberService) {
        this.pointsTransactionService = pointsTransactionService;
        this.memberService = memberService;
    }

    // 🔹 Get all transactions
    @GetMapping
    public List<PointsTransaction> getAllTransactions() {
        return pointsTransactionService.getAllTransactions();
    }

    // 🔹 Get transaction by ID
    @GetMapping("/{id}")
    public PointsTransaction getTransactionById(@PathVariable Long id) {
        return pointsTransactionService.getTransactionById(id);
    }

    // 🔹 Create a new transaction
    @PostMapping
    public PointsTransaction createTransaction(@RequestBody PointsTransactionRequest request) {
        Member member = memberService.getMemberById(request.getMemberId());
        if (member == null) {
            throw new IllegalArgumentException("Member does not exist");
        }

        PointsTransaction transaction = new PointsTransaction();
        transaction.setMember(member);
        return pointsTransactionService.createTransaction(transaction, request.getPoints());
    }

    // 🔹 Update a transaction
    @PutMapping("/{id}")
    public PointsTransaction updateTransaction(@PathVariable Long id,
                                               @RequestBody PointsTransaction transaction) {
        return pointsTransactionService.updateTransaction(id, transaction);
    }

    // 🔹 Delete a transaction
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        pointsTransactionService.deleteTransaction(id);
    }

    // 🔹 Get transactions by member
    @GetMapping("/member/{memberId}")
    public List<PointsTransaction> getTransactionsByMember(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        return pointsTransactionService.getTransactionsByMember(member);
    }
}
