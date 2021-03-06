package com.epam.bookLibrary.interceptor;

import com.epam.bookLibrary.model.User;
import com.epam.bookLibrary.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UserManager userManager;

    /**
     * Если пользователь не библиотекарь то запрет перехода на страницы админа
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User currentUser = userManager.getUser();
        if (!Objects.isNull(currentUser) && currentUser.isLibrarian()) {
            return true;
        }
        response.sendRedirect("/accessDenied");
        return false;
    }
}
