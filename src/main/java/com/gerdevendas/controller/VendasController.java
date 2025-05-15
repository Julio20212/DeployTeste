package com.gerdevendas.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.gerdevendas.entity.Vendas;
import com.gerdevendas.service.VendasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class VendasController {

	private final VendasService vendasService;

	@Autowired
	public VendasController(VendasService vendasService) {
		this.vendasService = vendasService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Vendas>> getAllVendas() {
		List<Vendas> vendas = vendasService.getAllVendas();
		return ResponseEntity.ok(vendas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vendas> getVendasById(@PathVariable Long id) {
		Vendas venda = vendasService.getVendasById(id);
		if (venda != null) {
			return ResponseEntity.ok(venda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Vendas> createVendas(@RequestBody @Valid Vendas vendas) {
		Vendas novaVenda = vendasService.salvarVendas(vendas);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Vendas> updateVendas(@PathVariable Long id, @RequestBody Vendas vendas) {
		Vendas updated = vendasService.updateVendas(id, vendas);
		if (updated != null) {
			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVendas(@PathVariable Long id) {
		boolean deleted = vendasService.deleteVendas(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
