package br.fiap.techchallenge.api.producao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.techchallenge.api.producao.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
