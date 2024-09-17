package appconsole;

import modelo.Motorista;
import modelo.Viagem;
import regras_negocio.Fachada;

public class Consultar {

    public Consultar() {
        try {
            Fachada.inicializar();

            String data = "30/07/2024";
            String placa = "AAA5002";
            String cnh = "1113";

            System.out.println(">>> Consultas \n");

            System.out.println("\n >>> Viagens para a data " + data);
            for (Viagem v : Fachada.viagensNaData(data))
                System.out.println(v);

            System.out.println("\n >>> Viagens com placa " + placa + " e CNH " + cnh);
            for (Viagem v : Fachada.viagensPorPlacaECNH(placa, cnh))
                System.out.println(v);

            System.out.println("\n >>> Motoristas com mais de 1 viagem");
            for (Motorista m : Fachada.maisDeNViagens("1"))
                System.out.println(m);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Fachada.finalizar();
        System.out.println("\nFim do programa!");
    }

    public static void main(String[] args) {
        new Consultar();
    }
}