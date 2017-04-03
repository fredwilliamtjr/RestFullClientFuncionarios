/*
 * ESTA CLASSE É OBRIGATORIO PARA EXECUTAR O PROJETO DE DENTRO DO TOMCAT, PARA ISSO MUDE NO POM
 * O packaging PARA war
 */
package br.com.fwtj.clientmvc;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Main.class);
	}

}
