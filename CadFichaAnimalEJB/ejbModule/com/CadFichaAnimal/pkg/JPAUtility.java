package com.CadFichaAnimal.pkg;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtility {
 	private static EntityManagerFactory emFactory;
	static {
		   
	}
	public static EntityManager getEntityManager(){
		
		emFactory = Persistence.createEntityManagerFactory("mysql");
		
		//System.out.println("**** VIB **** Criado o EMFactory" + emFactory.getProperties().toString());
		
		return emFactory.createEntityManager();
	}
	public static void close(){
		emFactory.close();
	}
} 