package com.epam.bookLibrary.interceptor;

import com.epam.bookLibrary.model.User;
import com.epam.bookLibrary.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserManager userManager;

    /**
     * В текущем методе происходит проверка наличия объекта текущего пользователя {@link User} в менеджере сессионных
     * пользователей {@link UserManager}.
     * <p>
     * Если объект равен null, значит пользователь не проходил через процесс аутентификации, а значит он не авторизован.
     * При этом происходит редирект на страницу логина.
     * 
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User currentUser = userManager.getUser();
        if (Objects.isNull(currentUser)) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

}
