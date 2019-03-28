package com.CadFichaAnimal.pkg;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


/**
 * Session Bean implementation class mantemAnimalSB
 */
@Stateless
@LocalBean
@TransactionManagement(value=TransactionManagementType.BEAN)


public class mantemAnimalSB {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "mysql")
	private EntityManager entityManager;
	private UserTransaction transaction = null;
	
    public mantemAnimalSB() {
    	//System.out.println("**** VIB **** Entrei 222 no mantem animal SB em add Animal");
        // TODO Auto-generated constructor stub
    }

	public boolean addAnimal(String id, String nome) {
		
		Animal animal = new Animal(id, nome);
		
		//System.out.println("**** VIB **** Entrada addAnimal" + animal.getId() + " / " + animal.getNome());

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		//System.out.println("**** VIB **** Propiredades do EM" + entityManager.getProperties().toString());
		
		entityManager.persist(animal);
		
		commitTransaction();
		
		entityManager.close();
		JPAUtility.close();
		
		System.out.println("Entity saved.");
		
		return true;
	}

	public Animal consultaAnimal(int seq) {
		
		Animal animal = new Animal(seq, "", "");
		
		//System.out.println("**** VIB **** " + id.getId() + " / " + animal.getNome());

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		//System.out.println("**** VIB **** Propiredades do EM" + entityManager.getProperties().toString());
		
		Animal animalRetorno = entityManager.find(Animal.class, animal.getSeq());

		//System.out.println("**** VIB **** animal id = " + animalRetorno.getNome());
		//System.out.println("**** VIB **** animal nome = " + animalRetorno.getId());
		//System.out.println("**** VIB **** animal seq = " + animalRetorno.getSeq());
		
		
		commitTransaction();
		
		//System.out.println("**** VIB **** depois da consulta = " + animalRetorno.getNome());
		
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return animalRetorno;
	}

	public boolean updateAnimal(int seq, String id, String nome) {
		
		Animal animal = new Animal(seq, id, nome);
		
		//System.out.println("**** VIB **** " + id.getId() + " / " + animal.getNome());

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		//System.out.println("**** VIB **** Propiredades do EM" + entityManager.getProperties().toString());
		
		Animal animalRetorno = entityManager.find(Animal.class, animal.getSeq());

		animalRetorno.setId(id);
		animalRetorno.setNome(nome);
		
		//System.out.println("**** VIB **** animal id = " + animalRetorno.getNome());
		//System.out.println("**** VIB **** animal nome = " + animalRetorno.getId());
		//System.out.println("**** VIB **** animal seq = " + animalRetorno.getSeq());
		
		//Animal animalUpdate = entityManager.merge(animalRetorno);
		entityManager.merge(animalRetorno);
		
		commitTransaction();
		
		//System.out.println("**** VIB **** depois da consulta = " + animalUpdate.getNome());
		
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return true;
	}

	public boolean deleteAnimal(int seq) {
		
		Animal animal = new Animal(seq, "", "");
		
		//System.out.println("**** VIB **** " + id.getId() + " / " + animal.getNome());

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		//System.out.println("**** VIB **** Propiredades do EM" + entityManager.getProperties().toString());
		
		Animal animalRetorno = entityManager.find(Animal.class, animal.getSeq());
		
		//System.out.println("**** VIB **** animal id = " + animalRetorno.getNome());
		//System.out.println("**** VIB **** animal nome = " + animalRetorno.getId());
		//System.out.println("**** VIB **** animal seq = " + animalRetorno.getSeq());
		
		entityManager.remove(animalRetorno);
		
		commitTransaction();
		
		//System.out.println("**** VIB **** depois da consulta = " + animalUpdate.getNome());
		
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return true;
	}
	
	public List<Animal> listaAnimais() {
		
		//System.out.println("**** VIB **** Lista Fichas ");

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		TypedQuery<Animal> query =
				entityManager.createQuery("SELECT c FROM Animal c", Animal.class);
		
		List<Animal> results = query.getResultList();				
				
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		
		return results;
	}
	
	public boolean startTransaction() {
		transaction = null;
		
		try {
			transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			transaction.begin();
			System.out.println("**** VIB **** depois do transacrtion = " + transaction.getStatus());
			return true;
		} catch (NamingException | NotSupportedException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("**** VIB **** Erro begin = " + e.getMessage());
			return false;
		}
	}
	
	
	public boolean commitTransaction() {
		try {
			transaction.commit();
			System.out.println("**** VIB **** depois do commit = " + transaction.getStatus());
			return true;
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("**** VIB **** Erro commit = " + e.getMessage());
			return false;
		}

	}
}
