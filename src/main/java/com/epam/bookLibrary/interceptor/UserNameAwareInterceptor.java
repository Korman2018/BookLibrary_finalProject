package com.epam.bookLibrary.interceptor;

import com.epam.bookLibrary.model.User;
import com.epam.bookLibrary.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserNameAwareInterceptor implements HandlerInterceptor {

    @Autowired
    private UserManager userManager;

    /**
     * Импортирует текущего пользователя на все страницы
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        User currentUser = userManager.getUser();
        if (modelAndView != null) {
            modelAndView.addObject("current_user", currentUser);
        }
    }
}
