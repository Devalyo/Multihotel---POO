import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/// Classe responsável pela inicialização de todos os componentes necessários do sistema, pela validação de input, e pela impressão de mensagens de feedback.
public class Sistema {
    
    private ArrayList<Hotel> hoteis;
    private ArrayList<Cliente> clientes;
    private ArrayList<Admin> admins;

    /// Inicializa as ArrayLists e uma instancia de um administrador
    public Sistema()
    {
        hoteis = new ArrayList<>();
        clientes = new ArrayList<>();
        admins = new ArrayList<>();
        
        admins.add(new Admin("Master"));
    }
/// Scanner instanciado em Main foi passado para todas as classes que fazem seu uso para facilitar sua administração.
    public void adicionarHotel(Scanner in)
    {
        System.out.println("Nome: ");
        String nome = in.next();
        /// Limpa o buffer
        in.nextLine();
        System.out.println("Endereço: ");
        String endereco = in.nextLine();

        if(buscaHotel(hoteis, nome) == null)
        {
            admins.get(0).adicionarHotel(hoteis, nome, endereco);
            System.out.println("\nHotel Adicionado com Sucesso\n");
        }
        else
        {
            System.out.println("Nome já existe");
        }



    }

    public void listarHoteis()
    {
        if(hoteis.size() == 0)
        {
            System.out.println("Não existem hoteis no sistema");
            return;
        }

        for(Hotel hotel : hoteis)
        {
            System.out.printf("\n----- Hotel ------");
            System.out.printf("\n Nome: %s", hotel.getNome());
            System.out.printf("\n Endereço: %s", hotel.getEndereco());
            System.out.printf("\n------------------\n");

        }

    }

    public void adicionarQuarto(Scanner in)
    {

        if(hoteis.size() == 0)
        {
            System.out.println("Não há hoteis no sistema, adicione um antes.");
            return;
        }

        Hotel hotel = selecionarHotel(hoteis, in);

        System.out.println("Numero do quarto: ");
        int numero = in.nextInt();
        System.out.println("Preço do quarto: ");
        float preco = in.nextFloat();
        System.out.println("Tipo do quarto: ");
        String tipo = in.next();

        hotel.adicionarQuarto(numero, preco, tipo);

        System.out.println("Quarto adicionado com sucesso");

        
    }

    public void listarQuartosDisponiveis(Scanner in)
    {

        System.out.println("Nome do hotel: ");
        String nome = in.next();
        Hotel hotel = buscaHotel(hoteis, nome);
        if(hotel == null)
        {
            System.out.println("Hotel não existe");
            return;
        }

        ArrayList<Quarto> quartos = hotel.getQuartos();

        for(Quarto quarto : quartos)
        {
            if(quarto.isLivre) 
            {
                System.out.println("----------------");
                System.out.println("Numero: " + quarto.getNumero());
                System.out.println("Preço: " + quarto.getPreco());
                System.out.println("Tipo: " + quarto.getTipo());
                System.out.println("----------------");
            }
        }
        

    }


