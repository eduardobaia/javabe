package br.com.learning.javabe.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.learning.javabe.entity.Chamado;
import br.com.learning.javabe.repository.ChamadoDAO;

@Path("chamados")
public class ChamadoController {

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Chamado> listChamados(){
		
		try {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			
			return chamadoDAO.listar();
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			Logger.getLogger(ChamadoController.class.getName());			
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Chamado getChamado(@PathParam("id") long id){

		try {
			ChamadoDAO chamadoDAO= new ChamadoDAO();
			return chamadoDAO.selecionar(id);
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			Logger.getLogger(ChamadoController.class.getName());			
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Chamado chamado){
		try {
		chamado.setStatus("NOVO");
	
		ChamadoDAO chamadoDAO=new ChamadoDAO();
		chamadoDAO.inserir(chamado);
		return Response.status(Response.Status.OK).build();
		}catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			Logger.getLogger(ChamadoController.class.getName());			
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Chamado chamado){

		try {
			chamado.setStatus(""+br.com.learning.javabe.entity.Status.PENDENTE);
			 
			ChamadoDAO chamadoDAO= new ChamadoDAO();
			chamadoDAO.alterar(chamado);
			return Response.status(Response.Status.OK).build();
			
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			Logger.getLogger(ChamadoController.class.getName());			
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id){

		try {
			ChamadoDAO chamadoDAO= new ChamadoDAO();
			chamadoDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			Logger.getLogger(ChamadoController.class.getName());			
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Response concluir(@PathParam("id") long id){

		try {
			
			ChamadoDAO chamadoDAO= new ChamadoDAO();
			
			Chamado c = chamadoDAO.selecionar(id);
			c.setStatus(""+br.com.learning.javabe.entity.Status.FECHADO);
			chamadoDAO.alterar(c);
			
			return Response.status(Response.Status.OK).build();
			
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE,null, ex);;			
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
