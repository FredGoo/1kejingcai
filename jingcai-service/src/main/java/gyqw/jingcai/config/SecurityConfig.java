package gyqw.jingcai.config;

import gyqw.jingcai.config.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private CustomLoginHandler customLoginHandler;
    private CustomLogoutHandler customLogoutHandler;
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setCustomLoginHandler(CustomLoginHandler customLoginHandler) {
        this.customLoginHandler = customLoginHandler;
    }

    @Autowired
    public void setCustomLogoutHandler(CustomLogoutHandler customLogoutHandler) {
        this.customLogoutHandler = customLogoutHandler;
    }

    @Autowired
    public void setCustomAccessDeniedHandler(CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/wechatLogin", "/user/wechatLoginTest").permitAll()
                .anyRequest().authenticated();

        http.formLogin();

        http.logout()
                .logoutUrl("/api/session/logout")
                // 登出前调用，可用于日志
                .addLogoutHandler(customLogoutHandler)
                // 登出后调用，用户信息已不存在
                .logoutSuccessHandler(customLogoutHandler);

        http.exceptionHandling()
                // 已登入用户的权限错误
                .accessDeniedHandler(customAccessDeniedHandler)
                // 未登入用户的权限错误
                .authenticationEntryPoint(customAccessDeniedHandler);


        // 根据 Header Accept-Language 字段设置 Locale
        // 要想启用错误信息的本地化，还需要设置MessageSource，请参阅Github源码
        http.addFilterBefore(new AcceptHeaderLocaleFilter(), UsernamePasswordAuthenticationFilter.class);

        // 替换原先的表单登入 Filter
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
    }

    private CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(customLoginHandler);
        filter.setAuthenticationFailureHandler(customLoginHandler);
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/api/session/login");
        return filter;
    }

    public static void responseText(HttpServletResponse response, String content) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
        response.flushBuffer();
    }

}
