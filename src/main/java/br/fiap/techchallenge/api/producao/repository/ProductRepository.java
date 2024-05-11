package br.fiap.techchallenge.api.producao.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fiap.techchallenge.api.producao.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
