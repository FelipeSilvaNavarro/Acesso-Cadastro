package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection faz_conexao() throws SQLException {
		final String url = "jdbc:mysql://localhost/db_senhas";
		final String usuario = "root";
		final String senha = "Felipe@17082022";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}

	}
}
