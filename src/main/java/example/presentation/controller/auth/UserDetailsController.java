package example.presentation.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserDetailsController {
    @GetMapping("/users/detail")
    public String userDetail(){
        return "user/userDetail";
    }
    
}
