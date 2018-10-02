package com.epam.bookLibrary.controller.reader;

import com.epam.bookLibrary.model.BookRequest;
import com.epam.bookLibrary.service.AuthorService;
import com.epam.bookLibrary.service.BookRequestService;
import com.epam.bookLibrary.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReaderRequestsController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookRequestService bookRequestService;

    @RequestMapping("/readerRequests")
    public ModelAndView readerRequests(HttpServletRequest request
            , @ModelAttribute(value = "req") String req) {
        long readerId = userManager.getUser().getId();
        List<BookRequest> requests = bookRequestService.getBookRequestsById(req, readerId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/reader/readerRequests");
        modelAndView.addObject("requests", requests);
        return modelAndView;
    }
}
