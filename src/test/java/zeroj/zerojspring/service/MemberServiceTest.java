package zeroj.zerojspring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zeroj.zerojspring.domain.Member;
import zeroj.zerojspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
    //clear를 위한 객체 생성
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        //memberservice에서 사용된 memberrepsitory와 같은 객체쓰일 수 있음
        memberService = new MemberService(memberRepository);
    }

    //메소드 끝날때마다 DB clear
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

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
//        try {
//            memberService.join(member2);
//            //Exception이 안터지고 넘어가면 안되기 때문에 문장 다음 fail넣어줌
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //try-catch문장 새롭게 바꿔주기
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

    @Test
    void findMembers() {
    }

    @Test
    void fineOne() {
    }
}