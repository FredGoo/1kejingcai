package gyqw.jingcai.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gyqw.jingcai.config.SecurityConfig.responseText;

@Component
public class CustomLoginHandler extends BaseHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(CustomLoginHandler.class);

    // Login Success
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        logger.info("User login successfully, name={}", authentication.getName());
        responseText(response, objectResult(SessionController.getJSON(authentication)));
    }

    // Login Failure
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        responseText(response, errorMessage(exception.getMessage()));
    }

}
