package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {
	
	private HttpServer server;
	
	@Before
	public void startaServidor(){
		server = Servidor.inicializaServidor();
	}
	
	@After
	public void mataServidor(){
		server.stop();
	}
	
	@Test
	public void testaQueAConexaoComOServidorFunciona(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
		
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8081");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		System.out.println(conteudo);
	}
	
	@Test
	public void testaQueSuportaNovoCarrinho(){
		Client client = ClientBuilder.newClient();
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314,"Microfone",37,1));
		carrinho.setRua("Rua Vergueiro 3185");
		carrinho.setCidade("Sao Paulo");
		String xml = carrinho.toXML();
		Entity <String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		WebTarget target = client.target("http://localhost:8081");
		Response response = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
	}
}
