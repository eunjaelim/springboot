package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 정보를 저장하고 조회하는 메서드를 선언
public interface MemberRepository {

    // Member 객체를 저장하는 역할
    Member save(Member member);
    // ID를 인자로 받아 해당 ID의 회원을 Optional로 감싸서 반환 / 회원이 존재하지 않을 경우 빈 Optional을 반환
    Optional<Member> findById(Long id);
    // 이름을 인자로 받아 해당 이름의 회원을 Optional로 감싸서 반환합니다. 회원이 존재하지 않을 경우 빈 Optional을 반환
    Optional<Member> findByName(String name);
    // findAll 메서드는 모든 회원 정보를 반환하는 역할
    List<Member> findAll();

}
