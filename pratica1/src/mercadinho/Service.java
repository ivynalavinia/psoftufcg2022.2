package mercadinho;

public class Service {

    RepositorioProduto repositorio;

    public Service() {
        this.repositorio = new RepositorioProduto();
    }

    public void criaProduto(String nome, String fabricante, double preco) {
        repositorio.add(nome, fabricante, preco);
    }

}
