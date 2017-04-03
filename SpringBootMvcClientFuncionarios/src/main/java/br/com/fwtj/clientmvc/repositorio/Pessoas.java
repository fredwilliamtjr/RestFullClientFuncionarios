/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.clientmvc.repositorio;

import br.com.fwtj.clientmvc.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Junior
 */

// REPOSITORIO DO SPRING DATA, EXTENDENDO ESSA CLASSE E PASSANDO O MODELO E O TIPO DO ID
// VARIOS METODOS JA ESTAO EMBUTIDOS
public interface Pessoas extends JpaRepository<Pessoa, Long> {
    
}
