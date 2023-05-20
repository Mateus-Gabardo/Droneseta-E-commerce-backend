package com.api.dronesetaecommerce.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dronesetaecommerce.model.ClienteModel;
import com.api.dronesetaecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public Page<ClienteModel> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<ClienteModel> findById(UUID id) {
		return repository.findById(id);
	}
	
	public Optional<ClienteModel> findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
	
	@Transactional
	public ClienteModel save(ClienteModel cliente) {
		return repository.save(cliente);
	}
	
	public void delete(ClienteModel cliente) {
		cliente.setExcludedAt(new Date());
		repository.save(cliente);
	}
	
	public boolean login(String cpf, String senha) {
		Optional<ClienteModel> cliente = repository.findByCpf(cpf);
		if(cliente.isEmpty() && cliente.get().checkSenha(senha)) {
			return true;
		}
		return false;
	}
	
}