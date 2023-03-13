package com.api.dronesetaecommerce.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.dronesetaecommerce.model.ProdutoModel;
import com.api.dronesetaecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public ProdutoModel save(ProdutoModel product) {
		return this.produtoRepository.save(product);
	}

	public Page<ProdutoModel> findAll(Pageable pageable) {
		return this.produtoRepository.findAll(pageable);
	}

	public Optional<ProdutoModel> findById(UUID id) {
		return this.produtoRepository.findById(id);
	}

	public void delete(ProdutoModel productModel) {
		this.produtoRepository.delete(productModel);
	}
	
}
