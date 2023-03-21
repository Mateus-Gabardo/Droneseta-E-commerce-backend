package com.api.dronesetaecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.dronesetaecommerce.model.DroneModel;

@Repository
public interface DroneRepository extends JpaRepository<DroneModel, UUID>{

}
