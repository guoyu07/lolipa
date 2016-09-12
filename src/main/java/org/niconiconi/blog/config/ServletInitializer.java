package org.niconiconi.blog.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Volio on 2016/9/12.
 */
public class ServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic filter = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding","utf-8");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }
}
