package com.api.dronesetaecommerce.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dronesetaecommerce.dto.ProdutoDto;
import com.api.dronesetaecommerce.exception.ObjectNotFoundException;
import com.api.dronesetaecommerce.model.ProdutoModel;
import com.api.dronesetaecommerce.service.ProdutoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProdutoDto productDto){
		ProdutoModel productModel = new ProdutoModel();
		BeanUtils.copyProperties(productDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoService.save(productModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoModel>> getAllProduct(@PageableDefault(
																page = 0, 
																size = 10, 
																sort = "descricao", 
																direction = Sort.Direction.ASC)
																Pageable peageable
																){
		return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.findAll(peageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
		Optional<ProdutoModel> productModelOptional = this.produtoService.findById(id);
		if(!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageProductNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
	}
	
	@GetMapping("/produtoMaisVendido")
	public ResponseEntity<Object> getOProdutosMaisVendidos() {
		return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.getProdutosMaisVendidos());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) throws ObjectNotFoundException {
		Optional<ProdutoModel> productModelOptional = this.produtoService.findById(id);
		if(!productModelOptional.isPresent()) {
			throw new ObjectNotFoundException(getMessageProductNotFound());
		}
		this.produtoService.delete(productModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessageProductDeleted());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
												@RequestBody @Valid ProdutoDto produtDto){
		
		Optional<ProdutoModel> productModelOptional = this.produtoService.findById(id);
		if(!productModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageProductNotFound());
		}
		ProdutoModel productModel = new ProdutoModel();
		BeanUtils.copyProperties(produtDto, productModel);
		productModel.setProdutoId(productModelOptional.get().getProdutoId());
		return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.save(productModel));
	}
	
	protected String getMessageProductNotFound() {
		return "Produto não encontrado";
	}
	
	protected String getMessageProductDeleted() {
		return "Produto deletado com sucesso!";
	}
	
}
