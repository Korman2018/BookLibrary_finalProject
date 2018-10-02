package com.epam.bookLibrary.controller.reader;

import com.epam.bookLibrary.model.Book;
import com.epam.bookLibrary.service.BookRequestService;
import com.epam.bookLibrary.service.BookService;
import com.epam.bookLibrary.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.epam.bookLibrary.utilities.Const.MSG;

@Controller
public class ReaderBaseController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private BookRequestService requestService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRequestService bookRequestService;

    @RequestMapping("/readerBase")
    public ModelAndView readerBase(
            @ModelAttribute(value = "search") String search
            , @ModelAttribute(value = "search_string") String searchString
            , @ModelAttribute(value = MSG) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/reader/readerBase");
        modelAndView.addObject(msg);

        List<Book> books = bookService.getNotRequestedBooks(search, searchString);

        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @PostMapping("/readerBase/req")
    public ModelAndView reqBook(
            @ModelAttribute(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/readerBase");

        if (bookService.isBookRequested(id)) {
            modelAndView.addObject(MSG, "Ошибка! Кто-то запросил книгу раньше Вас.");
        } else {
            long readerId = userManager.getUser().getId();
            bookRequestService.addBookRequest(readerId, id);
            modelAndView.addObject(MSG, "Книга успешно запрошена!");
        }
        return modelAndView;
    }
}
