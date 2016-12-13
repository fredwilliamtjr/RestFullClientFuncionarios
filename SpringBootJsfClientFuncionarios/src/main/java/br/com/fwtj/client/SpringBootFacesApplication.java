package br.com.fwtj.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.ProjectStage;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.apache.catalina.Context;
import org.primefaces.util.Constants;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.NonEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.sun.faces.config.FacesInitializer;

import br.com.fwtj.client.util.jsf.FacesViewScope;
import javax.faces.webapp.FacesServlet;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication // ***
public class SpringBootFacesApplication extends SpringBootServletInitializer {

    // INICIA O SPRING BOOT
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootFacesApplication.class, args);
    }

    // CNFIGURA O SPRING BOOT
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootFacesApplication.class);
    }
    
    // REGISTRA O VIEW SCOPE
    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.setScopes(Collections.<String, Object>singletonMap(
                FacesViewScope.NAME, new FacesViewScope()));
        return configurer;
    }

    // NECESSARIO PARA O PROXIMO METODO
    @SuppressWarnings("rawtypes")
    private Set<Class<?>> getServletContainerInitializerHandlesTypes(Class<? extends ServletContainerInitializer> sciClass) {
        HandlesTypes annotation = sciClass.getAnnotation(HandlesTypes.class);
        if (annotation == null) {
            return Collections.emptySet();
        }

        Class[] classesArray = annotation.value();
        Set<Class<?>> classesSet = new HashSet<Class<?>>(classesArray.length);
        for (Class clazz : classesArray) {
            classesSet.add(clazz);
        }
        return classesSet;
    }

    // REGISTRA COMFOIGURACOES DO WEB.XML
    @Bean
    @ConditionalOnMissingBean(NonEmbeddedServletContainerFactory.class)
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        tomcat.addContextCustomizers(new TomcatContextCustomizer() {
            @Override
            public void customize(Context context) {
                // register FacesInitializer
                context.addServletContainerInitializer(new FacesInitializer(),
                        getServletContainerInitializerHandlesTypes(FacesInitializer.class));

                // add configuration from web.xml
                context.addWelcomeFile("index.xhtml");

                // register additional mime-types that Spring Boot doesn't register
                context.addMimeMapping("eot", "application/vnd.ms-fontobject");
                context.addMimeMapping("ttf", "application/x-font-ttf");
                context.addMimeMapping("woff", "application/x-font-woff");
            }
        });
        return tomcat;
    }

    // REGISTRA COMFOIGURACOES DO WEB.XML
    @Bean
    public ServletContextInitializer servletContextCustomizer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {
                sc.setInitParameter(Constants.ContextParams.THEME, "delta");
                sc.setInitParameter(Constants.ContextParams.FONT_AWESOME, "true");
                sc.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());
            }
        };
    }
    
    // REGISTRA COMFOIGURACOES DO WEB.XML
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(servlet);
        servletRegistrationBean.addUrlMappings("*.xhtml");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

//    
}
