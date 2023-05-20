package com.api.dronesetaecommerce.controller;

import java.util.Optional;
import java.util.UUID;

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

import com.api.dronesetaecommerce.dto.EnderecoDto;
import com.api.dronesetaecommerce.exception.ObjectNotFoundException;
import com.api.dronesetaecommerce.model.ClienteModel;
import com.api.dronesetaecommerce.model.EnderecoModel;
import com.api.dronesetaecommerce.service.ClienteService;
import com.api.dronesetaecommerce.service.EnderecoService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<Page<EnderecoModel>> getAll(@PageableDefault(
															page = 0,
															size = 10,
															direction = Sort.Direction.ASC)
															Pageable pageable
															) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable("id") UUID id) {
		Optional<EnderecoModel> enderecoModelOptional = service.findById(id);
		if(!enderecoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageEnderecoNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(enderecoModelOptional.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid EnderecoDto enderecoDto) {
		EnderecoModel enderecoModel = new EnderecoModel();
		BeanUtils.copyProperties(enderecoDto, enderecoModel);
		
		addCliente(enderecoDto, enderecoModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(enderecoModel));
	}
	
	private void addCliente(EnderecoDto enderecoDto, EnderecoModel enderecoModel) {
		if(enderecoDto.getClienteId() != null) {
			Optional<ClienteModel> cliente = clienteService.findById(enderecoDto.getClienteId());
			if(cliente.isPresent()) {
				enderecoModel.setClienteId(cliente.get());
			}
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
										@RequestBody @Valid EnderecoDto enderecoDto) {
		Optional<EnderecoModel> enderecoModelOptional = service.findById(id);
		if(!enderecoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageEnderecoNotFound());
		}
		EnderecoModel enderecoModel = new EnderecoModel();
		BeanUtils.copyProperties(enderecoDto, enderecoModel);
		enderecoModel.setId(enderecoModelOptional.get().getId());
		
		addCliente(enderecoDto,enderecoModel);
		return ResponseEntity.status(HttpStatus.OK).body(service.save(enderecoModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) throws ObjectNotFoundException {
		Optional<EnderecoModel> enderecoModelOptional = service.findById(id);
		if(!enderecoModelOptional.isPresent()) {
			throw new ObjectNotFoundException(getMessageEnderecoNotFound());
		}
		service.delete(enderecoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessageEnderecoDeleted());
	}
	
	protected String getMessageEnderecoNotFound() {
		return "Endereço não encontrado";
	}
	
	protected String getMessageEnderecoDeleted() {
		return "Endereço deletado com sucesso!";
	}

}