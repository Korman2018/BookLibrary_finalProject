package com.epam.bookLibrary.config;

import com.epam.bookLibrary.interceptor.AdminInterceptor;
import com.epam.bookLibrary.interceptor.AuthInterceptor;
import com.epam.bookLibrary.interceptor.ReaderInterceptor;
import com.epam.bookLibrary.interceptor.UserNameAwareInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Бин общего интерсептера проверки наличия аутентификации
     */
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public UserNameAwareInterceptor userNameAwareInterceptor() {
        return new UserNameAwareInterceptor();
    }

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }

    @Bean
    public ReaderInterceptor readerInterceptor() {
        return new ReaderInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/logout", "/css/**", "/js/**"
                        , "/images/**", "/accessDenied", "/common/**");

        registry.addInterceptor(userNameAwareInterceptor())
                .addPathPatterns("/admin**")
                .addPathPatterns("/admin**/**")
                .addPathPatterns("/reader**");

        registry.addInterceptor(readerInterceptor()).addPathPatterns("/reader**");

        registry.addInterceptor(adminInterceptor()).addPathPatterns("/admin**");
    }
}
