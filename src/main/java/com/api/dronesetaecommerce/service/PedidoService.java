package com.api.dronesetaecommerce.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dronesetaecommerce.model.PedidoModel;
import com.api.dronesetaecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Page<PedidoModel> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<PedidoModel> findById(UUID id) {
		return repository.findById(id);
	}
	
	@Transactional
	public PedidoModel save(PedidoModel cliente) {
		return repository.save(cliente);
	}
	
	public void delete(PedidoModel cliente) {
		repository.delete(cliente);
	}
}
