package com.api.dronesetaecommerce.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.dronesetaecommerce.model.ViagemModel;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, UUID>{

	@Query(value = "SELECT * FROM viagem WHERE status=?", nativeQuery = true)
	public List<ViagemModel> findByStatus(String status);
	
	@Query(value = "SELECT * FROM viagem WHERE pedido_id=?", nativeQuery = true)
	public List<ViagemModel> findByPedidoID(UUID pedido);
}
