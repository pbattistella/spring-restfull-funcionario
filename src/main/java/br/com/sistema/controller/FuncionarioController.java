package br.com.sistema.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.model.Funcionario;
import br.com.sistema.repository.FuncionarioRepository;

@RestController
@RequestMapping("api")
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@GetMapping(path = "/funcionario", produces = "application/json")
	public @ResponseBody ResponseEntity<Iterable<Funcionario>> list() {
		try {
			Iterable<Funcionario> funcionario = funcionarioRepository.findAll();
			if (((Collection<?>) funcionario).size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(funcionario, HttpStatus.OK);
		} catch(Exception e) {
			return new  ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/funcionario/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Funcionario> getFuncionario(@PathVariable("id") long id) {
		try {
			Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
			
			if (funcionario != null) {
				return new ResponseEntity<>(funcionario.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch(Exception e) {
			return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/funcionario", produces = "application/json")
	public @ResponseBody ResponseEntity<Funcionario> add(@RequestBody Funcionario funcionario){
		try {
			return new ResponseEntity<>(funcionarioRepository.save(funcionario), HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping(path="/funcionario/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Funcionario> update(@PathVariable("id") long id, @RequestBody Funcionario funcionario){
		try {
			Optional<Funcionario> f = funcionarioRepository.findById(id);
			if (f.isPresent()) {
				return new ResponseEntity<>(funcionarioRepository.save(funcionario), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@DeleteMapping(path="/funcionario/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Funcionario> delete(@PathVariable("id") long id){
		try {
			funcionarioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
