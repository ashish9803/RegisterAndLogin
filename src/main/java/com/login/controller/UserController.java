package com.login.controller;

import com.login.model.UserModel;
import com.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("Register Request : " + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getLogin(),userModel.getPassword(),userModel.getEmail());
        if(registeredUser==null){
            System.out.println("Enter Details");
            return null;
        }
        else{
            return "redirect:/login";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel){
        System.out.println("Login Request : " + userModel);
        UserModel authenticated = userService.authenticate(userModel.getLogin(),userModel.getPassword());
        if(authenticated==null){
            System.out.println("User not found");
            return null;
        }
        else{
            return "Login Successful";
        }
    }

}
