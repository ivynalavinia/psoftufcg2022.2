package mercadinho;

public class Fachada {
    private Service servico;

    public Fachada() {
        this.servico = new Service();
    }

    public void criaProduto(String nome, String fabricante, Integer preco) {
        servico.criaProduto(nome, fabricante, preco);
    }
}
