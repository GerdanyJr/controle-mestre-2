package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.ProdutoAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Categoria;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.repository.ICategoriaRepository;
import com.github.gerdanyjr.controle_mestre_backend.repository.IProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final IProdutoRepository produtoRepository;
    private final ICategoriaRepository categoriaRepository;
    private final ProdutoAdapter produtoAdapter;

    public ProdutoService(
            IProdutoRepository produtoRepository,
            ICategoriaRepository categoriaRepository,
            ProdutoAdapter produtoAdapter
    ) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoAdapter = produtoAdapter;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProdutoResponse create(ProdutoRequest request) {
        Categoria foundCategoria = categoriaRepository
                .findById(request.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        Produto createdProduto = produtoRepository.save(produtoAdapter.toEntity(request, foundCategoria));

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
    public void delete(Long codigo) {
        Produto produto = produtoRepository
                .findById(codigo)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }

}
