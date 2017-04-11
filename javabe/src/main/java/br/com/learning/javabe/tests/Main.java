package br.com.learning.javabe.tests;

import java.sql.SQLException;

import br.com.learning.javabe.entity.Chamado;
import br.com.learning.javabe.repository.ChamadoDAO;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		ChamadoDAO chamadoDAO = new ChamadoDAO();
		
		
		
		Chamado ch = new Chamado();
		ch.setAssunto("Assunto haha");
		ch.setMensagem("This is a message!!!");
		ch.setStatus("novo");
		
		
			chamadoDAO.inserir(ch);
		
		
		
	}

}
