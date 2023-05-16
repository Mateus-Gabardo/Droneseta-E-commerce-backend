package com.api.dronesetaecommerce.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.dronesetaecommerce.model.ViagemModel;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, UUID>{

}
