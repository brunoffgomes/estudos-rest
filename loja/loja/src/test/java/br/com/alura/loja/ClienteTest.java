package br.com.alura.loja;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;






import com.thoughtworks.xstream.XStream;

public class ClienteTest {
	
	
	private HttpServer server;
	/*@Test
	public void testaQueAConexaoComOServidorFunciona(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		//System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("Rua Vergueiro 3185"));

	} */
	
/*	@Test
	public void testaQueBuscarCarrinhoTrazCarrinhoEsperado(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		System.out.println(conteudo);
		//Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
		Carrinho carrinhoFromXML = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinhoFromXML.getRua());
	} */
	
	@Before
	public void startaServidor(){
		this.server = Servidor.inicializaServidor();
	}
	
	@After
	public void mataServidor(){
		server.stop();
		
	}
	
/*	@Test
	public void testaQueBuscarProjetoTrazProjetoEsperado(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/projetos").request().get(String.class);
		System.out.println(conteudo);
		//Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		
		Assert.assertEquals("Minha Loja", projeto.getNome());
	}
	*/
	
/*	@Test
	public void testaQueSuportaNovosCarrinhos(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080"); */
		
		/*
		 * Criamos carrinho e transformamos em XML.
		 */
	/*	Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("Sao Paulo");
		String xml = carrinho.toXML(); */
		
		
		/*
		 * Representacao do conteudo enviado e seu tipo(xml).
		 */
		
	/*	Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
		Assert.assertEquals("<status>sucesso</status>", response); 
		
		
	}*/
	
/*	@Test
	public void testaQueSuportaNovosProjetos(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080"); */
		
		
		
		/*
		 * Criamos o projeto e transformamos em XML.
		 */
	/*	Projeto projeto = new Projeto(2L,"Casas Bahia",2015);
		String xml = projeto.toXML(); */
		
		/*
		 * Representacao do conteudo enviado e seu tipo(xml).
		 */
	//	Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
	/*	Response response = target.path("/projetos").request().post(entity);
		Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
		Assert.assertEquals("<status>sucesso</status>", response);
 	}
 	*/
	
	@Test
	public void testaQueSuportaNovosProjetos(){
		
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		Client client = ClientBuilder.newClient(config);
		
		
		WebTarget target = client.target("http://localhost:8080");
		
		/*
		 * Criamos o projeto e transformamos em XML.
		 */
		Projeto projeto = new Projeto(2L,"Casas Bahia",2015);
		String xml = projeto.toXML();
		
		/*
		 * Representacao do conteudo enviado e seu tipo(xml).
		 */
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/projetos").request().post(entity);
		Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
		Assert.assertEquals("<status>sucesso</status>", response);
		
		
	}
}
