/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Veiculo;

public class DAOVeiculo extends DAO<Veiculo>{


	public Veiculo read (Object chave){
		try{
			String placa = (String) chave;
			TypedQuery<Veiculo> q = manager.createQuery("select v from Veiculo v where v.placa=:placa",Veiculo.class);
			q.setParameter("placa", placa);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Veiculo> readAll(){
		TypedQuery<Veiculo> q = manager.createQuery("select v from Veiculo v LEFT JOIN FETCH v.viagens order by v.placa", Veiculo.class);
		return  q.getResultList();
	}
	
}

