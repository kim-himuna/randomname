package example.presentation.controller.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.application.service.PackService;
import example.domain.model.pack.PackId;
import example.domain.model.pack.PackToRegister;
import example.domain.model.pack.Pack;
import example.presentation.coordinator.pack.PackRecordCoordinator;
import example.presentation.form.PackForm;
import example.presentation.form.WordForm;


@Controller
@RequestMapping("packs/register")
@SessionAttributes({"packForm"})
public class PackRegisterController {

    @Autowired
    PackRecordCoordinator packRecordCoordinator;

    @Autowired
    PackService packService;

    @GetMapping
    public String clearSessionAtStart(SessionStatus sessionstatus){
        sessionstatus.setComplete();
        return "forward:/packs/register/input";
    }

    @GetMapping("input")
    public String showForm(Model model) {

        PackForm packForm = new PackForm();
        WordForm[] words = {new WordForm(),new WordForm(),new WordForm(),new WordForm(),new WordForm()};
        packForm.setWordsForm(words);

        model.addAttribute("packForm",packForm);

        return "packs/register/form";
    }

    @GetMapping("input/again")
    public String showFormToModify(){
        return "packs/register/form";
    }

    @PostMapping("confirm")
    public String validate(@Validated @ModelAttribute("packForm") PackForm packForm, BindingResult result){
        if (result.hasErrors()){
            return "packs/register/form";
        }

        return "packs/register/confirm";
    }

    @GetMapping("register")
    public String registerThenRedirectAndClearSession(
        @ModelAttribute("packForm") PackForm packForm,SessionStatus status, RedirectAttributes attributes) {
            
        PackToRegister packToRegister = packRecordCoordinator.packCoordinate(packForm);
        PackId packId = packService.register(packToRegister);
        
        status.setComplete();
        attributes.addAttribute("packId", packId);
        attributes.addAttribute("packTitle", packForm.getTitle());


        return "redirect:/packs/register/completed";
    }

    @GetMapping("completed")
    public String showResult(Model model,@RequestParam("packId") PackId packId,@RequestParam("packTitle") String packTitle) {

        Pack pack = packService.getPack(packId);
        model.addAttribute("pack",pack);
        return "packs/packDetail";
    }

}
