package org.example;

import org.example.web.config.WebContextConfig;
import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setConfigLocation("classpath:app-config.xml");
//        servletContext.addListener(new ContextLoaderListener(appContext));

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebContextConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        ServletRegistration.Dynamic servlet = servletContext.addServlet("h2-console", new WebServlet());
        servlet.setLoadOnStartup(2);
        servlet.addMapping("/console/*");

    }
}
