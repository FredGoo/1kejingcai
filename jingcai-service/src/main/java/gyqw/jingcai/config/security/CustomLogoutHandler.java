package gyqw.jingcai.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gyqw.jingcai.config.SecurityConfig.responseText;

@Component
public class CustomLogoutHandler extends BaseHandler implements LogoutHandler, LogoutSuccessHandler {

    // Before Logout
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }

    // After Logout
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        responseText(response, objectResult(SessionController.getJSON(null)));
    }

}
