/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.clientmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Junior
 */
@SpringBootApplication
public class Main {

    
    public static void main(String[] args) {
        // INICIA O SPRING BOOT, DEVE ESTAR NO METODO MAIN
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public LocaleResolver localeResolver() {
//        return new FixedLocaleResolver(new Locale("pt", "BR"));
//    }

    // CRIA UM REDIRECT DO RAIZ PARA SEU CONTROLER ESCOLHIDO
    @Configuration
    public static class MvcConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addRedirectViewController("/", "/inicio");
        }

    }

}
