package zeroj.zerojspring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zeroj.zerojspring.domain.Member;
import zeroj.zerojspring.repository.MemberRepository;
import zeroj.zerojspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

//    스프링 컨테이너와 DB까지 연결한 통합테스트 진행
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //Test에서 이러한 상황이 주어져서 이걸로 실행했을때 결과가 이걸로 나와야함 으로 짤린다.
        //given(이 데이터로 기반으로 함)
        Member member = new Member();
        member.setName("서영정");

        //when(이걸 검증)
        Long saveId = memberService.join(member);

        //then(검증부)
        Member findMember = memberService.findOne(saveId).get();
        //이름이 같은지 비교
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복회원예외(){
        //given(이 데이터로 기반으로 함)
        Member member1 = new Member();
        member1.setName("안태용");

        Member member2 = new Member();
        member2.setName("안태용");

        //when(이걸 검증)
        memberService.join(member1);

        //try-catch문장 새롭게 바꿔주기
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}