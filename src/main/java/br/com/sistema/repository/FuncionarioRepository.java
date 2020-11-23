package br.com.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	

}
