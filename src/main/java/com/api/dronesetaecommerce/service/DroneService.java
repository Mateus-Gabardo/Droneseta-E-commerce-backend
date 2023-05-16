package com.api.dronesetaecommerce.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.dronesetaecommerce.model.DroneModel;
import com.api.dronesetaecommerce.repository.DroneRepository;

@Service
public class DroneService {
	
	@Autowired
	private DroneRepository droneRepository;
	
	@Transactional
	public DroneModel save(DroneModel product) {
		return this.droneRepository.save(product);
	}

	public Page<DroneModel> findAll(Pageable pageable) {
		return this.droneRepository.findAll(pageable);
	}

	public Optional<DroneModel> findById(UUID id) {
		return this.droneRepository.findById(id);
	}

	public void delete(DroneModel droneModel) {
		this.droneRepository.delete(droneModel);
	}
	
}
