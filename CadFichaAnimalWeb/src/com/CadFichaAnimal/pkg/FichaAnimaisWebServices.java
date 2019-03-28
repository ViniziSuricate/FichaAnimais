package com.CadFichaAnimal.pkg;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


  
@Path("/Ficha")
public class FichaAnimaisWebServices {

	@EJB
	mantemFichaSB mantemFichaSB;
	mantemAnimalSB mantemAnimalSB;

	
	@GET
    @Path("/consultaListaFichas")
	@Produces("application/json")
	@Consumes("application/json")
	public List <Ficha> listaFichas() {
		
    	List <Ficha> listaFichas = new ArrayList <Ficha>();
    	listaFichas = mantemFichaSB.listaFichas();		
		return listaFichas;
		
	}

	
	@GET
    @Path("/filtraFichaId")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Ficha> filtraFichasId(@QueryParam("idFicha") Integer idFicha) {


    	List <Ficha> listaFichas = new ArrayList <Ficha>();
    	listaFichas = mantemFichaSB.filtraListaFichasId(idFicha);

		return listaFichas;
		
	}

	@SuppressWarnings("deprecation")
	@GET
    @Path("/filtraFichaData")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Ficha> filtraFichasData(@QueryParam("dataInicial") String dataInicialT, @QueryParam("dataFinal") String dataFinalT) {

		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String dateString = format.format( new Date()   );
		Date dataInicial = null;
		try {
			dataInicial = format.parse ( dataInicialT );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dataFinal = null;
		try {
			dataFinal = format.parse ( dataFinalT );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	    System.out.println("*** VIB *** datas: " + dataInicial + " e " + dataFinal);
    	List <Ficha> listaFichas = new ArrayList <Ficha>();
    	listaFichas = mantemFichaSB.filtraListaFichasData(dataInicial , dataFinal);		
		return listaFichas;
		
	}
	
	
	@GET
    @Path("/adicionaFicha")
	@Produces("application/json")
	@Consumes("application/json")
	public Integer adicionaFicha(@QueryParam("obs") String obs, @QueryParam("listaAnimais") List <AnimaisFicha> listaAnimais) {

		System.out.println("*** VIB *** antes do add Ficha");

		
		int idFicha = mantemFichaSB.addFicha(obs);
		
		
		return idFicha;
	}

	@GET
    @Path("/adicionaAnimalFicha")
	@Produces("application/json")
	@Consumes("application/json")
	public Response adicionaAnimal(@QueryParam("idFicha") Integer idFicha, @QueryParam("idAnimal") Integer idAnimal) {

		
		mantemFichaSB.addAnimalFicha(idAnimal, idFicha);
		
		
		return null;
	}
	
	@GET
    @Path("/atualizaAnimal")
	@Produces("application/json")
	@Consumes("application/json")
	public Response atualizaAnimal(@QueryParam("idAnimal") Integer idAnimal, @QueryParam("codAnimal") String codAnimal, @QueryParam("nome") String nome) {

		
		mantemFichaSB.updateAnimal(idAnimal, codAnimal, nome);
		
		
		return null;
	}
	
	@GET
    @Path("/deletaFicha")
	@Produces("application/json")
	@Consumes("application/json")
	public Response deletaFicha(@QueryParam("idFicha") Integer id) {
		
		mantemFichaSB.deleteFicha(id);
		
		return null;
	}

	@GET
    @Path("/consultaFicha")
	@Produces("application/json")
	@Consumes("application/json")
	public Ficha consultaFicha(int id) {
		
		Ficha ficha = new Ficha();
		ficha = mantemFichaSB.consultaFicha(id);
		return ficha;
	}




	@GET
    @Path("/listaAnimais")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Animal> listaAnimais() {
		
		List <Animal> listaAnimais = new ArrayList <Animal>();
		listaAnimais = mantemAnimalSB.listaAnimais();
		return listaAnimais;
		
	}

	@GET
    @Path("/listaAnimaisFicha")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Animal> listaAnimaisFicha() {

		return null;
	}
  

}



