package modelo;

import java.text.SimpleDateFormat;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Viagem {
	@Id
	private String id;
	private Date data;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}) 
	@JoinColumn(name="veiculo_placa")
	private Veiculo veiculo;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}) 
	private Motorista motorista;
	private String destino;
	
	public Viagem() {}

	public Viagem(Date data, Veiculo veiculo, Motorista motorista, String destino) {
		this.data = data;
		this.veiculo = veiculo;
		this.motorista = motorista;
		this.destino = destino;
		this.id = geraId(data, veiculo.getPlaca(), motorista.getCnh());
	}

	public static String geraId(Date data, String placa, String cnh) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyMMdd");
		String formattedDate = dateFormatter.format(data);
		return formattedDate + placa + cnh;
	}

	public String getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

//    public void setData(Date data) {
//        this.data = data;
//        this.id = geraId(data, veiculo.getPlaca(), motorista.getCnh());  // Atualiza o ID quando a data muda
//    }

	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Motorista getMotorista() {
		return motorista;
	}
	
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	// Método para obter a data como uma string no formato dd-MM-yyyy
	public String getDataAsString(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(data);
	}
	
	@Override public String toString() { 
    String dataFormatada = getDataAsString(data);
	 
	return "Viagem [id=" + id + ", data=" + dataFormatada + ", destino=" +
	destino + ", veiculo=" + veiculo.getPlaca() + ", motorista=" + motorista.getNome() + "]"; }
	 
}
