package br.ifsc.praticas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	private Connection conexao;
	private static JDBCUtil BANCO;
	private static final String database = "bruna";
	private static final String user = "root";
	private static final String psw = "aluno";

	private JDBCUtil() {
	}

	public static JDBCUtil getInstance() {

		if (BANCO == null) {
			BANCO = new JDBCUtil();
		}

		return BANCO;
	}

	public Connection conectar() {
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?serverTimezone=UTC&&useSSL=false", user,
					psw);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexao;
	}

	public boolean isConnected() {
		return (conexao != null);
	}

}
