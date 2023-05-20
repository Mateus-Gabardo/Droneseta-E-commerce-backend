package com.api.dronesetaecommerce.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dronesetaecommerce.dto.PedidoDto;
import com.api.dronesetaecommerce.dto.ViagemDto;
import com.api.dronesetaecommerce.model.DroneModel;
import com.api.dronesetaecommerce.model.PedidoModel;
import com.api.dronesetaecommerce.model.StatusDrone;
import com.api.dronesetaecommerce.model.StatusPedido;
import com.api.dronesetaecommerce.model.StatusViagem;
import com.api.dronesetaecommerce.model.ViagemModel;
import com.api.dronesetaecommerce.repository.DroneRepository;
import com.api.dronesetaecommerce.repository.PedidoRepository;
import com.api.dronesetaecommerce.repository.ViagemRepository;

@Service
public class GeneralService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ViagemRepository viagemRepository; 
	
	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	private ViagemService viagemService;
	
	public void validarPedidos() {
		List<PedidoModel> pedidosEmAberto = pedidoRepository.findByStatus("AGUARDANDO_ENVIO");
		
		for(PedidoModel pedido : pedidosEmAberto) {			
			if(pedido.getProdutos().size() > 0) {
				int quantidade = pedido.getProdutos().size();
				do {
					if(quantidade > 10) {
						viagemService.criarViagem(pedido, 10);
						quantidade -= 10;
					} else {
						viagemService.criarViagem(pedido, quantidade);
						pedido.setStatus(StatusPedido.SAIU_ENTREGA);
						pedidoRepository.save(pedido);
						quantidade = 0;
					}
				} while (quantidade != 0);
			}
		}
		
		List<PedidoModel> pedidosEmEntrega = pedidoRepository.findByStatus("SAIU_ENTREGA");
		for(PedidoModel pedido : pedidosEmEntrega) {
			
			List<ViagemModel> viagens = viagemRepository.findByPedidoID(pedido.getPedidoId());
			boolean finalizado = true;
			for(ViagemModel viagem : viagens) {
				if(!viagem.getStatus().equals(StatusViagem.FINALIZADO)) {
					finalizado = false;
				}
			}
			if(!viagens.isEmpty() && finalizado == true) {
				PedidoDto pedidoDto = new PedidoDto();
				BeanUtils.copyProperties(pedido, pedidoDto);
				pedidoDto.setStatus(StatusPedido.FINALIZADO);
				BeanUtils.copyProperties(pedidoDto, pedido);
				pedidoRepository.save(pedido);
			}
		}	
	}
	
	
	public void validarViagens() {
		List<ViagemModel> viagensEmAberto = viagemRepository.findByStatus("ABERTO");
		
		for(ViagemModel viagem : viagensEmAberto) {
			List<DroneModel> dronesDisponiveis = droneRepository.findByStatus("DISPONIVEL");
			
			if(dronesDisponiveis.size() == 0)
				break;
				
			ViagemDto viagemDto = new ViagemDto();
			BeanUtils.copyProperties(viagem, viagemDto);
			viagemDto.setDroneId(dronesDisponiveis.get(0).getDroneId());
			viagemDto.setStatus(StatusViagem.EM_ANDAMENTO);
			viagemDto.setStatusUpdate(new Date());
			BeanUtils.copyProperties(viagemDto, viagem);
			addDrone(viagemDto, viagem);
							
			PedidoModel pedido = new PedidoModel();
			pedido = pedidoRepository.getById(viagem.getPedido().getPedidoId());
			PedidoDto pedidoDto = new PedidoDto();
			BeanUtils.copyProperties(pedido, pedidoDto);
			pedidoDto.setStatus(StatusPedido.SAIU_ENTREGA);
			BeanUtils.copyProperties(pedidoDto, pedido);
			pedidoRepository.save(pedido);
		}
		
		List<ViagemModel> viagensEmAndamento = viagemRepository.findByStatus("EM_ANDAMENTO");
		
		
		for(ViagemModel viagem : viagensEmAndamento) {
			
			if (viagem.getStatusUpdate() == null)
				continue;
			
			LocalDateTime dataViagem = viagem.getStatusUpdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	        ZonedDateTime dataInicial = dataViagem.atZone(ZoneId.systemDefault());
			ZonedDateTime dataFinal = ZonedDateTime.now();
			long diferenca = ChronoUnit.MINUTES.between(dataInicial, dataFinal);
			
			//Diferença de 1 minutos para teste. O correto é 60 minutos
			if(diferenca >= 1) {
				ViagemDto viagemDto = new ViagemDto();
				BeanUtils.copyProperties(viagem, viagemDto);
				viagemDto.setStatus(StatusViagem.FINALIZADO);
				viagemDto.setStatusUpdate(new Date());
				
				BeanUtils.copyProperties(viagemDto, viagem);
				viagemService.save(viagem);
				DroneModel drone = viagem.getDrone();
				drone.setStatus(StatusDrone.DISPONIVEL);
				droneRepository.save(drone);
			}
		}
	}
	
	private void addDrone(ViagemDto viagemDto, ViagemModel viagemModel) {
		if(viagemDto.getDroneId() != null) {
			Optional<DroneModel> droneOption = droneRepository.findById(viagemDto.getDroneId());
			if(droneOption.isPresent()) {
				DroneModel drone = droneOption.get();
				viagemModel.setDrone(drone);
				viagemService.save(viagemModel);
				drone.setStatus(StatusDrone.OCUPADO);
				droneRepository.save(drone);
			}
		}
	}	
	
}
