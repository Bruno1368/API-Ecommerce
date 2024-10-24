package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoItemVenda;
import com.aplicacao.api.dto.DtoVenda;
import com.aplicacao.api.model.ItemVenda;
import com.aplicacao.api.model.Produto;
import com.aplicacao.api.model.Venda;
import com.aplicacao.api.repository.ProdutoRepository;
import com.aplicacao.api.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    VendaRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    public void cadastrarVenda(DtoVenda vendaDto) {
        Venda venda = new Venda(vendaDto);
        List<ItemVenda> itensVenda = new ArrayList<>();
        Produto produto = new Produto();
        try {
            for (DtoItemVenda itensDto : vendaDto.itens()) {
                ItemVenda item = new ItemVenda();
                item.setId(itensDto.id());
                item.setQuantidade(itensDto.quantidade());
                Optional<Produto> produtoOpitional = produtoRepository.findById(itensDto.idProduto());
                if (!produtoOpitional.isPresent()) {
                    throw new NoSuchElementException("Produto " + itensDto.idProduto() + " não encontrado");
                }

                produto = produtoOpitional.get();
                item.setProduto(produto);
                item.setVenda(venda);
                itensVenda.add(item);
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        venda.setItensVenda(itensVenda);
        repository.save(venda);
    }

}


//System.out.println("Produto " + produto + " não encontrado");


/*    public void cadastrarVenda(DtoVenda vendaDto){
        Venda venda = new Venda(vendaDto);
        List<ItemVenda> itensVenda = new ArrayList<>();

        for (DtoItemVenda itensDto:vendaDto.itens()) {
            ItemVenda item = new ItemVenda();
            item.setId(itensDto.id());
            item.setQuantidade(itensDto.quantidade());
            Produto produto = new Produto();
            try{
                Optional<Produto> produtoOpitional = produtoRepository.findById(itensDto.idProduto());
                produto = produtoOpitional.orElseThrow(() -> new ProdutoNaoEncontrado("Produto não encontrado"));
            }catch (ProdutoNaoEncontrado e){
                System.out.println(e.getMessage());
            }
            item.setProduto(produto);
            item.setVenda(venda);
            itensVenda.add(item);
            ;
        }

        venda.setItensVenda(itensVenda);
        repository.save(venda);

    }*/












/*

    Map<String, Produto> mapa = new HashMap<>();
                    mapa.put("eu", produto);
                            for (Produto a: Object.class.getSimpleName()) {

        }*/
