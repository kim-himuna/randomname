package example.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class IndexController {
    public String index(){
        return "index";
    }
}
