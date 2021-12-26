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
    public String deleteThenRedirect(Model model,@PathVariable(value = "packId") PackId packId){
        packService.deletePack(packId);
        return "redirect:";
    }
}
