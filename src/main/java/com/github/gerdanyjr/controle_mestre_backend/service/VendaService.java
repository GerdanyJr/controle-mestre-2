package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.ProdutoVendaAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.VendaAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoVendaRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.VendaRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.VendaResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import com.github.gerdanyjr.controle_mestre_backend.repository.IProdutoRepository;
import com.github.gerdanyjr.controle_mestre_backend.repository.IProdutoVendaRepository;
import com.github.gerdanyjr.controle_mestre_backend.repository.IVendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {
    private final IProdutoVendaRepository produtoVendaRepository;
    private final IProdutoRepository produtoRepository;
    private final IVendaRepository vendaRepository;
    private final ProdutoVendaAdapter produtoVendaAdapter;
    private final VendaAdapter vendaAdapter;

    public VendaService(
            IProdutoVendaRepository produtoVendaRepository,
            IProdutoRepository produtoRepository,
            IVendaRepository vendaRepository,
            ProdutoVendaAdapter produtoVendaAdapter,
            VendaAdapter vendaAdapter
    ) {
        this.produtoVendaRepository = produtoVendaRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.produtoVendaAdapter = produtoVendaAdapter;
        this.vendaAdapter = vendaAdapter;
    }

    @Transactional(rollbackFor = Exception.class)
    public VendaResponse create(VendaRequest request) {
        List<ProdutoVenda> produtoVendas = new ArrayList<>();

        for (ProdutoVendaRequest produto : request.produtos()) {
            Produto foundProduto = produtoRepository
                    .findById(produto.id())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (foundProduto.getQuantidade() < produto.quantidade()) {
                throw new RuntimeException("Estoque de " + foundProduto.getNome() + " insuficiente");
            }

            foundProduto.setQuantidade(foundProduto.getQuantidade() - produto.quantidade());
            ProdutoVenda produtoVenda = produtoVendaAdapter.toEntity(produto, foundProduto);

            produtoRepository.save(foundProduto);
            produtoVendas.add(produtoVendaRepository.save(produtoVenda));
        }

        BigDecimal total = produtoVendas
                .stream()
                .map(pv -> pv.getValorUnitario().multiply(BigDecimal.valueOf(pv.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        Venda venda = vendaRepository.save(vendaAdapter.toEntity(request, total, produtoVendas));

        return vendaAdapter.toResponse(venda);
    }

}
