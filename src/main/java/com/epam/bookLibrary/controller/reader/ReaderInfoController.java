package com.epam.bookLibrary.controller.reader;

import com.epam.bookLibrary.model.User;
import com.epam.bookLibrary.service.UserManager;
import com.epam.bookLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.bookLibrary.utilities.Const.MSG;


@Controller
public class ReaderInfoController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserService userService;

    @RequestMapping("/readerInfo")
    public ModelAndView readerInfo(
            @ModelAttribute(value = MSG) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/reader/readerInfo");
        modelAndView.addObject(msg);
        return modelAndView;
    }

    @PostMapping("/readerInfo")
    public ModelAndView readerInfoSave(@Validated User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/readerInfo");
        user.setLibrarian(false);
        if (result.hasErrors()) {
            modelAndView.addObject(MSG, "Проверьте правильность заполнения полей");
        } else if (!userService.checkAndUpdateUser(user)) {
            modelAndView.addObject(MSG, "Пользователь с таким логином уже существует");
        } else {
            modelAndView.addObject(MSG, "Пользователь успешно изменен");
            modelAndView.setViewName("redirect:/logout");
        }
        return modelAndView;
    }
}
