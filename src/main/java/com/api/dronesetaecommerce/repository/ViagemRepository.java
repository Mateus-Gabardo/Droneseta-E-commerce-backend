package com.api.dronesetaecommerce.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.dronesetaecommerce.model.ViagemModel;

public interface ViagemRepository extends JpaRepository<ViagemModel, UUID>{

}
