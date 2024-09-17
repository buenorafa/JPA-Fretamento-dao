package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar(){

		//Veículo
		try {
			System.out.println(">>> Inicializando Fachada");
			Fachada.inicializar();

			System.out.println(">>> Cadastrando veículo");
			Fachada.cadastrarVeiculo("AAA5001", "Frontier");
            Fachada.cadastrarVeiculo("BBB7001", "Strada");
            Fachada.cadastrarVeiculo("CCC9001", "Picape");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Motorista
		try {
			System.out.println(">>> Cadastrando motorista");
			Fachada.cadastrarMotorista("1112", "João");
			Fachada.cadastrarMotorista("2223", "Madu");
			Fachada.cadastrarMotorista("3334", "Rafael");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Viagem
		try {
			System.out.println(">>> Cadastrando viagem");
			Fachada.cadastrarViagem("01/08/2024", "AAA5001", "1112", "Recife");
            Fachada.cadastrarViagem("30/07/2024", "BBB7001", "2223", "Valentina");
            Fachada.cadastrarViagem("06/09/2024", "CCC9001", "3334", "Jacumã");	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nFim do programa!");
	}

	public static void main(String[] args) {
		new Cadastrar();
	}
}