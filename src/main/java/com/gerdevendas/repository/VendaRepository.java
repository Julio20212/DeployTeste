package com.gerdevendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerdevendas.entity.Vendas;

public interface VendaRepository extends JpaRepository < Vendas, Long> {

}
