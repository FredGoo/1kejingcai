package gyqw.jingcai.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gyqw.jingcai.config.SecurityConfig.responseText;

@Component
public class CustomAccessDeniedHandler extends BaseHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    // NoLogged Access Denied
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        responseText(response, errorMessage(authException.getMessage()));
    }

    // Logged Access Denied
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        responseText(response, errorMessage(accessDeniedException.getMessage()));
    }

}
