package gyqw.jingcai.config.security;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class AcceptHeaderLocaleFilter implements Filter {

    private AcceptHeaderLocaleResolver localeResolver;

    public AcceptHeaderLocaleFilter() {
        localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Locale locale = localeResolver.resolveLocale((HttpServletRequest) request);
        LocaleContextHolder.setLocale(locale);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
