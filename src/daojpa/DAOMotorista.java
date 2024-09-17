/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Motorista;

public class DAOMotorista extends DAO<Motorista>{

	public Motorista read (Object chave){
		try{
			String cnh = (String) chave;
			TypedQuery<Motorista> q = manager.createQuery("select m from Motorista m where m.cnh=:cnh", Motorista.class);
			q.setParameter("cnh", cnh);
			return q.getSingleResult();

		}catch(NoResultException e){
			return null;
		}
	}
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	//			Consultas de Motorista
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	public List<Motorista> maisDeNViagens(int n) {
	    TypedQuery<Motorista> q = manager.createQuery(
	        "SELECT m FROM Motorista m WHERE m.viagens.size > :n ORDER BY m.id", 
	        Motorista.class
	    );
	    q.setParameter("n", n);
	    return q.getResultList();
	}

}

