package br.com.learning.javabe.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMySqlJDBC implements ConexaoJDBC{

	private Connection connection= null;
	
	public ConexaoMySqlJDBC() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Properties propertie = new Properties();
		propertie.put("user", "root");
		propertie.put("password", "3410");

		this.connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/javabe", propertie);
		this.connection.setAutoCommit(false);

	}
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return this.connection;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if(this.connection != null){
			try {
				this.connection.close();
			} catch (SQLException e) {
				Logger.getLogger(ConexaoMySqlJDBC.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	@Override
	public void commit() throws SQLException {
		this.connection.commit();
		this.connection.close();
	}

	@Override
	public void rollback() {
		if(this.connection != null){
			try {
				this.connection.rollback();
			} catch (SQLException e) {
				Logger.getLogger(ConexaoMySqlJDBC.class.getName()).log(Level.SEVERE, null, e);
				
 			}finally{
 				this.close();
 			}
		}
		
	}

}
