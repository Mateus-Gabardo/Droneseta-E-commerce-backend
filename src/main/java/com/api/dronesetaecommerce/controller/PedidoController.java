package com.api.dronesetaecommerce.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dronesetaecommerce.dto.PedidoDto;
import com.api.dronesetaecommerce.exception.ObjectNotFoundException;
import com.api.dronesetaecommerce.model.ClienteModel;
import com.api.dronesetaecommerce.model.EnderecoModel;
import com.api.dronesetaecommerce.model.PedidoModel;
import com.api.dronesetaecommerce.model.ProdutoModel;
import com.api.dronesetaecommerce.model.StatusPedido;
import com.api.dronesetaecommerce.service.ClienteService;
import com.api.dronesetaecommerce.service.EnderecoService;
import com.api.dronesetaecommerce.service.PedidoService;
import com.api.dronesetaecommerce.service.ProdutoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping
	public ResponseEntity<Object> savePedido(@RequestBody @Valid PedidoDto pedidoDto) {
		PedidoModel pedidoModel = new PedidoModel();
		Optional<ClienteModel> cliente = clienteService.findByCpf(pedidoDto.getCpf());
		if(!cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		Optional<EnderecoModel> endereco = enderecoService.findById(pedidoDto.getEnderecoId());
		if(endereco.isPresent()) {
			pedidoModel.setEndereco(endereco.get());
		}

		List<ProdutoModel> produtos = produtoService.findAll(pedidoDto.getProdutoId());	
		
		this.pedidoService.save(pedidoModel);

		pedidoModel.setProdutos(produtos);
		pedidoModel.setCliente(cliente.get());
		pedidoModel.setStatus(StatusPedido.AGUARDANDO_ENVIO);
		for(ProdutoModel produto : produtos) {
			produto.setQuantidade(produto.getQuantidade() - 1);
		}
		
		this.pedidoService.save(pedidoModel);		
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoModel);
	}
	
	@GetMapping
	public ResponseEntity<Page<PedidoModel>> getAllPedido(
				@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable peageable) {
		return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.findAll(peageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOnePedido(@PathVariable(value = "id") UUID id) {
		Optional<PedidoModel> pedidoModelOptional = this.pedidoService.findById(id);
		if (!pedidoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessagePedidoNotFound());
		}
		return ResponseEntity.status(HttpStatus.OK).body(pedidoModelOptional.get());
	}
	
	@GetMapping("/byCliente/{id}")
	public ResponseEntity<Object> getAllPedidosByCliente(@PathVariable(value = "id") UUID id, 
			@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable peageable)  {
		Optional<ClienteModel> clienteOptional = clienteService.findById(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findByClienteId(id, peageable));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") UUID id) throws ObjectNotFoundException {
		Optional<PedidoModel> pedidoModelOptional = this.pedidoService.findById(id);
		if (!pedidoModelOptional.isPresent()) {
			throw new ObjectNotFoundException(getMessagePedidoNotFound());
		}
		this.pedidoService.delete(pedidoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(getMessagePedidoDeleted());
	}
	
	@PostMapping("/confirmarPagamento/{id}")
	public ResponseEntity<Object> confirmarPagamento(@PathVariable(value = "id") UUID id) throws ObjectNotFoundException{
		Optional<PedidoModel> pedido = pedidoService.findById(id);
		if(!pedido.isPresent()) {
			throw new ObjectNotFoundException(getMessagePedidoNotFound());
		}
		if(pedido.get().getStatus() == StatusPedido.AGUARDANDO_PAGAMENTO) {
			pedido.get().setStatus(StatusPedido.AGUARDANDO_ENVIO);
			return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.save(pedido.get()));			
		}		
		return ResponseEntity.status(HttpStatus.OK).body("Pedido não esta Aguardando Pagamento");
	}
	
	@PostMapping("/updateStatus/{id}")
	public ResponseEntity<Object> updateStatusPedido(@PathVariable(value = "id") UUID id, 
													 @RequestBody @Valid PedidoDto pedidoDto) throws ObjectNotFoundException {
		Optional<PedidoModel> pedido = pedidoService.findById(id);
		if(!pedido.isPresent()) {
			throw new ObjectNotFoundException(getMessagePedidoNotFound());
		}
		pedido.get().setStatus(pedidoDto.getStatus());
		
		return ResponseEntity.status(HttpStatus.OK).body(this.pedidoService.save(pedido.get()));
	}
	
	protected String getMessagePedidoNotFound() {
		return "Pedido não encontrado";
	}

	protected String getMessagePedidoDeleted() {
		return "Pedido deletado com sucesso!";
	}
}
