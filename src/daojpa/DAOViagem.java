/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;


import java.util.Date;
import java.util.List;

//import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Viagem;

public class DAOViagem extends DAO<Viagem> {

	public Viagem read(Object chave) {
		try {
			String id = (String) chave;
			TypedQuery<Viagem> q = manager.createQuery("select v from Viagem v where v.id = :id ",
					Viagem.class);
			q.setParameter("id", id);

			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	// --------------------------------------------
	// consultas
	// --------------------------------------------
//
//	public List<Viagem> readAll(String digitos) {
//		TypedQuery<Viagem> q = manager.createQuery(
//				"select v from Viagem v LEFT JOIN FETCH t.pessoa  where t.numero like :x order by t.numero",
//				Viagem.class);
//		q.setParameter("x", "%" + digitos + "%");
//		return q.getResultList();
//	}
	public List<Viagem> viagensNaData(Date data) {
	    TypedQuery<Viagem> q = manager.createQuery(
	        "SELECT v FROM Viagem v WHERE v.data = :data", 
	        Viagem.class
	    );
	    q.setParameter("data", data);
	    return q.getResultList();
	}
	
	public List<Viagem> viagensPorPlacaECNH(String placa, String cnh) {
	    TypedQuery<Viagem> q = manager.createQuery(
	        "SELECT v FROM Viagem v WHERE v.veiculo.placa = :placa AND v.motorista.cnh = :cnh", 
	        Viagem.class
	    );
	    q.setParameter("placa", placa);
	    q.setParameter("cnh", cnh);
	    return q.getResultList();
	}
	
}
