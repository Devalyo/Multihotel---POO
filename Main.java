import java.util.Scanner;

/// Classe unicamente responsável pela interface com usuário e pela instancia do Scanner
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();
        int opcao;

        do {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Adicionar Novo Hotel");
            System.out.println("2. Listar Todos os Hotéis");
            System.out.println("3. Adicionar Quarto a um Hotel");
            System.out.println("4. Listar Quartos Disponíveis em um Hotel");
            System.out.println("5. Fazer Reserva");
            System.out.println("6. Cancelar Reserva");
            System.out.println("7. Listar Reservas de um Cliente");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = in.nextInt();

            switch (opcao) {
                case 1:
                    sistema.adicionarHotel(in);
                    break;
                case 2:
                    sistema.listarHoteis();
                    break;
                case 3:
                    sistema.adicionarQuarto(in);
                    break;
                case 4:
                    sistema.listarQuartosDisponiveis(in);
                    break;
                case 5:
                    sistema.fazerReserva(in);
                    break;
                case 6:
                    sistema.cancelarReserva(in);
                    break;
                case 7:
                    sistema.listarReservasCliente(in);
                    break;
                case 8:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 8);

        in.close();
    }
}