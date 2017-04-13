package br.com.learning.javabe.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import br.com.learning.javabe.entity.Chamado;
import br.com.learning.javabe.infra.ConexaoJDBC;
import br.com.learning.javabe.infra.ConexaoMySqlJDBC;

public class ChamadoDAO {

	private final ConexaoJDBC conexao;

	
	// poliformis using interface.
	public ChamadoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMySqlJDBC();
	}

	
	public void TestaAi(){
		
		String sqlQuery = "SELECT * FROM CRMD.CTRL_PROCM WHERE ID_SIT_PROCM = 3";

		 int id;
        int idsit;
        String titulo;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		//	stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				  id = rs.getInt("ID_CTRL_PROCM");
	                idsit= rs.getInt("ID_SIT_PROCM");
	                
	                titulo = rs.getString("END_RETOR");
	                System.out.println("ROW = " + id + ": "+ idsit +" >>>" + titulo );
			}

		} catch (SQLException e) {
			//this.conexao.rollback();
			System.out.println(e.getMessage());
			//throw e;
		}
		
		
	}
	
	
	public Long inserir(Chamado chamado) throws SQLException, ClassNotFoundException {
		Long id = null;

		String sqlQuery = "INSERT INTO chamado (assunto, mensagem, status) VALUES (?,?,?) insert_id";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, chamado.getAssunto());
			stmt.setString(2, chamado.getMensagem());
			stmt.setString(3, chamado.getStatus());
			// SELECT LAST_INSERT_ID() ;
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getLong("id");
			}
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return id;

	}

	public int alterar(Chamado chamado) throws SQLException, ClassNotFoundException {
		String slqQuery = "UPDATE chamado SET assunto=?, mensagem= ?, status= ? WHERE id =  ? ";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(slqQuery);
			stmt.setString(1, chamado.getAssunto());
			stmt.setString(2, chamado.getMensagem());
			stmt.setString(3, chamado.getStatus());
			stmt.setLong(4, chamado.getId());
			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;
	}

	public int excluir(long id) throws SQLException, ClassNotFoundException {
		int linhasAfetadas = 0;
		String sqlQuery = "DELETE FROM chamado WHERE id= ? ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;

	}

	public Chamado selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM chamado WHERE id= ? ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return parser(rs);
			}

		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return null;
	}

	private Chamado parser(ResultSet resultSet) throws SQLException {

		Chamado c = new Chamado();

		c.setId(resultSet.getLong("id"));
		c.setAssunto(resultSet.getString("assunto"));
		c.setMensagem(resultSet.getString("mensagem"));
		c.setStatus(""+Status.valueOf(resultSet.getString("status")));

		
		return c;
	}
	
	
	public List<Chamado> listar() throws SQLException, ClassNotFoundException{
		String sqlQuery ="SELECT * FROM chamado ORDER BY id";
		
		try {
			PreparedStatement stmt= this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs= stmt.executeQuery();
			
			List<Chamado> chamados = new ArrayList<>();
			
			while(rs.next()){
				chamados.add(parser(rs));
			}
			
			return chamados;
		} catch (SQLException e) {
			throw e;
		}
		
	}
	

}
