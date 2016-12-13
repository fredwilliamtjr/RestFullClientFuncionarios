/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwtj.springbootrestfuncionarios.repositorio;

import br.com.fwtj.springbootrestfuncionarios.dominio.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Junior
 */
public interface FuncionariosRepositorio extends JpaRepository<Funcionario, Long>{
    
}
