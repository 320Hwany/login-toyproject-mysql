package toyproject.login.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toyproject.login.AutoConfig;
import toyproject.login.repository.JpaMemberRepository;
import toyproject.login.repository.MemberRepository;
import toyproject.login.repository.MemoryMemberRepository;

import javax.persistence.EntityManager;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    @BeforeEach
    void beforeEach(){
        memberRepository.clear();
    }

    @Test
    void join(){
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        memberService.join(member);
        Optional<Member> findMember = memberService.findMember("hello");
        //then
        assertThat(findMember.get().getName()).isEqualTo("hello");
    }

    @Test
    void joinX(){
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        memberService.join(member);
        Optional<Member> findMember = memberService.findMember("hello");
        //then
        assertThat(findMember.get().getName()).isNotEqualTo("hi");
    }

    @Test
    void validateDuplicateMember(){
        //given
        Member member1 = new Member();
        member1.setName("hello");
        Member member2 = new Member();
        member2.setName("hello");
        memberService.join(member1);
        //when
        assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
    }

    @Test
    void findMemberX(){
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        memberService.join(member);
        //then
        Optional<Member> findMember = memberService.findMember("hi");
        assertThrows(NoSuchElementException.class,
                () -> findMember.get().getName());
    }

    @Test
    void findMembers(){
        //given
        Member member1 = new Member();
        member1.setName("hello");
        member1.setId(1L);
        member1.setPassword("hello");

        Member member2 = new Member();
        member2.setName("hi");
        member2.setId(2L);
        member2.setPassword("hi");

        Member member3 = new Member();
        member3.setName("hiHello");
        member3.setId(3L);
        member3.setPassword("hiHello");
        //when
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
        List<Member> memberList = memberService.findMembers();
        //then
        assertThat(memberList.size()).isEqualTo(3);
    }

}