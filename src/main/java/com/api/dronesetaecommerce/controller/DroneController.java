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

import com.api.dronesetaecommerce.dto.DroneDto;
import com.api.dronesetaecommerce.exception.ObjectNotFoundException;
import com.api.dronesetaecommerce.model.DroneModel;
import com.api.dronesetaecommerce.model.StatusDrone;
import com.api.dronesetaecommerce.service.DroneService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/drone")
public class DroneController {
	
	@Autowired
	private DroneService droneService;

	@PostMapping
	public ResponseEntity<Object> saveDrone(@RequestBody @Valid DroneDto droneDto) {
		DroneModel droneModel = new DroneModel();
		BeanUtils.copyProperties(droneDto, droneModel);
		droneModel.setStatus(StatusDrone.DISPONIVEL);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.droneService.save(droneModel));
	}

	@GetMapping
	public ResponseEntity<Page<DroneModel>> getAllDrone(
			@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable peageable) {
		return ResponseEntity.status(HttpStatus.OK).body(this.droneService.findAll(peageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneDrone(@PathVariable(value = "id") UUID id) {
		Optional<DroneModel> droneModelOptional = this.droneService.findById(id);
		if (!droneModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageDroneNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(droneModelOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDrone(@PathVariable(value = "id") UUID id) throws ObjectNotFoundException {
		Optional<DroneModel> droneModelOptional = this.droneService.findById(id);
		if (!droneModelOptional.isPresent()) {
			throw new ObjectNotFoundException(getMessageDroneNotFound());
		}
		this.droneService.delete(droneModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessageDroneDeleted());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateDrone(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid DroneDto droneDto) {

		Optional<DroneModel> droneModelOptional = this.droneService.findById(id);
		if (!droneModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageDroneNotFound());
		}
		DroneModel droneModel = new DroneModel();
		BeanUtils.copyProperties(droneDto, droneModel);
		droneModel.setDroneId(droneModelOptional.get().getDroneId());
		droneModel.setStatus(droneModelOptional.get().getStatus());
		return ResponseEntity.status(HttpStatus.OK).body(this.droneService.save(droneModel));
	}

	protected String getMessageDroneNotFound() {
		return "Drone não encontrado";
	}

	protected String getMessageDroneDeleted() {
		return "Drone deletado com sucesso!";
	}
}
