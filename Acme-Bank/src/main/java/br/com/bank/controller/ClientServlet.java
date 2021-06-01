package br.com.bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bank.model.Client;
import br.com.bank.service.ClientServiceImpl;

@WebServlet("/clientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientServiceImpl service;
	

	public ClientServlet() {
		this.service = new ClientServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {

		case "remover":
			String id = request.getParameter("id");
			try {
				service.deleteById(Long.parseLong(id));
				RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/list_client.jsp");
				request.setAttribute("Sucesso", "Cadastro Excluido Com Sucesso");
				request.setAttribute("Clients", this.service.getAll());
				rd.forward(request, response);
			} catch (Exception e) {
				e.toString();
			}
			response.setStatus(HttpServletResponse.SC_OK);
			break;

		case "editar":

			String idUpdate = request.getParameter("id");
			try {
				Client cli = service.getClientById(Long.parseLong(idUpdate));
				RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/update_client.jsp");
				request.setAttribute("sucesso", "Cadastro excluido com sucesso");
				request.setAttribute("client", cli);
				rd.forward(request, response);
			} catch (Exception e) {
				e.toString();
			}
			response.setStatus(HttpServletResponse.SC_OK);
			break;

		case "listar":
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/list_client.jsp");
				request.setAttribute("clients", this.service.getAll());
				rd.forward(request, response);
			} catch (Exception e) {
				e.toString();
			}
			break;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// PEGA OS DADOS DA REQUESIÇÂO //
        Long id = null;
        if(request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String phone = request.getParameter("Phone");
        String senha = request.getParameter("Senha");

        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setSenha(senha);

        if (client.getId() != null) {

            try {

                service.update(client);

                RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/list_client.jsp");
                request.setAttribute("Sucesso", "Cadastro Atualizado Com Sucesso.");
                request.setAttribute("Clients", this.service.getAll());
                rd.forward(request, response);

            } catch (Exception e) {
                System.out.println(e.toString());
                response.sendRedirect("admin/primeiroAcesso.jsp");
            }

        } else if (id == null) {

            try {

                // SALVAR O MEU CLIENTE //
                if (this.service.save(client)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                response.sendRedirect("admin/primeiroAcesso.jsp");
            }
        }
		

	}
}
