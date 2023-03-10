package com.api.dronesetaecommerce.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dronesetaecommerce.exceptions.ResourceNotFoundException;
import com.api.dronesetaecommerce.model.Endereco;
import com.api.dronesetaecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoServices {
	
	private Logger logger = Logger.getLogger(EnderecoServices.class.getName());
	
	@Autowired
	EnderecoRepository repository;
	
	public List<Endereco> findAll() {
		logger.info("Procurando todos os endereços!");
		
		return repository.findAll();
	}

	public Endereco findById(Long id) {
		logger.info("Procurando um endereço!");
			
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public Endereco create(Endereco endereco) {
		logger.info("Salvando um novo endereço!");
		
		return repository.save(endereco);
	}
	
	public Endereco update(Endereco endereco) {
		logger.info("Atualizando um endereço!");
		Endereco entity = repository.findById(endereco.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		if(endereco.getLogradouro() != null)
			entity.setLogradouro(endereco.getLogradouro());
		if(endereco.getNumero() != null)
			entity.setNumero(endereco.getNumero());
		if(endereco.getComplemento() != null)
			entity.setComplemento(endereco.getComplemento());
		if(endereco.getBairro() != null)
			entity.setBairro(endereco.getBairro());
		if(endereco.getCidade() != null)
			entity.setCidade(endereco.getCidade());
		if(endereco.getEstado() != null)
			entity.setEstado(endereco.getEstado());
		if(endereco.getCep() != null)
			entity.setCep(endereco.getCep());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deletando um endereço!");

		Endereco entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço com esse ID não foi encontrado!"));
		repository.delete(entity);
	}
	
}