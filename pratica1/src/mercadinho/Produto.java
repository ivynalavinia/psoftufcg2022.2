package mercadinho;

public class Produto {
    private Integer id;
    private String nome;
    private String fabricante;
    private double preco;

    public Produto(String nome, String fabricante, double preco) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.preco = preco;
        this.id = this.hashCode();
    }

    public Integer getId() {
        return this.id;
    }
}
