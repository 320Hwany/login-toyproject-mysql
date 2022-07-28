package toyproject.login.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyproject.login.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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
