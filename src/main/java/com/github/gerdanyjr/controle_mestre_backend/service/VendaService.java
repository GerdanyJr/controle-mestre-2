package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.ProdutoVendaAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.VendaAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoVendaRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.VendaRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.VendaResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.NaoEncontradoException;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.QuantidadeInsuficienteException;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.*;
import com.github.gerdanyjr.controle_mestre_backend.repository.*;
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
    private final IFuncionarioRepository funcionarioRepository;
    private final IClienteRepository clienteRepository;
    private final ProdutoVendaAdapter produtoVendaAdapter;
    private final VendaAdapter vendaAdapter;

    public VendaService(
            IProdutoVendaRepository produtoVendaRepository,
            IProdutoRepository produtoRepository,
            IVendaRepository vendaRepository,
            ProdutoVendaAdapter produtoVendaAdapter,
            VendaAdapter vendaAdapter,
            IFuncionarioRepository funcionarioRepository, IClienteRepository clienteRepository
    ) {
        this.produtoVendaRepository = produtoVendaRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.produtoVendaAdapter = produtoVendaAdapter;
        this.vendaAdapter = vendaAdapter;
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public VendaResponse criar(VendaRequest request) {
        List<ProdutoVenda> produtoVendas = new ArrayList<>();

        funcionarioRepository
                .findById(request.funcionarioId())
                .orElseThrow(() -> new NaoEncontradoException("Funcionário não encontrado com id: " + request.funcionarioId()));

        clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado com id " + request.clienteId()));

        for (ProdutoVendaRequest produto : request.produtos()) {
            Produto foundProduto = produtoRepository
                    .findById(produto.id())
                    .orElseThrow(() -> new NaoEncontradoException("Produto não encontrado"));

            if (foundProduto.getQuantidade() < produto.quantidade()) {
                throw new QuantidadeInsuficienteException("Estoque de " + foundProduto.getNome() + " insuficiente");
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

    @Transactional(readOnly = true)
    public VendaResponse buscarPorId(Long id) {
        Venda foundVenda = vendaRepository
                .findById(id).orElseThrow(() -> new NaoEncontradoException("Venda não encontrada!"));

        return vendaAdapter.toResponse(foundVenda);
    }

    public List<VendaResponse> buscarTodos() {
        return vendaRepository.findAll().stream().map(vendaAdapter::toResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) {
        Venda foundVenda = vendaRepository
                .findById(id).orElseThrow(() -> new NaoEncontradoException("Venda não encontrada!"));

        vendaRepository.delete(foundVenda);
    }

}
