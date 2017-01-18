package br.ufc.quixada.apiweb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.apiweb.model.Cliente;
import br.ufc.quixada.apiweb.repository.ClienteRepository;
import ch.qos.logback.core.net.server.Client;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente cadastrar(Cliente cliente){
		return repository.save(cliente);
	}
	
	public Collection<Cliente> buscarTodos(){
		return repository.findAll();
	}
	
	public Cliente buscarPorId(Integer idCliente){
		return repository.findOne(idCliente);
	}
	
	public void deletar(Cliente cliente){
		repository.delete(cliente);
	}
	
	public Cliente alterar(Cliente cliente){
		return repository.save(cliente);
	}
}
