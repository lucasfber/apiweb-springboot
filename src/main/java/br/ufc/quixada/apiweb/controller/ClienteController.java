package br.ufc.quixada.apiweb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.apiweb.model.Cliente;
import br.ufc.quixada.apiweb.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(
			value = "/clientes",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente){
		Cliente clienteCadastrado = clienteService.cadastrar(cliente);
		
		return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/clientes", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> listarTodos(){
		Collection<Cliente> clientesBuscados = clienteService.buscarTodos();
		
		return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/clientes/{id}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id){
		Cliente clienteEncontrado = clienteService.buscarPorId(id);
		
		if(clienteEncontrado == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clienteService.deletar(clienteEncontrado);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clientes", 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente){
		
		Cliente clienteAlterado = clienteService.alterar(cliente);
		return new ResponseEntity<>(clienteAlterado, HttpStatus.OK);
	}
}
