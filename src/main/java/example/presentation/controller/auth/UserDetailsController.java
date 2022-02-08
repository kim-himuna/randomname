package example.presentation.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("users")
public class UserDetailsController {
    @GetMapping("/detail")
    public String userDetail(){
        return "user/userDetail";
    }

    
}
