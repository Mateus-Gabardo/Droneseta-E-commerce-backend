package com.api.dronesetaecommerce.service;

import java.util.List;
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
		return repository.findAllFiltered(pageable);
	}

	public Optional<PedidoModel> findById(UUID id) {
		return repository.findById(id);
	}
	
	@Transactional
	public PedidoModel save(PedidoModel pedido) {
		return repository.save(pedido);
	}
	
	public void delete(PedidoModel pedido) {
		repository.delete(pedido);
	}
	
	public List<PedidoModel> findByClienteId(UUID clienteId){
		return repository.findAllByClienteClienteIdAndClienteExcludedAtNotNull(clienteId);
	}
	
}
