package br.com.delivery.deliveryapi.repository;

import br.com.delivery.deliveryapi.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}

