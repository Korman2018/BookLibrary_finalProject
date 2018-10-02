package com.epam.bookLibrary.controller.admin;

import com.epam.bookLibrary.model.Author;
import com.epam.bookLibrary.model.Book;
import com.epam.bookLibrary.model.Genre;
import com.epam.bookLibrary.service.AuthorService;
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
public class AdminBaseController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @RequestMapping("/adminBase")
    public ModelAndView adminBase(
            @ModelAttribute(value = "search") String search
            , @ModelAttribute(value = "search_string") String searchString
            , @ModelAttribute(value = MSG) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/adminBase");
        modelAndView.addObject(msg);

        List<Book> books = bookService.getNotRequestedBooks(search, searchString);
        List<Author> authors = authorService.getAuthors();
        List<Genre> genres = genreService.getGenres();

        modelAndView.addObject("books", books);
        modelAndView.addObject("authors", authors);
        modelAndView.addObject("genres", genres);

        return modelAndView;
    }

    @PostMapping("/adminBase/del")
    public ModelAndView delBook(
            @ModelAttribute(value = "del_id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        bookService.deleteBook(id);
        modelAndView.addObject(MSG, "Книга успешно удалена");
        modelAndView.setViewName("redirect:/adminBase");
        return modelAndView;
    }


    @PostMapping("/adminBase/add")
    public ModelAndView addBook(
            @Validated Book book
            , BindingResult result
            , @ModelAttribute(value = "authorId") int authorId
            , @ModelAttribute(value = "genreId") int genreId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminBase");

        if (result.hasErrors()) {
            modelAndView.addObject(MSG, "Ошибка! Проверьте название книги.");
        } else {
            bookService.addBook(book.getTitle(), authorId, genreId);
            modelAndView.addObject(MSG, "Книга успешно добавлена!");
        }
        return modelAndView;
    }
}
