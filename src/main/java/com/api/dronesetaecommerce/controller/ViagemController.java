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

import com.api.dronesetaecommerce.dto.ViagemDto;
import com.api.dronesetaecommerce.exception.ObjectNotFoundException;
import com.api.dronesetaecommerce.model.ViagemModel;
import com.api.dronesetaecommerce.service.ViagemService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/viagem")
public class ViagemController {
	
	@Autowired
	private ViagemService viagemService;
	
	@PostMapping
	public ResponseEntity<Object> saveViagem(@RequestBody @Valid ViagemDto viagemDto) {
		ViagemModel viagemModel = new ViagemModel();
		BeanUtils.copyProperties(viagemDto, viagemModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.viagemService.save(viagemModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<ViagemModel>> getAllViagem(
			@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable peageable) {
		return ResponseEntity.status(HttpStatus.OK).body(this.viagemService.findAll(peageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneViagem(@PathVariable(value = "id") UUID id) {
		Optional<ViagemModel> viagemModelOptional = this.viagemService.findById(id);
		if (!viagemModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageViagemNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(viagemModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteViagem(@PathVariable(value = "id") UUID id) throws ObjectNotFoundException {
		Optional<ViagemModel> viagemModelOptional = this.viagemService.findById(id);
		if (!viagemModelOptional.isPresent()) {
			throw new ObjectNotFoundException(getMessageViagemNotFound());
		}
		this.viagemService.delete(viagemModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessageViagemDeleted());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateViagem(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ViagemDto droneDto) {

		Optional<ViagemModel> viagemModelOptional = this.viagemService.findById(id);
		if (!viagemModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageViagemNotFound());
		}
		ViagemModel viagemModel = new ViagemModel();
		BeanUtils.copyProperties(droneDto, viagemModel);
		viagemModel.setViagemId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(this.viagemService.save(viagemModel));
	}

	
	protected String getMessageViagemNotFound() {
		return "Drone não encontrado";
	}

	protected String getMessageViagemDeleted() {
		return "Drone deletado com sucesso!";
	}

}
