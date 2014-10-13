package com.backbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static javax.servlet.ServletRegistration.Dynamic;

@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /*
        Used for traditional deployment into a Servlet 3.0 container.
        SpringBootServletInitializer is a WebApplicationInitializer which is detected automatically by
        SpringServletContainerInitializer which itself is bootstrapped automatically by any Servlet 3.0 container

        Servlet 3.0 ServletContainerInitializer is designed to support code-based configuration as opposed to
        the traditional web.xml based approach.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /*
     * JBoss EAP 6.2.0 and 6.3.0 mapping.
     */
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(Application.class.getName());

        Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*"); // required JBoss EAP 6.2.0, 6.3.0
        super.onStartup(container);
    }


}
