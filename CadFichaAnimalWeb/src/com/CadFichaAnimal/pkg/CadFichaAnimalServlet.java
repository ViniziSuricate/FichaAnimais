package com.CadFichaAnimal.pkg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/CadFichaAnimalServlet")


public class CadFichaAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	mantemFichaSB mantemFichaSB;
	
    public CadFichaAnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("*** VIB *** Entrando no CadFichaAnimalServlet doGet Servlet");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
/*		
		//consulta
		System.out.println("*** VIB *** antes do consulta Ficha");		
		
		Ficha fichaConsulta = mantemFichaSB.consultaFicha(9);
	    System.out.println(fichaConsulta.getObs());
	    
	    
	    List <AnimaisFicha> listaAnimais = new ArrayList <AnimaisFicha>();
	    listaAnimais = mantemFichaSB.consultaAnimaisFicha(9);
		listaAnimais.forEach(item -> {
			System.out.println("*** VIB *** item: id:" + item.getId() + " - idFicha: " + item.getIdFicha() + " - idAnimal: " + item.getIdAnimal());
			
		} );
*/		
		
/*
        //Inclusão
		List <AnimaisFicha> listaAnimais = new ArrayList <AnimaisFicha>();
		AnimaisFicha animalFicha1 = new AnimaisFicha(); 
		AnimaisFicha animalFicha2 = new AnimaisFicha(); 
		animalFicha1.setIdAnimal(1);
		animalFicha2.setIdAnimal(2);
		listaAnimais.add(animalFicha1);
		listaAnimais.add(animalFicha2);
		
		System.out.println("*** VIB *** antes do add Ficha");

		listaAnimais.forEach(item -> {
			System.out.println("*** VIB *** item: idFicha: " + item.getIdFicha() + " - idAnimal: " + item.getIdAnimal());
			
		} );
		System.out.println(mantemFichaSB.addFicha("Primeira ficha", listaAnimais));
*/		
		
/*
        //Update
		List <AnimaisFicha> listaAnimais = new ArrayList <AnimaisFicha>();
		AnimaisFicha animalFicha1 = new AnimaisFicha(); 
		AnimaisFicha animalFicha2 = new AnimaisFicha(); 
		animalFicha1.setIdAnimal(9);
		animalFicha1.setIdFicha(5);
		animalFicha2.setIdAnimal(12);
		animalFicha2.setIdFicha(5);
		listaAnimais.add(animalFicha1);
		listaAnimais.add(animalFicha2);
		Date data = new Date();
		//System.out.println("*** VIB *** antes do add Ficha");

		//listaAnimais.forEach(item -> {
		//	System.out.println("*** VIB *** item: idFicha: " + item.getIdFicha() + " - idAnimal: " + item.getIdAnimal());
		//} );
		
		System.out.println(mantemFichaSB.updateFicha(5, data, "A", "Update na ficha", listaAnimais));
		
*/		
		
/*		
		//Delete
		System.out.println(mantemFichaSB.deleteFicha(3));
*/		

		
		//Consulta lista de Fichas
    	List <Ficha> listaFichas = new ArrayList <Ficha>();
    	listaFichas = mantemFichaSB.listaFichas();
    	listaFichas.forEach(item -> {
			System.out.println("*** VIB *** item: id:" + item.getId() + " - Obs: " + item.getObs() + " - idStatus: " + item.getStatus());			
		} );
    	
    	response.getWriter().append("Retorno serviço: ").append(listaFichas.toString());
    	
		//System.out.println("Saindo do doGet Servlet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
