package com.api.dronesetaecommerce.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.dronesetaecommerce.model.PedidoModel;
import com.api.dronesetaecommerce.model.ProdutoModel;
import com.api.dronesetaecommerce.repository.PedidoRepository;
import com.api.dronesetaecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	
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
	
	public List<ProdutoModel> findAll(List<UUID> idProdutos){
		return produtoRepository.findAllByProdutoIdIn(idProdutos);
	}
	

    public List<ProdutoModel> getProdutosMaisVendidos() {
        List<PedidoModel> pedidos = pedidoRepository.findAll();
        Map<ProdutoModel, Integer> vendasPorProduto = new HashMap<>();

        for (PedidoModel pedido : pedidos) {
            List<ProdutoModel> produtos = pedido.getProdutos();
            for (ProdutoModel produto : produtos) {
                int vendas = vendasPorProduto.getOrDefault(produto, 0);
                vendasPorProduto.put(produto, vendas + 1);
            }
        }

        List<Map.Entry<ProdutoModel, Integer>> entries = new ArrayList<>(vendasPorProduto.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<ProdutoModel> produtosMaisVendidos = new ArrayList<>();
        for (int i = 0; i < Math.min(10, entries.size()); i++) {
            produtosMaisVendidos.add(entries.get(i).getKey());
        }

        return produtosMaisVendidos;
    }
	
}
