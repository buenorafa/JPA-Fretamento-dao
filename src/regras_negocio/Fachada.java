package regras_negocio;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import daojpa.DAO;
import daojpa.DAOMotorista;
import daojpa.DAOVeiculo;
import daojpa.DAOViagem;
import modelo.Motorista;
import modelo.Veiculo;
import modelo.Viagem;

public class Fachada {
	private Fachada() {}
	
	private static DAOMotorista daomotorista = new DAOMotorista();
	private static DAOVeiculo daoveiculo = new DAOVeiculo();
	private static DAOViagem daoviagem = new DAOViagem();
	
	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}
	

	public static void cadastrarMotorista(String cnh, String nome) throws Exception {
		DAO.begin();
		Motorista motorista = daomotorista.read(cnh);
		//Verifica se o motorista já existe e lança uma exceção caso encontre
		if(motorista != null) {
			DAO.rollback();
			throw new Exception("Criar motorista - CNH já existe: " + cnh);
		}
		motorista = new Motorista(cnh, nome);
		daomotorista.create(motorista);
		DAO.commit();
		
	}

	public static void excluirMotorista(String cnh) throws Exception {
		DAO.begin();
		Motorista motorista = daomotorista.read(cnh);
		if (motorista ==null) {
			throw new Exception("Motorista não encontrado: " + cnh);
		}
		daomotorista.delete(motorista);
		DAO.commit();
	}

	
	public static List<Motorista> listarMotoristas(){
		return daomotorista.readAll();
	}
	
	public static Motorista localizarMotorista(String cnh){
		return daomotorista.read(cnh);
	}
	

	public static void cadastrarVeiculo(String placa, String modelo) throws Exception {
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if(veiculo != null) {
			DAO.rollback();
			throw new Exception("Criar veículo - Placa já existe: " + placa);
		}
		veiculo = new Veiculo(placa, modelo);
		daoveiculo.create(veiculo);
		DAO.commit();		
	}

	public static void excluirVeiculo(String placa) throws Exception {
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo == null) {
			throw new Exception("Veículo não encontrado: " + veiculo);
		}
		daoveiculo.delete(veiculo);
		DAO.commit();
	}


	public static List<Veiculo> listarVeiculos() {
        return daoveiculo.readAll();
    }
	
	public static Veiculo localizarVeiculo(String placa){
		return daoveiculo.read(placa);
	}
	
	private static Date criarData(String data) throws Exception {
		 String[] DATE_FORMATS = {
			        "dd-MM-yyyy",
			        "yyyy-MM-dd",
			        "MM/dd/yyyy",
			        "dd/MM/yyyy"
			    };
		 for (String format : DATE_FORMATS) {
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	                return dateFormat.parse(data);
	            } catch (ParseException e) {
	                // Ignorar e tentar o próximo formato
	            }
	        }
	        // Se nenhum formato for válido, retornar null ou lançar exceção
	        return null;
	}

	public static void cadastrarViagem(String data, String placa, String cnh, String destino) throws Exception {
		DAO.begin();

		//verifica se o motorista existe 
		Motorista motorista = daomotorista.read(cnh);
        if (motorista == null)
            throw new Exception("Cadastrar viagem - Motorista não encontrado! CNH: " + cnh); 
		
		
		//Verifica se o veiculo existe
		Veiculo veiculo = daoveiculo.read(placa);
		if(veiculo == null) {
			throw new Exception("Criar viagem - Veiculo não encontrado! Placa: "+ placa);
		} 
		
		//Formatação da data
		Date dataConv = criarData(data);
		if(dataConv == null) {
			throw new Exception("Cadastrar viagem - Formato de data inválido: "+ data);
		}
						

		String id = Viagem.geraId(dataConv, placa, cnh);

		//Verifica se viagem já existe
		Viagem viagem = daoviagem.read(id);
		if(viagem != null) {
			throw new Exception("Criar viagem - Viagem já existe! ID: " + id);
		}

		// Regra de negócio: não pode haver 2 viagens do mesmo carro na mesma data
		for(Viagem v : veiculo.getViagens()) {
				if( v.getData().equals(dataConv)) {
					// REGRA DE NEGÓCIO
					throw new Exception(String.format("Criar Viagem - Não foi possível cadastrar viagem! O veículo de placa %s já possui viagem nesta data: %s", placa, data));
				}						
			}
		
		viagem = new Viagem(dataConv, veiculo, motorista, destino);
		veiculo.adicionar(viagem);
		motorista.adicionar(viagem);
		daoviagem.create(viagem);
		DAO.commit();
	}

	public static void excluirViagem(String id) throws Exception {
		DAO.begin();
		//Verifica se a viagem já existe
		Viagem viagem = daoviagem.read(id);
		if (viagem == null) {
			throw new Exception("Excluir viagem - Essa viagem não existe! \nId: " + id);
		}

		Veiculo veiculo = viagem.getVeiculo();
		Motorista motorista = viagem.getMotorista();
		veiculo.remover(viagem);
		motorista.remover(viagem);
		daoveiculo.update(veiculo);
		daomotorista.update(motorista);
		daoviagem.delete(viagem);
		DAO.commit();
	}
	
	public static Viagem localizarViagem(String id) {
		return daoviagem.read(id);
	}

	//TO-DO: ALTERAR VIAGEM
	
	public static List<Viagem> listarViagens() {
		return daoviagem.readAll();
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	//			CONSULTAS
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	public static List<Viagem> viagensNaData(String data) throws Exception{	
		Date dataFormatada = criarData(data);
		List<Viagem> resultados =  daoviagem.viagensNaData(dataFormatada);
		return resultados;
	}

	public static List<Viagem> viagensPorPlacaECNH(String placa, String cnh){	
		List<Viagem> resultados =  daoviagem.viagensPorPlacaECNH(placa, cnh);
		return resultados;
	}

	public static List<Motorista>  maisDeNViagens(String n_as_string){
		int n = Integer.parseInt(n_as_string);
		List<Motorista> resultados =  daomotorista.maisDeNViagens(n);
		return resultados;
	}
}
