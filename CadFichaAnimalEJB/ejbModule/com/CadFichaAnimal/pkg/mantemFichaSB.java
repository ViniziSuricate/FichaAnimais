package com.CadFichaAnimal.pkg;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


/**
 * Session Bean implementation class mantemFichaSB
 */
@Stateless
@LocalBean
@TransactionManagement(value=TransactionManagementType.BEAN)


public class mantemFichaSB {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "mysql")
	private EntityManager entityManager;

	private UserTransaction transaction = null;
	
    public mantemFichaSB() {
        // TODO Auto-generated constructor stub
    }

	public int addFicha(String txtObs) {
		
		Ficha ficha = new Ficha(txtObs);
		Date data = new Date();
		ficha.setData(data);
		ficha.setStatus("A");
		
		//System.out.println("**** VIB **** Entrada addFicha: " + ficha.getData() + " / " + ficha.getObs() + " / " + ficha.getStatus());

		startTransaction();
		
		//System.out.println("**** VIB **** Antes do getEntity");
		entityManager = JPAUtility.getEntityManager();
		
		//System.out.println("**** VIB **** Antes do persist ficha" + ficha.toString());
		//System.out.println("**** VIB **** Antes do persist ficha" + ficha.getId());
		
		//commit da ficha
		try {

			entityManager.persist(ficha);

        } catch (IllegalStateException ie) {

        	System.out.println("Failed to update " + ficha + "\n" + ie.getMessage());
        	entityManager.close();
    		JPAUtility.close();
    		return 0;

        } catch (EntityExistsException e) {

            System.out.println("Object already exists: " + e.getMessage());
        	entityManager.close();
    		JPAUtility.close();
    		return 0;


        } catch (PersistenceException e) {

        	System.out.println("Failed to update " + ficha + "\n" + e.getMessage());
        	entityManager.close();
    		JPAUtility.close();
    		return 0;


        } catch (Exception e) {

        	System.out.println("Failed to persist " + ficha.toString() + "\n" + e.getMessage());

        } finally {


        }
		
		//System.out.println("*** VIB *** tamanho do list " + listaAnimais.size());
		


		//Commit do relacionamento entre ficha e lista de animais
		//insereListaAnimais(listaAnimais);
		
		
		//System.out.println("**** VIB **** Depois do persist ficha");
		
		//System.out.println("**** VIB **** valor de Ficha: " + ficha.getId());
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		System.out.println("Entity saved.");
		
		return ficha.getId();
	}

	
	public Ficha consultaFicha(int seq) {
		
		Ficha ficha = new Ficha();
		ficha.setId(seq);
		
		//System.out.println("**** VIB **** " + ficha.getId());

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		//System.out.println("**** VIB **** Propiredades do EM" + entityManager.getProperties().toString());
		
		Ficha fichaRetorno = entityManager.find(Ficha.class, ficha.getId());

		//System.out.println("**** VIB **** animal id = " + fichaRetorno.getId());
		//System.out.println("**** VIB **** animal obs = " + fichaRetorno.getObs());
		//System.out.println("**** VIB **** animal status = " + fichaRetorno.getStatus());
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return fichaRetorno;
	}
	
	
	
	public List <AnimaisFicha> consultaAnimaisFicha(int seq) {
			
		startTransaction();
			
		entityManager = JPAUtility.getEntityManager();
				
		TypedQuery<AnimaisFicha> query =
				entityManager.createQuery(
						  "SELECT c "
						+ "FROM AnimaisFicha c "
						+ "WHERE c.idFicha_AnimaisFicha = :idFicha"
						, AnimaisFicha.class)
				.setParameter("idFicha", seq);
		
		List<AnimaisFicha> listaAnimais = query.getResultList();
								
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return listaAnimais;
	}
		
		
	private boolean insereListaAnimais(List<AnimaisFicha> listaAnimais)
	{
		//Commit do relacionamento entre ficha e lista de animais
		listaAnimais.forEach(item -> {
			//System.out.println("*** VIB *** item: idFicha: " + item.getIdFicha() + " - idAnimal: " + item.getIdAnimal());
			entityManager.persist(item);
			
		} );
	
		return true;
	}
	
	public boolean addAnimalFicha(int idFicha, int idAnimal)
	{
		//Commit do relacionamento entre ficha e lista de animais
		startTransaction();
		
		AnimaisFicha animalFicha = new AnimaisFicha(idFicha , idAnimal);
		
		entityManager = JPAUtility.getEntityManager();
	
		entityManager.persist(animalFicha);
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
				
		return true;
	}
	
	private boolean deleteListaAnimais(int idFicha)
	{
		//Limpar lista de animais
		entityManager.createQuery(
			  "DELETE "
			+ "FROM AnimaisFicha c "
			+ "WHERE c.idFicha_AnimaisFicha = :idFicha")
		.setParameter("idFicha", idFicha);

		return true;
	}
		
		
	public boolean updateFicha(int idFicha, Date data, String status, String txtObs, List<AnimaisFicha> listaAnimais) {
		
		Ficha ficha = new Ficha();
		ficha.setId(idFicha);
		
		//System.out.println("**** VIB **** " + id.getId() + " / " + animal.getNome());
	
		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		//System.out.println("**** VIB **** Propiredades do EM" + entityManager.getProperties().toString());
		
		Ficha fichaRetorno = entityManager.find(Ficha.class, ficha.getId());
	
		fichaRetorno.setData(data);
		fichaRetorno.setObs(txtObs);
		fichaRetorno.setStatus(status);
		
		//System.out.println("**** VIB **** animal id = " + animalRetorno.getNome());
		//System.out.println("**** VIB **** animal nome = " + animalRetorno.getId());
		//System.out.println("**** VIB **** animal seq = " + animalRetorno.getSeq());
		
		//Animal animalUpdate = entityManager.merge(animalRetorno);
		entityManager.merge(fichaRetorno);
		
		
		deleteListaAnimais(idFicha);
				
		insereListaAnimais(listaAnimais);
				
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return true;
	}
	
	public boolean deleteFicha(int idFicha) {
		
		Ficha ficha = new Ficha();
		ficha.setId(idFicha);
		
		System.out.println("**** VIB **** Ficha " + ficha.getId());

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		
		//System.out.println("**** VIB **** Propiredades do EM" + entityManager.getProperties().toString());
		
		Ficha fichaRetorno = entityManager.find(Ficha.class, ficha.getId());
		if (fichaRetorno != null)
			//Remove Ficha
			entityManager.remove(fichaRetorno);
		
		//Remove Animais associados
		entityManager = JPAUtility.getEntityManager();
		//deleteListaAnimais(ficha.getId());

		entityManager.createQuery(
				  "DELETE "
				+ "FROM AnimaisFicha c "
				+ "WHERE c.idFicha_AnimaisFicha = :idFicha")
			.setParameter("idFicha", idFicha).executeUpdate();;
		
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return true;
	}

	
	public List<Ficha> listaFichas() {
				
		System.out.println("**** VIB **** Lista Fichas ");

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		TypedQuery<Ficha> query =
				entityManager.createQuery("SELECT c FROM Ficha c", Ficha.class);
		
		List<Ficha> results = query.getResultList();				
				
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		
		return results;
	}	
	
	
	public List<Ficha> filtraListaFichasId(int id) {
		
		System.out.println("**** VIB **** Filtra Fichas Id ");

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		TypedQuery<Ficha> query =
				entityManager.createQuery(
						  "SELECT c "
						+ "FROM Ficha c "
						+ "WHERE c.id_Ficha = :id_Ficha"
						, Ficha.class)
				.setParameter("id_Ficha", id);
				
		
		List<Ficha> results = query.getResultList();				
				
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		
		return results;
	}	
	
	public List<Ficha> filtraListaFichasData(Date dataInicial, Date dataFinal) {

		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		TypedQuery<Ficha> query =
				entityManager.createQuery(
						  "SELECT c "
						+ "FROM Ficha c "
						+ "WHERE c.dtCat_Ficha BETWEEN :dataInicial AND :dataFinal"
						, Ficha.class)
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", dataFinal);
				
		
		List<Ficha> results = query.getResultList();				
				
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		
		return results;
	}
	
	public boolean updateAnimal(int idAnimal, String codAnimal, String descAnimal) {
		
		Animal animal = new Animal();
		animal.setSeq(idAnimal);
		
	
		startTransaction();
		
		entityManager = JPAUtility.getEntityManager();
		
		
		Animal animalRetorno = entityManager.find(Animal.class, animal.getId());
	
		animalRetorno.setId(codAnimal);
		animalRetorno.setNome(descAnimal);
		
		entityManager.merge(animalRetorno);				
		
		commitTransaction();
		entityManager.close();
		JPAUtility.close();
		
		//System.out.println("Entity mantemAnimal saved.");
		
		return true;
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
