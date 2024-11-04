package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoCarrinho;
import com.aplicacao.api.dto.DtoCarrinhoResponse;
import com.aplicacao.api.dto.produtoDTO.DtoNovoEstoque;
import com.aplicacao.api.model.Carrinho;
import com.aplicacao.api.model.ItemCarrinho;
import com.aplicacao.api.repository.CarrinhoRepository;
import com.aplicacao.api.repository.ClienteRepository;
import com.aplicacao.api.repository.ItemCarrinhoRepository;
import com.aplicacao.api.repository.ProdutoRepository;
import com.aplicacao.api.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarrinhoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    ClienteRepository clienteRepository;
    public ResponseEntity<CustomResponse<DtoCarrinhoResponse>> adicionarItens(DtoCarrinho dtoCarrinho, Long idCliente) {
          List<ItemCarrinho> itens = new ArrayList<>();

          var cliente = clienteRepository.findById(idCliente)
                  .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));

          var carrinho = carrinhoRepository.save(new Carrinho());
          carrinho.setCliente(cliente);


        for (var item:dtoCarrinho.itemCarrinho()) {
            var produto = produtoRepository.findById(item.idProduto())
                            .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

            if(item.quantidade() > produto.getEstoque()){
                throw new RuntimeException("Pedido acima do estoque");
            }


            var itemCarrinho = new ItemCarrinho();
            itemCarrinho.setCarrinho(carrinho);
            itemCarrinho.setProduto(produto);
            itemCarrinho.setQuantidade(item.quantidade());
            itemCarrinho.setValorUnitario(produto.getPreco());
            itens.add(itemCarrinho);

        }
        itemCarrinhoRepository.saveAll(itens);
        carrinho.setItensCarrinho(itens);

        return ResponseEntity.ok().body(new CustomResponse<>(null, "itens adicionados com sucesso"));


    }
}
