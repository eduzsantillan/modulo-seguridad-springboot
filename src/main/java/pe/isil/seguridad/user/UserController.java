package pe.isil.seguridad.user;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("listUsers",userService.findAll());

        return "user/index";
    }

    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model ){

        model.addAttribute("userToUpdate",userService.findUserById(id));
        return "user/update";
    }


    @PostMapping("/update")
    public String updateUser(User user, Model model){
        UserDTO result =   userService.updateUser(user,user.getId());
        if(result.getCode().equals("200")){
            return "redirect:/user/";
        }else{
            model.addAttribute("resp","Correo ya está en uso, por favor probar con otro");
            return "./ValidationResponse";
        }
    }


    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id){

        userService.deleteUser(id);
        return "redirect:/user/";

    }

    @PostMapping("/register")
    public String registerUser(User user,Model model){
        UserDTO result = userService.addUser(user);

        if(result.getCode().equals("200")){
            return "redirect:/user/";
        }else{
            model.addAttribute("resp",result.getMessage());
            return "./ValidationResponse";
        }
    }






}
