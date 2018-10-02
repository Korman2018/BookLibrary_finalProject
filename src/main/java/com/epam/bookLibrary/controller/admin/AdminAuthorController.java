package com.epam.bookLibrary.controller.admin;

import com.epam.bookLibrary.model.Author;
import com.epam.bookLibrary.model.Person;
import com.epam.bookLibrary.service.AuthorService;
import com.epam.bookLibrary.service.BookRequestService;
import com.epam.bookLibrary.service.BookService;
import com.epam.bookLibrary.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.epam.bookLibrary.utilities.Const.MSG;

@Controller
public class AdminAuthorController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookRequestService bookRequestService;

    @Autowired
    private BookService bookService;

    private static final String REDIRECT_AUTHORS = "redirect:/adminAuthors";


    @RequestMapping("/adminAuthors")
    public ModelAndView adminAuthors(@ModelAttribute(value = MSG) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(msg);

        List<Author> authors = authorService.getAuthors();

        modelAndView.setViewName("/admin/adminAuthors");
        modelAndView.addObject("authors", authors);
        return modelAndView;
    }

    @PostMapping("/adminAuthors/save")
    public ModelAndView updateAuthor(
            @Validated Person person
            , BindingResult result
            , @ModelAttribute(value = "saveId") int id) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject(MSG
                    , "Ошибка! Проверьте  правильность заполнения полей при изменении автора.");
        } else {
            authorService.setAuthor(id, new Author(person));
            modelAndView.addObject(MSG, "Автор успешно изменен!");
        }
        modelAndView.setViewName(REDIRECT_AUTHORS);
        return modelAndView;
    }

    @PostMapping("/adminAuthors/add")
    public ModelAndView addAuthor(
            @Validated Person person
            , BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject(MSG
                    , "Ошибка! Проверьте  правильность заполнения полей при добавлении автора.");
        } else {
            authorService.addAuthor(new Author(person));
            modelAndView.addObject(MSG, "Автор успешно добавлен!");
        }
        modelAndView.setViewName(REDIRECT_AUTHORS);
        return modelAndView;
    }

    @PostMapping("/adminAuthors/del")
    public ModelAndView delAuthor(
            @ModelAttribute(value = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        authorService.deleteAuthor(id);
        modelAndView.addObject(MSG, "Автор успешно удален");
        modelAndView.setViewName(REDIRECT_AUTHORS);
        return modelAndView;
    }
}
