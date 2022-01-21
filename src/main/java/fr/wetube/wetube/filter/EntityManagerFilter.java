package fr.wetube.wetube.filter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EntityManagerFilter", value = "/*")
public class EntityManagerFilter implements Filter {

    @Inject
    private EntityManager entityManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Override
    public void destroy() {}
}
