/*
 * ESTA CLASSE É OBRIGATORIO PARA EXECUTAR O PROJETO DE DENTRO DO TOMCAT, PARA ISSO MUDE NO POM
 * O packaging PARA war
 */
package br.com.fwtj.springbootrestfuncionarios.config;

import br.com.fwtj.springbootrestfuncionarios.SpringBootRestFuncionariosApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class InicializadorServlet extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootRestFuncionariosApplication.class);
	}

}
