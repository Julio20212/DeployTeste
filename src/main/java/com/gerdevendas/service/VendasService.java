package com.gerdevendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerdevendas.entity.Vendas;
import com.gerdevendas.repository.VendaRepository;

@Service
public class VendasService {
	private final VendaRepository vendaRepository;

	@Autowired
	public VendasService(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}

	public List<Vendas> getAllVendas() {
		return vendaRepository.findAll();
	}

	public Vendas getVendasById(Long id) {
		Optional<Vendas> vendas = vendaRepository.findById(id);
		return vendas.orElse(null);
	}		

	public Vendas salvarVendas(Vendas vendas) {
		return vendaRepository.save(vendas);
	}

	public Vendas updateVendas(Long id, Vendas updatedVendas) {
		Optional<Vendas> existingVendas = vendaRepository.findById(id);
		if (existingVendas.isPresent()) {
			updatedVendas.setId(id);
			return vendaRepository.save(updatedVendas);
		}
		return null;
	}

	public boolean deleteVendas(Long id) {
		Optional<Vendas> existingVendas = vendaRepository.findById(id);
		if (existingVendas.isPresent()) {
			vendaRepository.deleteById(id);
			return true;
		}
		return false;
	}



}
