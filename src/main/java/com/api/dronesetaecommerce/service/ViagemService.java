package com.api.dronesetaecommerce.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.dronesetaecommerce.model.ViagemModel;
import com.api.dronesetaecommerce.repository.ViagemRepository;

@Service
public class ViagemService {
	
	@Autowired
	private ViagemRepository viagemRepository;
	
	@Transactional
	public ViagemModel save(ViagemModel product) {
		return this.viagemRepository.save(product);
	}

	public Page<ViagemModel> findAll(Pageable pageable) {
		return this.viagemRepository.findAll(pageable);
	}

	public Optional<ViagemModel> findById(UUID id) {
		return this.viagemRepository.findById(id);
	}

	public void delete(ViagemModel ViagemModel) {
		this.viagemRepository.delete(ViagemModel);
	}
}