    public void fazerReserva(Scanner in)
    {

        if(hoteis.size() == 0)
        {
            System.out.println("Adicione um hotel antes de fazer uma reserva");
            return;
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Nome do Cliente: ");
        String nomeCliente = in.next();

        Cliente cliente = buscarCliente(nomeCliente);
        if(cliente == null)
        {
            cliente = adicionarCliente(nomeCliente, in);
        }
        clientes.add(cliente);

        if(hoteis.size() == 0)
        {
            System.out.println("Não existem hoteis");
            return;
        }
        Hotel hotel = selecionarHotel(hoteis, in);

        if(hotel.getQuartos().size() == 0)
        {
            System.out.println("Não Existem Quartos no Hotel");
            return;
        }
        Quarto quarto = selecionarQuartos(hotel, in);

        System.out.println("Data de check in: (dd/MM/yyyy)");
        String checkInTexto = in.next();
        System.out.println("Data de check out: (dd/MM/yyyy)");
        String checkOutTexto = in.next();

        LocalDate checkIn = LocalDate.parse(checkInTexto, formato);
        LocalDate checkOut = LocalDate.parse(checkOutTexto, formato);

        Reserva reserva = new Reserva(cliente, quarto, checkIn, checkOut);

        
        if(cliente.fazerReserva(reserva))
        {
            System.out.println("reserva efetuada com sucesso");
            return;
        }
        else
        {
            System.out.println("Quarto não disponivel");
            return;
        }


    }


    public void cancelarReserva(Scanner in)
    {

        System.out.println("Numero da reserva: ");
        int id = in.nextInt();

        for(Cliente cliente : clientes)
        {
            ArrayList<Reserva> reservas = cliente.getReservas();
            for(Reserva reserva : reservas)
            {
                if(reserva.getId() == id)
                {
                    reserva.cancelarReserva();
                    return;
                }
            }
        }

        System.out.println("Numero de reserva não existe");
        return;
    }

    public void listarReservasCliente(Scanner in)
    {

        System.out.println("Nome do Cliente: ");
        String nome = in.next();
        Cliente cliente = buscarCliente(nome);
        if(cliente == null)
        {
            System.out.println("Cliente não existe");
            return;
        }
        ArrayList<Reserva> reservas = cliente.getReservas();
        if(reservas.size() == 0)
        {
            System.out.println("Não há reservas");
        }

        for(Reserva reserva : reservas)
        {
            System.out.println("----------------");
            System.out.println("Numero: " + reserva.getId());
            System.out.println("Preço: " + reserva.getQuarto().getPreco());
            System.out.println("Tipo: " + reserva.getQuarto().getTipo());
            System.out.println("----------------");
        }

    }



   private Cliente buscarCliente(String nome)
    {
        for(Cliente cliente : clientes)
        {
            if(cliente.getName().equalsIgnoreCase(nome))
            {
                return cliente;
            }
        }

        return null;

    }

    private Cliente adicionarCliente(String nome, Scanner in)
    {

        System.out.println("Email do cliente: ");
        String emailCliente = in.next();
        System.out.println("Telefone do cliente: ");
        String telefoneCliente = in.next();

        return new Cliente(nome, emailCliente, telefoneCliente);


    }

    private Quarto selecionarQuartos(Hotel hotel, Scanner in)
    {
        int escolha = 0;
        ArrayList<Quarto> quartos = hotel.getQuartos();

        
        while(escolha < 1 || escolha > quartos.size())
        {
            for(Quarto quarto : quartos)
            {
                System.out.printf("\n------------------\n");
                System.out.printf("\n Quarto %d",  quarto.getNumero());
                if(!quarto.isLivre)
                {
                    System.out.printf(" !INDISPONÍVEL!");
                }
                System.out.printf("\n------------------\n");
            }
            System.out.println("Selecione o quarto (1-9)");
            escolha = in.nextInt();

        } 

        return quartos.get(escolha - 1);

    } 


   
    public static Hotel selecionarHotel(ArrayList<Hotel> hoteis, Scanner in)
    {
        int escolha = 0;
        
        while(escolha < 1 || escolha > hoteis.size())
        {
            int index = 0;
            for(Hotel hotel : hoteis)
            {
                System.out.printf("\n------------------\n");
                System.out.printf("\n Hotel %d: %s", index + 1, hotel.getNome());
                System.out.printf("\n------------------\n");
                index++;
            }
            System.out.println("\nSelecione o hotel (1-9)");
            escolha = in.nextInt();
        } 

        return hoteis.get(escolha - 1);

    } 

     public static Hotel buscaHotel(ArrayList<Hotel> hoteis, String nome)
    {

        for(Hotel hotel : hoteis)
        {
            if(hotel.getNome().equalsIgnoreCase(nome))
            return hotel;
        }
        
        return null;

    }
}