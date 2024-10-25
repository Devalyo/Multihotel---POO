import java.util.ArrayList;

// Classe responsável por armazenar e administrar a classe quartos
public class Hotel {

    private String nome, endereco;
    private ArrayList<Quarto> quartos;

    public Hotel(String nome, String endereco)
    {
        this.nome = nome;
        this.endereco = endereco;
        quartos = new ArrayList<>();
    }

    public void adicionarQuarto(int numero, float preco, String tipo)
    {

        quartos.add(new Quarto(numero, preco, tipo));
    }

    public void removerQuarto(int numeroQuarto)
    {
        quartos.remove(numeroQuarto - 1);
    }

    

    public String getEndereco() {
        return endereco;
    }
    public String getNome() {
        return nome;
    }
    public ArrayList<Quarto> getQuartos() {
        return quartos;
    }

    
}