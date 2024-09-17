package modelo; 

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Motorista {
	@Id
	private String cnh;
	private String nome;
	
	@OneToMany(	mappedBy="motorista", 
			cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
			orphanRemoval=true)	
	private List<Viagem> viagens = new ArrayList<>();
	
	public Motorista() {}
	public Motorista(String cnh, String nome) {
		this.cnh = cnh;
		this.nome = nome;
	}
	
	public String getCnh() {
		return cnh;
	}
	
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Viagem> getViagens(){
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
		String result = "\nMotorista nome=" + nome + ", cnh=" + cnh + "\n";
		
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
