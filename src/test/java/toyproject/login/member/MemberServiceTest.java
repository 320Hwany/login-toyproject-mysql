package toyproject.login.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toyproject.login.repository.JpaMemberRepository;
import toyproject.login.repository.MemberRepository;
import toyproject.login.repository.MemoryMemberRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    void beforEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void join(){
        //given
        Member member = new Member();
        member.setId(1L);
        member.setName("hello");
        //when
        memberService.join(member);
        //then
        assertThat(member.getName()).isEqualTo("hello");
    }
}