package com.epam.bookLibrary.controller;

import com.epam.bookLibrary.model.User;
import com.epam.bookLibrary.service.UserManager;
import com.epam.bookLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.epam.bookLibrary.utilities.Const.MSG;

@Controller
public class AuthController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage(@ModelAttribute(value = MSG) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        User user = userManager.getUser();
        if (user != null) {
            if (user.isLibrarian()) {
                modelAndView.setViewName("redirect:/adminBase");
            } else {
                modelAndView.setViewName("redirect:/readerBase");
            }
        }
        modelAndView.addObject(MSG, msg);
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request
            , @ModelAttribute(value = MSG) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login");
        modelAndView.addObject(MSG, msg);
        request.getSession().invalidate();
        return modelAndView;
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/error");
        modelAndView.addObject("error", "Доступ запрещен!");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginCheck(
            @ModelAttribute(value = "login") String login
            , @ModelAttribute(value = "password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.checkLoginAndPasswordAndGetUser(login, password);
        if (user != null) {
            userManager.setUser(user);
            modelAndView.setViewName("redirect:/login");
        } else {
            modelAndView.addObject(MSG, "Ошибка! Проверьте логин и пароль");
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Validated User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");

        if (result.hasErrors()) {
            modelAndView.addObject(MSG, "Проверьте правильность заполнения полей");
        } else if (!userService.checkAndAddUser(user)) {
            modelAndView.addObject(MSG, "Пользователь с таким логином уже существует");
        } else {
            modelAndView.setViewName("redirect:/login");
            modelAndView.addObject(MSG, "Пользователь успешно зарегистрирован");
        }
        return modelAndView;
    }
}
