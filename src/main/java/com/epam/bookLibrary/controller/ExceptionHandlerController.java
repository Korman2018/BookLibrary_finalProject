package com.epam.bookLibrary.controller;

import com.epam.bookLibrary.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.bookLibrary.utilities.Const.MSG;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AuthorIsAssignedToBookException.class)
    public ModelAndView handleException(AuthorIsAssignedToBookException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminAuthors");
        modelAndView.addObject(MSG, ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(FailedToSetBookException.class)
    public ModelAndView handleException(FailedToSetBookException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminBase");
        modelAndView.addObject(MSG, ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(FailedToSetGenreException.class)
    public ModelAndView handleException(FailedToSetGenreException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminGenres");
        modelAndView.addObject(MSG, ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(GenreExistException.class)
    public ModelAndView handleException(GenreExistException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminGenres");
        modelAndView.addObject(MSG, ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/error");
        modelAndView.addObject("error", "Что-то пошло не так. Причина: " + ex.getMessage());
        return modelAndView;
    }
}
