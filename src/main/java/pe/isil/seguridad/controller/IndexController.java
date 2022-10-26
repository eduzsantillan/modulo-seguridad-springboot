package pe.isil.seguridad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.isil.seguridad.service.UserService;


@Controller
public class IndexController {


    @Qualifier("userServiceLocal")
    @Autowired
    private UserService userService;


    @GetMapping(value = {"/","/home","/index"})
    public String index(Model model){
        model.addAttribute("atributo","SPRING MVC");
        return "index";
    }

    @GetMapping("/handler2")
    public String vista(Model model){
        model.addAttribute("att");
        return "home";
    }



















}
