package br.ifsc.praticas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ifsc.praticas.dao.PessoaDAO;
import br.ifsc.praticas.model.Pedido;
import br.ifsc.praticas.model.Pessoa;

public class JDBCPessoaDAO implements PessoaDAO {

	private static String sql;
	private JDBCUtil banco;

	public JDBCPessoaDAO() {
		banco = JDBCUtil.getInstance();
		banco.conectar();
	}

	@Override
	public void salvar(Pessoa pessoa) {
		if (banco.isConnected()) {

			if(pessoa.getId() == 0) {
				pessoa.setId(geraId(pessoa));
			}
			
			sql = "INSERT INTO pessoa(ID, ultimoNome, primeiroNome, idade, profissao) VALUES(?,?,?,?,?)";
			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pessoa.getId());
				ps.setString(2, pessoa.getUltimoNome());
				ps.setString(3, pessoa.getPrimeiroNome());
				ps.setInt(4, pessoa.getIdade());
				ps.setString(5, pessoa.getProfissao());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	
	private int geraId(Pessoa p) {
		int id = 0;
		sql = "select max(id) AS id from Pessoa";
		ResultSet rs = this.executeQuery(sql, banco.conectar());
		try {
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
		
		return id+1;
	}

	@Override
	public void remover(Pessoa pessoa) {
		if (banco.isConnected()) {
			sql = "DELETE FROM pessoa WHERE id=?";
			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pessoa.getId());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizar(Pessoa pessoa) {
		if (banco.isConnected()) {
			sql = "UPDATE pessoa SET ultimoNome=?,primeiroNome=?,idade=?,profissao=? WHERE id=?";

			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setString(1, pessoa.getUltimoNome());
				ps.setString(2, pessoa.getPrimeiroNome());
				ps.setInt(3, pessoa.getIdade());
				ps.setString(4, pessoa.getProfissao());
				ps.setInt(5, pessoa.getId());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<Pessoa> listar() {
		List<Pessoa> pessoas = null;
		if (banco.isConnected()) {

			sql = "SELECT * FROM pessoa";

			ResultSet rs = this.executeQuery(sql, banco.conectar());
			pessoas = new ArrayList<Pessoa>();
			try {
				while (rs.next()) {
					pessoas.add(this.getPessoaInfo(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return pessoas;
	}

	@Override
	public Pessoa buscarPorId(int id) {
		Pessoa pessoa = null;
		if (banco.isConnected()) {
			sql = "SELECT * FROM pessoa WHERE ID=" + id;
			ResultSet rs = this.executeQuery(sql, banco.conectar());
			try {
				while (rs.next()) {
					pessoa = new Pessoa();
					pessoa.setId(Integer.valueOf(rs.getString("ID")));
					pessoa.setIdade(Integer.valueOf(rs.getString("idade")));
					pessoa.setPrimeiroNome(rs.getString("primeiroNome"));
					pessoa.setUltimoNome(rs.getString("ultimoNome"));
					pessoa.setProfissao(rs.getString("profissao"));
				}
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		return pessoa;
	}

	private Pessoa getPessoaInfo(ResultSet rs) throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(Integer.valueOf(rs.getString("ID")));
		pessoa.setIdade(Integer.valueOf(rs.getString("idade")));
		pessoa.setPrimeiroNome(rs.getString("primeiroNome"));
		pessoa.setUltimoNome(rs.getString("ultimoNome"));
		pessoa.setProfissao(rs.getString("profissao"));
		return pessoa;
	}

	private ResultSet executeQuery(String query, Connection conexao) {
		Statement st = null;
		try {
			st = conexao.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

}
