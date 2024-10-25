import java.util.ArrayList;

public class Cliente {
    
    private String nome, email, telefone;
    private ArrayList<Reserva> reservas;


    public Cliente(String nome, String email, String telefone)
    {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        reservas = new ArrayList<>();
    }

    public boolean fazerReserva(Reserva reserva)
    {
        if(reserva.confirmarReserva())
        {
            reservas.add(reserva);
            return true;
        }
        else
        {
            return false;
        }

    }
    
    public void CancelarReserva(int index)
    {
        reservas.remove(index);

    }


    public String getName()
    {
        return this.nome;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getNome() {
        return nome;
    }
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    public String getTelefone() {
        return telefone;
    }
    
}