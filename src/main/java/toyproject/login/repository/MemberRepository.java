package toyproject.login.repository;

import toyproject.login.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByName(String name);
    List<Member> findAll();
}
