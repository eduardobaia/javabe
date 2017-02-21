package br.com.learning.javabe.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoPostgresJDBC implements ConexaoJDBC {

	private Connection connection = null;

	public ConexaoPostgresJDBC() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		Properties propertie = new Properties();
		propertie.put("user", "postgres");
		propertie.put("password", "postgres");

		this.connection = DriverManager
				.getConnection("jdbc:postgresql://127.0.0.1:5432/helpe-me/applicationName=helpMe", propertie);
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
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {

				Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		this.connection.commit();
		this.connection.close();
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		if (this.connection != null) {
			try {
				this.connection.rollback();
				} catch (SQLException e) {

				Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, e);
			}finally {
				this.close();
			}
		}
	}

}
