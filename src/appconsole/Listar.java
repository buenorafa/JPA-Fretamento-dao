package appconsole;

import modelo.Motorista;
import modelo.Veiculo;
import modelo.Viagem;
import regras_negocio.Fachada;

public class Listar {
	
	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de motoristas:");
			for(Motorista m: Fachada.listarMotoristas())
				System.out.println(m);

			System.out.println("\n---listagem de veiculos:");
			for(Veiculo v: Fachada.listarVeiculos())
				System.out.println(v);
			
			System.out.println("\n---listagem de viagens:");
			for(Viagem vv: Fachada.listarViagens())
				System.out.println(vv);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}
	

	public static void main(String[] args) {
		new Listar();
	}

}
