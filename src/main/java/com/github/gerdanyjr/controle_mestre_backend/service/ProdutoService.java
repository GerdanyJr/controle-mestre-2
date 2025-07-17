package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.ProdutoAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.ConflitoException;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.EntidadeEmUsoException;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.NaoEncontradoException;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Categoria;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import com.github.gerdanyjr.controle_mestre_backend.repository.ICategoriaRepository;
import com.github.gerdanyjr.controle_mestre_backend.repository.IProdutoRepository;
import com.github.gerdanyjr.controle_mestre_backend.repository.IProdutoVendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final IProdutoRepository produtoRepository;
    private final IProdutoVendaRepository produtoVendaRepository;
    private final ICategoriaRepository categoriaRepository;
    private final ProdutoAdapter produtoAdapter;

    public ProdutoService(
            IProdutoRepository produtoRepository,
            IProdutoVendaRepository produtoVendaRepository,
            ICategoriaRepository categoriaRepository,
            ProdutoAdapter produtoAdapter
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoVendaRepository = produtoVendaRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoAdapter = produtoAdapter;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProdutoResponse criar(ProdutoRequest request) {
        Categoria foundCategoria = categoriaRepository
                .findById(request.categoriaId())
                .orElseThrow(() -> new NaoEncontradoException("Categoria não encontrada!"));

        produtoRepository.findByEan(request.ean()).ifPresent(produto -> {
            throw new ConflitoException("Ean já cadastrado");
        });

        Produto createdProduto = produtoRepository.save(produtoAdapter.toEntity(request, foundCategoria));

        return produtoAdapter.toResponse(createdProduto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos
                .stream()
                .map(produtoAdapter::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(Long id) {
        Produto foundProduto = produtoRepository
                .findById(id).orElseThrow(() -> new NaoEncontradoException("produto não encontrada!"));

        return produtoAdapter.toResponse(foundProduto);
    }


    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long codigo) {
        Produto produto = produtoRepository
                .findById(codigo)
                .orElseThrow(() -> new NaoEncontradoException("Produto não encontrado"));

        List<ProdutoVenda> allByProdutoId = produtoVendaRepository.findAllByProdutoId(produto.getCodigo());

        if (!allByProdutoId.isEmpty()) {
            throw new EntidadeEmUsoException("Este produto possui vendas vinculadas e não pode ser removido");
        }

        produtoRepository.delete(produto);
    }

}
