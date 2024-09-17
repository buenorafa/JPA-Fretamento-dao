package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Veiculo {

	@Id
	private String placa;
	private String modelo;
	
	@OneToMany(	mappedBy="veiculo", 
			cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
			orphanRemoval=true)	
	private List<Viagem> viagens = new ArrayList<>();
	
	public Veiculo() {}
	
	public Veiculo(String placa, String modelo) {
		this.placa = placa;
		this.modelo = modelo;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public List<Viagem> getViagens() {
	    return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
	    this.viagens = viagens;
	}
	
	
	public void adicionar(Viagem viagem) {
		viagens.add(viagem);
		
	}
	public void remover(Viagem viagem) {
		viagens.remove(viagem);
	}
	
	
	@Override 
	public String toString() { 
		
		String result = "\nVeiculo placa=" + placa + ",  modelo=" + modelo + "\n";
		
		if (viagens.size() > 1) {
			result+= "data das viagens: ";
		} else {
			result+= "data da viagem: ";
		}
		
		for (Viagem v : viagens) 
			result+= "\n" + v.getData();
 
		return result; 
	}
	
	
}
