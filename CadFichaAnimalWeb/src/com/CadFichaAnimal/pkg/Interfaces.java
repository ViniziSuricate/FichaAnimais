package com.CadFichaAnimal.pkg;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/rest")
public interface Interfaces {
	
	
	@GET
    @Path("/consultaListaFichas")
	@Produces("application/json")
	public String[] listaFichas();
	
	@POST
    @Path("/adicionaFicha")
	@Produces("application/json")
	public Response adicionaFicha(Ficha ficha);
	
	@POST
    @Path("/deletaFicha")
	@Produces("application/json")
	public Response deletaFicha(int id);
	
	@GET
    @Path("/consultaFicha")
	@Produces("application/json")
	public String[] consultaFicha(int id);

	@GET
    @Path("/filtraFichaId")
	@Produces("application/json")
	public String[] filtraFichasId(int idFicha);

	@GET
    @Path("/filtraFichaData")
	@Produces("application/json")
	public String[] filtraFichasData(Date dataInicial, Date dataFinal);
	
	@POST
    @Path("/adicionaAnimaisFicha")
	@Produces("application/json")	
	public Response adicionaAnimaisFicha(int idFicha, String[] animais);
	
	@POST
    @Path("/deletaAnimaisFicha")
	@Produces("application/json")	
	public Response deletaAnimaisFicha(int idFicha);

	@GET
    @Path("/listaAnimais")
	@Produces("application/json")	
	public String[] listaAnimais();
	
	@GET
    @Path("/listaAnimaisFicha")
	@Produces("application/json")		
	public String[] listaAnimaisFicha();

}
