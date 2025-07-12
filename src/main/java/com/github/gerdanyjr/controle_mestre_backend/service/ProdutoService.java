package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.ProdutoAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.repository.IProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final IProdutoRepository produtoRepository;
    private final ProdutoAdapter produtoAdapter;

    public ProdutoService(
            IProdutoRepository produtoRepository,
            ProdutoAdapter produtoAdapter
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoAdapter = produtoAdapter;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProdutoResponse cadastrar(ProdutoRequest request) {
        Produto createdProduto = produtoRepository.save(produtoAdapter.toEntity(request));

        return produtoAdapter.toResponse(createdProduto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> findAll() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos
                .stream()
                .map(produtoAdapter::toResponse)
                .toList();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long codigo) {
        Produto produto = produtoRepository
                .findById(codigo)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }

}
