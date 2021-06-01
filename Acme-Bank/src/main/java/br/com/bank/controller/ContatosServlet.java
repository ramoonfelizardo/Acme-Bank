package br.com.bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bank.model.Client;
import br.com.bank.model.Contato;
import br.com.bank.service.ClientServiceImpl;
import br.com.bank.service.ContatoServiceImpl;

/**
 * Servlet implementation class ContatoServlet
 */
@WebServlet("/contatosServlet")
public class ContatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private Contato contato;
	private ContatoServiceImpl service;
	private Client client;
	private ClientServiceImpl serviceClient;

	
	public ContatosServlet() {
		this.service = new ContatoServiceImpl();
		this.contato = new Contato();
		this.serviceClient = new ClientServiceImpl();
		this.client = new Client();
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		switch (acao) {
		
		case "remover":
			String id = request.getParameter("id");
			System.out.println(id);
		case "editar":
			
		case "listar":
			RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/list_client.jsp");
			request.setAttribute("contatos", this.service.list());
			rd.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
		String name  = request.getParameter("Name"); 
		String email = request.getParameter("Email");
		String phone = request.getParameter("Phone");
		String senha = request.getParameter("Senha");
		
		//MONTEI O MEU OBJETO CLIENTE //
		Client client = new Client();
		client.setName(name);
		client.setEmail(email);
		client.setPhone(phone);
		client.setSenha(senha);
		
		if(this.serviceClient.save(client)) {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/list_client.jsp");
		request.setAttribute("Sucesso", "Cadastro Salvo Com Sucesso");
		request.setAttribute("Clients", this.serviceClient.getAll());
		rd.forward(request, response);
			}
		}catch (Exception e){
			System.out.println(e.toString());
			response.sendRedirect("admin/primeiroAcesso.jsp");
		}
		
	}
}