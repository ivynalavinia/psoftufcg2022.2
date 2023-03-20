package mercadinho;

import java.util.Date;

public class Lote {
    Produto produto;
    Date validade;
    int quantidade;

    public Lote(Produto produto, Date validade, int quantidade) {
        this.produto = produto;
        this.validade = validade;
        this.quantidade = quantidade;
    }
}
