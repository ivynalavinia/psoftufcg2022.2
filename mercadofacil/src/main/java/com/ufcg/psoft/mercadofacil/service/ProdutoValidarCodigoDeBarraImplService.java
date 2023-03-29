package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoValidarCodigoDeBarraImplService implements ProdutoValidarCodigoDeBarraService {

    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;

    @Override
    public boolean validar (Produto produtoAlterado) {
        // todo
        return true;
    }
}
