package zeroj.zerojspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zeroj.zerojspring.domain.Member;
import zeroj.zerojspring.service.MemberService;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        //createMemberform으로 이동해주는 역할 -> 리턴하면서 template에서 찾음 -> viewResolver 통해서 html뿌림
        return "members/createMemberform";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        //스프링이 setName을 호출해 값이 들어감 왜냐면 name이 private로 선언되었기 때문에
        //그래서 getName으로 꺼내줌
        member.setName(form.getName());

        memberService.join(member);

        //회원가입이 끝나면서 홈화면으로 보내주는 것
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
