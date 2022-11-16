package pe.isil.seguridad.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserSecurityController {

    @Autowired
    private UserSecurityService userSecurityService;

    @GetMapping({"/","/index","/login"})
    public String login(){
        return "security/index";
    }


}
