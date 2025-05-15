package com.gerdevendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerdevendas.entity.Cliente;
import com.gerdevendas.repository.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;
 
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
 
	public List<Cliente> buscaTodosClientes() {
		return clienteRepository.findAll();
	}
 
	public Cliente buscaClienteById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElse(null);
	}
 
	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
 
	public Cliente alterarCliente(Long id, Cliente alterarCliente) {
		Optional<Cliente> existeCliente = clienteRepository.findById(id);
		if (existeCliente.isPresent()) {
			alterarCliente.setId(id);
			return clienteRepository.save(alterarCliente);
		}
		return null;
	}
 
	public boolean apagarCliente(Long id) {
		Optional<Cliente> existeCliente = clienteRepository.findById(id);
		if (existeCliente.isPresent()) {
			clienteRepository.deleteById(id);
			return true;
		}
		return false;
	}
 
}