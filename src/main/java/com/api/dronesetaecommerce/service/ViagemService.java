package com.api.dronesetaecommerce.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.dronesetaecommerce.dto.ViagemDto;
import com.api.dronesetaecommerce.model.PedidoModel;
import com.api.dronesetaecommerce.model.StatusViagem;
import com.api.dronesetaecommerce.model.ViagemModel;
import com.api.dronesetaecommerce.repository.ViagemRepository;

@Component
@EnableScheduling
@Service
public class ViagemService {
	
	@Autowired
	private ViagemRepository viagemRepository;
	
	public ViagemModel save(ViagemModel viagem) {
		return this.viagemRepository.save(viagem);
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
	
	public ViagemModel criarViagem(PedidoModel pedido, int qtdCamisetas) {
		ViagemDto viagemDto = new ViagemDto();
		viagemDto.setDataHora(new Date());
		viagemDto.setStatus(StatusViagem.ABERTO);
		viagemDto.setEnderecoId(pedido.getEndereco().getId());
		viagemDto.setQtdCamisetas(qtdCamisetas);
		ViagemModel viagemModel = new ViagemModel();
		BeanUtils.copyProperties(viagemDto, viagemModel);
		
		viagemModel.setPedido(pedido);
		viagemModel.setEnderecoModel(pedido.getEndereco());
		
		viagemRepository.save(viagemModel);
		return viagemModel;
	}

}
