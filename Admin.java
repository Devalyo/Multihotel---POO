import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private String nome;
    private int id;
    private static int counter = 1;


    public Admin(String nome)
    {
        this.nome = nome;
        this.id = counter;
        counter++;
    }

    public boolean adicionarHotel(ArrayList<Hotel> hoteis, String nome, String endereco)
    {
        Hotel hotel = new Hotel(nome, endereco);
        hoteis.add(hotel);
        return true;

    }

    public void removerHotel(ArrayList<Hotel> hoteis, String nome)
    {
        if(hoteis.size() == 0)
        {
            System.out.println("Não existem hoteis no sistema");
        }
        Scanner in = new Scanner(System.in);
        Hotel hotel = Sistema.selecionarHotel(hoteis, in);
        hoteis.remove(hotel);
    }

    public static int getCounter() {
        return counter;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
}