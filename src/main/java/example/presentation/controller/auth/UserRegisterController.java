package example.presentation.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.application.service.UserService;
import example.domain.model.user.User;
import example.domain.model.user.UserName;
import example.domain.model.user.UserPassword;
import example.domain.model.user.UserRole;
import example.presentation.form.UserRegisterForm;

@Controller
@RequestMapping("user/register")
@SessionAttributes({"userRegisterForm"})
public class UserRegisterController {
    
    @Autowired
    UserService userSerivice;

    @GetMapping
    public String clearSessionAtStart(SessionStatus sessionstatus){
        sessionstatus.setComplete();
        return "forward:/user/register/input";
    }
    
    @GetMapping("input")
    public String showForm(Model model){
        UserRegisterForm userRegisterForm = new UserRegisterForm();
        model.addAttribute("userRegisterForm", userRegisterForm);
        return "user/register/registerationForm";
    }

    @PostMapping("confirm")
    public String validate(@Validated @ModelAttribute("userRegisterForm") UserRegisterForm userRegisterForm,BindingResult result,Model model){

        if(result.hasErrors()){
            return "user/register/registerationForm";
        }

        if(userSerivice.isExistUser(userRegisterForm.getUserName())){
            model.addAttribute("userRegisterForm", userRegisterForm);
            model.addAttribute("signupError", "ユーザー名 " + userRegisterForm.getUserName() + "は既に登録されています");
            return "user/register/registerationForm";
        }
        return "user/register/confirm";
    }

    @GetMapping("register")
    public String registerThenRedirectAndClearSession(
        @ModelAttribute("userRegisterForm") UserRegisterForm userRegisterForm,SessionStatus status, RedirectAttributes attributes,Model model) {

        if(userSerivice.isExistUser(userRegisterForm.getUserName())){
            model.addAttribute("userRegisterForm", userRegisterForm);
            model.addAttribute("signupError", "ユーザー名 " + userRegisterForm.getUserName() + "は既に登録されています");
            return "user/register/registerationForm";
        }

        /**TODO userのidかぶらないような処理に書き直す*/
        User user = new User(null, new UserName(userRegisterForm.getUserName()), new UserRole("USER"), new UserPassword(userRegisterForm.getUserPassword()));
        userSerivice.register(user);

        status.setComplete();

        return "redirect:/user/register/completed";
    }

    @GetMapping("completed")
    public String showResult(Model model) {
        return "redirect:/";
    }
    
}
