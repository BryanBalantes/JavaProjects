package com.commercial.logbook_app.controller;

import com.commercial.logbook_app.config.USER_TYPE;
import com.commercial.logbook_app.dto.UserDTO;
import com.commercial.logbook_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

  private UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String getRegistrationPage(Model model) {
    model.addAttribute("title", "SignUp");
    model.addAttribute("user", new UserDTO());
    return "registration/index";
  }

  @PostMapping
  public String register(@ModelAttribute("user") UserDTO user) {

    System.out.println("REGISTER CONTROLLER CALLED");
    user.setType(USER_TYPE.GENERAL.getType());
    userService.create(user);
    return "redirect:/";
  }
}
