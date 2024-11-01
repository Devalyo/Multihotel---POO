public class Quarto {
    private int numero;
    private float preco;
    private String tipo;
    private boolean isLivre;

    // Classe reponsável por seus atributos e gerenciar a sua condição de reserva

    public Quarto(int numero, float preco, String tipo)
    {
        this.numero = numero;
        this.preco = preco;
        this.tipo = tipo;
        this.isLivre = true;
    }


    public void reservar()
    {
        isLivre = false;
    }

    public void liberar()
    {
        isLivre = true;
    }

    public int getNumero() {
        return numero;
    }
    public float getPreco() {
        return preco;
    }
    public String getTipo() {
        return tipo;
    }
    public boolean getIsLivre()
    {
        return isLivre;
    }

    
}
