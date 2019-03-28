package com.CadFichaAnimal.pkg;

import java.io.IOException;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/CadAnimalServlet")


public class CadAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	mantemAnimalSB manterAnimalSB;
	
    public CadAnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//System.out.println("Entrando no doGet manterAnimal Servlet");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		//System.out.println(manterAnimalSB.addAnimal("CAO05", "Husky"));
		Animal animalConsulta = manterAnimalSB.consultaAnimal(2);
		System.out.println(animalConsulta.getNome());
		//System.out.println(manterAnimalSB.updateAnimal(6, "GAT05", "Siames"));
		//System.out.println(manterAnimalSB.deleteAnimal(7));

		
		System.out.println("Saindo do doGet Servlet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
