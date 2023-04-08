package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ProdutoAlterarPadraoService implements ProdutoAlterarService {
    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;

    private boolean EANInvalido(String EAN) {
        if (EAN.length() != 13) {
            return true;
        }

        int somaEAN = 0;

        String[] arrayStringEAN = EAN.split("");
        int[] arrayNumEAN = new int[arrayStringEAN.length];
        for (int i = 0; i < arrayStringEAN.length; i++) {
            arrayNumEAN[i] = Integer.parseInt(arrayStringEAN[i]);
        }

        for (int i = arrayNumEAN.length - 2; i > 0; i = i - 2) {
            somaEAN += arrayNumEAN[i] * 3;
            somaEAN += arrayNumEAN[i - 1];
        }

        return ((somaEAN + arrayNumEAN[arrayNumEAN.length - 1]) % 10) != 0;
    }

    public void EANValido(Produto produtoAlterado) {
        if (isNull(produtoAlterado.getCodigoBarra()) || EANInvalido(produtoAlterado.getCodigoBarra())) {
            throw new RuntimeException("Codigo de barra invalido");
        }
    }

    public void precoValido(Produto produtoAlterado) {
        if (produtoAlterado.getPreco() <= 0) {
            throw new RuntimeException("Preco invalido");
        }
    }

    public void nomeValido(Produto produtoAlterado) {
        if (isNull(produtoAlterado.getNome()) || produtoAlterado.getNome().isBlank()) {
            throw new RuntimeException("Nome invalido");
        }
    }

    public void fabricanteValido(Produto produtoAlterado) {
        if (isNull(produtoAlterado.getFabricante()) || produtoAlterado.getFabricante().isBlank()) {
            throw new RuntimeException("Fabricante invalido");
        }
    }

    @Override
    public Produto alterar(Produto produto) {
        EANValido(produto);
        precoValido(produto);
        nomeValido(produto);
        fabricanteValido(produto);

        return produtoRepository.update(produto);
    }
}
