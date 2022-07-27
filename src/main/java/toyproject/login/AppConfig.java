package toyproject.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toyproject.login.member.MemberService;
import toyproject.login.repository.JpaMemberRepository;
import toyproject.login.repository.MemberRepository;

import javax.persistence.EntityManager;

@Configuration
public class AppConfig {

    private final EntityManager em;

    @Autowired
    public AppConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository){
        return new MemberService(memberRepository);
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
