package toyproject.login.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 가입된 회원입니다");
                });
    }

    public Optional<Member> findMember(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
