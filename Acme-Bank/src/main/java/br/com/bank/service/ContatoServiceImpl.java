/**
 * 
 */
package br.com.bank.service;

import java.util.List;

import br.com.bank.dao.ContatoDaoImpl;
import br.com.bank.model.Client;
import br.com.bank.model.Contato;

/**
 * @author cbgomes
 *
 */
public class ContatoServiceImpl implements ContatoService {
	
	private ContatoDaoImpl dao;
	
	public ContatoServiceImpl() {
		this.dao = new ContatoDaoImpl();
	}

	public void salvar(Contato contato) {
		this.dao.salvar(contato);
	}

	public List<Contato> list() {
		return this.dao.list();
	}

	public Contato getContatoById(Long id) {
		return dao.getContatoById(id);
	}

	public boolean update(Contato client) {
		return false;
	}

}
