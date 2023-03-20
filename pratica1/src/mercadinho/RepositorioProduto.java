package mercadinho;

import java.util.HashMap;
import java.util.Map;

public class RepositorioProduto {
    private Map<Integer, Produto> mapa;

    public RepositorioProduto() {
        this.mapa = new HashMap<>();
    }

    public void add(String nome, String fabricante, double preco) {
        Produto p = new Produto (nome, fabricante, preco);
        mapa.put(p.getId(), p);
    }
}
