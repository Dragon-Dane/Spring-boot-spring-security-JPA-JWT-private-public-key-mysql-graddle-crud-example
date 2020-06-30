package com.amran.user.module.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @Author : Amran Hosssain on 6/16/2020
 * Any kind of permission related exception will rise from this component like Un-Authorization 401.
 * Every Request will serve from here.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    /*
     *ExceptionTranslationFilter is used to catch any Spring Security exceptions so that either an HTTP error response can be returned, or an appropriate AuthenticationEntryPoint can be launched.
     * The AuthenticationEntryPoint will be called if the user requests a secure HTTP resource, but they are not authenticated.
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
