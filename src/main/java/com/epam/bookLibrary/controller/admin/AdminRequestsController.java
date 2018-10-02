package com.epam.bookLibrary.controller.admin;

import com.epam.bookLibrary.model.BookRequest;
import com.epam.bookLibrary.model.Status;
import com.epam.bookLibrary.service.BookRequestService;
import com.epam.bookLibrary.service.StatusService;
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
public class AdminRequestsController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private StatusService statusService;

    @Autowired
    private BookRequestService bookRequestService;

    @RequestMapping("/adminRequests")
    public ModelAndView adminRequests(
            @ModelAttribute(value = MSG) String msg
            , @ModelAttribute(value = "req") String req) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(msg);
        modelAndView.setViewName("/admin/adminRequests");

        List<BookRequest> requests = bookRequestService.getBookRequests(req);
        List<Status> statuses = statusService.getStatuses();

        modelAndView.addObject("requests", requests);
        modelAndView.addObject("statuses", statuses);

        return modelAndView;
    }

    @PostMapping("/adminRequests/save")
    public ModelAndView saveBookReq(
            @ModelAttribute(value = "saveId") int id
            , @ModelAttribute(value = "status") int statusId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/adminRequests");

        if (bookRequestService.isBookRequestExist(id)) {
            long librarianId = userManager.getUser().getId();
            bookRequestService.updateRequestStatus(id, librarianId, statusId);
            modelAndView.addObject(MSG, "Статус  успешно изменен!");
        } else {
            modelAndView.addObject(MSG, "Кто-то уже закрыл этот запрос!");
        }
        return modelAndView;
    }
}
