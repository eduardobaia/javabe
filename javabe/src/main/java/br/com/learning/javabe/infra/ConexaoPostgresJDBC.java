package br.com.learning.javabe.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoPostgresJDBC implements ConexaoJDBC{

	private Connection connection =null;
	public ConexaoPostgresJDBC() throws ClassNotFoundException, SQLException{
	Class.forName("org.postgresql.Driver");

	Properties propertie = new Properties();
	propertie.put("user", "postgres");
	propertie.put("password", "postgres");
	
	
	this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/helpe-me/application");
	this.connection.setAutoCommit(false);
	
		
	}
	
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		
	}

}
