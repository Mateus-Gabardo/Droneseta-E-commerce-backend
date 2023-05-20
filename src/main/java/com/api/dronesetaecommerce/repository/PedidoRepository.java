package com.api.dronesetaecommerce.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.dronesetaecommerce.model.PedidoModel;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID>{
	
	@Query(value = "SELECT * FROM pedido WHERE status=?", nativeQuery = true)
	public List<PedidoModel> findByStatus(String status);
}
