package com.boroka.jobsearchingapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String users() {
        return "/users";
    }

    @GetMapping("/mainpage")
    public String mainPageToUser(Model model) {
        return "/users";
    }

    @PostMapping("/users")
    public String createUser(
            @ModelAttribute("formUser") @Valid UserEntity formUser,
            BindingResult bindingResult
    ) {
        if (!bindingResult.hasErrors()) {
            userService.createUser(formUser);
        }
        return users();
    }

    @ModelAttribute("allUser")
    List<UserDto> allUser() {
        return userService.findAllUsers();
    }

    @ModelAttribute("formUser")
    public UserEntity formUser() {
        return new UserEntity();
    }

    @ModelAttribute("formNewUser")
    public Boolean formNewUser() {
        return true;
    }
}
