package com.hellokoding.account.web;

import com.hellokoding.account.model.User;
import com.hellokoding.account.service.UserService;
import com.hellokoding.account.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        logger.debug("Hello!");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.debug("Error!");
            return "registration";
        }

        userService.save(userForm);

        model.addAttribute("username", userForm.getUsername());
        return "index";
    }
}
