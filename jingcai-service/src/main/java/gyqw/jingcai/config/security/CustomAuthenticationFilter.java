package gyqw.jingcai.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;

        try (InputStream is = request.getInputStream()) {
            // 使用JsonPath读取JSON请求，你也可以换成你喜欢的库
//            DocumentContext context = JsonPath.parse(is);
//            String username = context.read("$.username", String.class);
//            String password = context.read("$.password", String.class);
            // todo
            authRequest = new UsernamePasswordAuthenticationToken("username", "password");
        } catch (IOException e) {
            logger.error("attemptAuthentication error", e);
            authRequest = new UsernamePasswordAuthenticationToken("", "");
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
