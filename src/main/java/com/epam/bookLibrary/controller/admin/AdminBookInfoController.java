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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.epam.bookLibrary.utilities.Const.MSG;

@Controller
public class AdminBookInfoController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;


    @PostMapping("/adminBook/save")
    public ModelAndView bookSubmit(
            @ModelAttribute(value = "title") String title
            , @ModelAttribute(value = "id") int id
            , @ModelAttribute(value = "authorId") int authorId
            , @ModelAttribute(value = "genreId") int genreId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminBase");
        bookService.setBookAndCheck(id, title, authorId, genreId);
        modelAndView.addObject(MSG, "Книга успешно обновлена!");
        return modelAndView;
    }

    @GetMapping("/adminBook/{book_id}")
    public ModelAndView adminBook(
            @ModelAttribute(value = MSG) String msg
            , @PathVariable(name = "book_id") int bookId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/adminBookInfo");
        modelAndView.addObject(msg);

        List<Author> authors = authorService.getAuthors();
        Book book = bookService.getById(bookId);
        List<Genre> genres = genreService.getGenres();

        modelAndView.addObject("book", book);
        modelAndView.addObject("authors", authors);
        modelAndView.addObject("genres", genres);

        return modelAndView;
    }
}
