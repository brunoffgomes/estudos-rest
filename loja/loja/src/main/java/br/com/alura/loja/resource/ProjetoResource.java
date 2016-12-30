package br.com.alura.loja.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {
	
	
	
/*	
 * 	REST retornando XML.
 *  @Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String buscar(@PathParam("id") long id){
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto.toXML();
	} */
	
	
	/*
	 * REST retornando JSON.
	 */
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String buscar(@PathParam("id") long id){
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto.toJSON();
	} 
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String adiciona(String conteudo){
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		return "<status>sucesso</status>";
	}
	
	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Projeto projeto = new ProjetoDAO().busca(id);
		new ProjetoDAO().remove(projeto.getId());
		return Response.ok().build();
	}
	
}
