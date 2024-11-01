import java.time.LocalDate;

public class Reserva {
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataCheckIn, dataCheckOut;
    private int id;
    private static int contador = 1;

    public Reserva(Cliente cliente, Quarto quarto, LocalDate dataIn, LocalDate dataOut)
    {
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckIn = dataIn;
        this.dataCheckOut = dataOut;
        this.id = contador;
        contador++;
    }

    public boolean confirmarReserva()
    {
        if(this.quarto.getisLivre())
        {
            quarto.reservar();
            return true;
        }
        return false;
        
    }

    public void cancelarReserva()
    {
        quarto.liberar();
        cliente.getReservas().remove(this);
        System.out.println("Reserva Cancelada");

    }


    public Cliente getCliente() {
        return cliente;
    }
    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }
    public LocalDate getDataCheckOut() {
        return dataCheckOut;
    }
    public Quarto getQuarto() {
        return quarto;
    }
    public int getId() {
        return id;
    }

}
