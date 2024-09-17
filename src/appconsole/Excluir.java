package appconsole;

import regras_negocio.Fachada;

public class Excluir {

    public Excluir() {
        try {
            Fachada.inicializar();
            System.out.println(">>> Deletando viagem");
            Fachada.excluirViagem("260607BBB70012223");
            System.out.println(">>> Viagem deletada");

            System.out.println(">>>Deletando veículo...");
            Fachada.excluirVeiculo("AAA5001");
            System.out.println("Veículo deletado.");
            
            System.out.println(">>>Deletando motorista...");
            Fachada.excluirVeiculo("CCC111");
            System.out.println("Motorista deletado.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Fachada.finalizar();
        System.out.println("\nFim do programa!");
    }

    public static void main(String[] args) {
        new Excluir();
    }
}
