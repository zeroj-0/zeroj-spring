package zeroj.zerojspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //localhost8080에 들어갔을때 바로 밑 코드 호출됨
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
