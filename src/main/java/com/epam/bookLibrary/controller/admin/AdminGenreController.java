package com.epam.bookLibrary.controller.admin;

import com.epam.bookLibrary.model.Genre;
import com.epam.bookLibrary.service.BookRequestService;
import com.epam.bookLibrary.service.BookService;
import com.epam.bookLibrary.service.GenreService;
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
public class AdminGenreController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private GenreService genreService;

    @Autowired
    private BookRequestService bookRequestService;

    @Autowired
    private BookService bookService;

    private static final String REDIRECT_ADMIN_GENRES = "redirect:/adminGenres";

    @RequestMapping("/adminGenres")
    public ModelAndView adminGenres(
            @ModelAttribute(value = MSG) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(msg);

        List<Genre> genres = genreService.getGenres();
        modelAndView.setViewName("/admin/adminGenres");
        modelAndView.addObject("genres", genres);
        return modelAndView;
    }

    @PostMapping("/adminGenres/save")
    public ModelAndView updateGenre(
            @Validated Genre genre
            , BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(REDIRECT_ADMIN_GENRES);
        long id = genre.getId();

        if (result.hasErrors()) {
            modelAndView.addObject(MSG
                    , "Ошибка! Описание жанра не должно быть пустым и больше 40 символов");
        } else {
            genreService.setGenreAndCheck(id, genre.getDescription());
            modelAndView.addObject(MSG, "Жанр успешно изменен!");
        }
        return modelAndView;
    }

    @PostMapping("/adminGenres/add")
    public ModelAndView addGenre(
            @Validated Genre genre
            , BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(REDIRECT_ADMIN_GENRES);

        if (result.hasErrors()) {
            modelAndView.addObject(MSG
                    , "Ошибка! Описание жанра не должно быть пустым и больше 40 символов");
        } else {
            genreService.addGenre(genre.getDescription());
            modelAndView.addObject(MSG, "Жанр успешно добавлен!");
        }
        return modelAndView;
    }

    @PostMapping("/adminGenres/del")
    public ModelAndView delGenre(@ModelAttribute(value = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(REDIRECT_ADMIN_GENRES);

        if (bookService.isGenreUsed(id)) {
            modelAndView.addObject(MSG, "Ошибка! Есть книга с таким жанром. Сначала удалите данную книгу");
        } else {
            genreService.deleteGenre(id);
            modelAndView.addObject(MSG, "Жанр успешно удален");
        }
        return modelAndView;
    }
}
