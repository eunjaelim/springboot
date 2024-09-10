package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImple implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImple(MemberRepository memberRepository, DiscountPolicy discountPolicy) {  
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // FixDiscountPolicy 구현 종속성 주입
//    DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // DIP 위반 개선
        Member member = memberRepository.findById(memberId); // 회원 정보 검색
      int discountPrice = discountPolicy.discount(member,itemPrice); // 주문에 대한 할인을 계산하는 데 사용

      return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 생성1


    }
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
