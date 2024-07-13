package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    // 이 필드는 클래스 내부에서만 접근 가능하며, 한 번 초기화된 후에는 변경할 수 없습니다.
    // MemoryMemberRepository 클래스의 인스턴스를 생성하여 memberRepository 필드에 할당
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    회원가입

    // Member 객체를 매개변수로 받음
    // Member 객체는 가입하려는 회원의 정보를 담고 있다.
    public Long join(Member member){
        // 중복 회원 검증
        validateDuplicateMember(member);
        // memberRepository의 save 메서드를 호출하여 Member 객체를 저장
        memberRepository.save(member);
        // 저장된 Member 객체의 ID를 반환
        return member.getId();
    }
    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
