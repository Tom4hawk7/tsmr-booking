package tsmr.loyalty.service;

import tsmr.loyalty.entity.Member;
import tsmr.loyalty.entity.Tier;
import tsmr.loyalty.repository.MemberRepository;
import org.springframework.stereotype.Service;
import tsmr.loyalty.repository.TierRepository;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TierRepository tierRepository;

    public MemberService(MemberRepository memberRepository, TierRepository tierRepository) {
        this.memberRepository = memberRepository;
        this.tierRepository = tierRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id).orElseThrow(() ->new RuntimeException("Member Not Found"));
    }

    public Member getMemberByName(String name){
        return memberRepository.findByMemberName(name);
    }

    public Member createMember(Member member) {
        if (member.getTier() == null) {
            Tier baseTier = tierRepository.findByMinPoints(0)
                    .orElseThrow(() -> new RuntimeException("Base tier not found"));
            member.setTier(baseTier);
        }
        member.setPointsBalance(0);
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member updatedMember){
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member Not Found"));

        member.setMemberName(updatedMember.getMemberName());
        member.setMemberName(updatedMember.getMemberName());
        member.setPointsBalance(updatedMember.getPointsBalance());
        member.setTier(updatedMember.getTier());

        return memberRepository.save(member);
    }

    public void deleteMember(Long id){

        memberRepository.deleteById(id);
    }

}
