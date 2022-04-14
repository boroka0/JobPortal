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

    @GetMapping("/user")
    public String users(Model model) {
        return users();
    }

    private String users() {
        return "/user";
    }

    @GetMapping("/user/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        UserEntity formUser = userService.getById(id);
        model.addAttribute("formUser", formUser);
        return users();
    }

    @PostMapping("/user")
    public String createUser(
            @ModelAttribute("formUser") @Valid UserEntity formUser,
            BindingResult bindingResult,
            Model model
    ) {
        if (!bindingResult.hasErrors()) {
            userService.createUser(formUser);
            refreshAllUsers(model);
            clearFormUser(model);
        }
        return users();
    }

    @PostMapping("/user/{id}")
    public String save(
            @PathVariable Integer id,
            @ModelAttribute("formUser") @Valid UserEntity formUser,
            BindingResult bindingResult,
            Model model) {
        if (!bindingResult.hasErrors()) {
            userService.save(formUser);
            refreshAllUsers(model);
            clearFormUser(model);
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

    private void refreshAllUsers(Model model) {
        model.addAttribute("allUser", allUser());
    }

    private void clearFormUser(Model model) {
        model.addAttribute("formUser", formUser());
    }
}
