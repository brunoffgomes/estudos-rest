package br.com.alura.loja.resource;

import java.net.URI;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@NotNull(message = "N�o existe carrinho para o id solicitado")
	public String busca(@PathParam("id") long id){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML();
	} 

/*	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@NotNull(message = "N�o existe carrinho para o id solicitado")
	public String busca(@PathParam("id") long id){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toJson();
	} */
	
	/**
	 * Cria um novo registro de carrinho.
	 * Devolve o resultado no status code.
	 * 
	 * @param conteudo
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo){
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri  = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();
		//return "<status>sucesso</status>";
	}
	
	
	@Path("{id}/produtos/{produtosId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	
}
