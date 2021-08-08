package zeroj.zerojspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import zeroj.zerojspring.service.MemberService;

// html을 뿌려주기 위함.
// controller를 만들어서 memberservice를 통해서 회원가입하고 데이터를 조회할 수 있어야 하는데 -> 의존관계에 있다고 할 수 있음

// 스프링컨테이너라는 통이 생기는데 @Controller라는 어노테이션이 있으면 memberController라는 객체를 생성해 스프링에 넣어두고 스프링이 관리해줌
//   => 스프링 컨테이너에서 스프링 빈이 관리된다라고 표현함
@Controller
public class MemberController {

    //memberService를 가져다 써야하는데
    //스프링이 관리를하게되면 스프링 컨테이너에 등록하고 컨테이너에서 받아쓰도록 바꿔주어야 함
    //memberService의 경우 여러개의 인스턴스를 만들 필요가 없기때문에 하나를 생성해놓고 공용으로 쓰면 되는 것
//    private final MemberService memberService = new MemberService();

    //스프링컨테이너에 등록
    private final MemberService memberService;

    //생성자에 @Autowired를 붙이는 경우 memberService를 스프링이 스프링컨테이너에 있는 memberService를 가져다가 연결시켜 준다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
