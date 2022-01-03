package example.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import example.application.service.PackService;
import example.domain.model.pack.PackId;

@Controller
@RequestMapping("packs/{packId}/delete")
public class PackDeleteController {

    @Autowired
    PackService packService;
    
    @GetMapping
    public String deleteConfirm(Model model,@PathVariable(value = "packId") long packId){
        model.addAttribute("packId", packId);
        return "packs/delete/confirm";
    }

    @GetMapping("complete")
    public String deleteThenRedirect(Model model,@PathVariable(value = "packId") long packId){
        packService.deletePack(new PackId(packId));
        return "redirect:/";
    }
}
