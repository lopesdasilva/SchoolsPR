package filters;
 
import java.io.IOException;
 
import javax.servlet.*;
 ;
 
public class LoginFilter implements Filter {
 
    @Override
    public void destroy() {
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
 
 
        filterChain.doFilter(servletRequest, servletResponse);
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}