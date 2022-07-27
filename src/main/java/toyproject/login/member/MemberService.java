package toyproject.login.member;

import toyproject.login.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
