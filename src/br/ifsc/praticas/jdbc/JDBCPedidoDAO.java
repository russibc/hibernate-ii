package br.ifsc.praticas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ifsc.praticas.dao.PedidoDAO;
import br.ifsc.praticas.model.Pedido;
import br.ifsc.praticas.model.Pessoa;

public class JDBCPedidoDAO implements PedidoDAO {

	private static String sql;
	private JDBCUtil banco;

	public JDBCPedidoDAO() {
		banco = JDBCUtil.getInstance();
		banco.conectar();
	}
	

	@Override
	public void salvar(Pedido pedido) {
		if (banco.isConnected()) {

			if(pedido.getId() == 0) {
				pedido.setId(geraId(pedido));
			}

			sql = "INSERT INTO pedido(id, numeroPedido, pessoaId, valor) VALUES(?,?,?,?)";
			
			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pedido.getId());
				ps.setInt(2, pedido.getNumeroPedido());
				ps.setInt(3, pedido.getPessoa().getId());
				ps.setInt(4, pedido.getValor());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void remover(Pedido pedido) {
		if (banco.isConnected()) {
			sql = "DELETE FROM pedido WHERE id=?";
			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pedido.getId());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizar(Pedido pedido) {
		if (banco.isConnected()) {
			sql = "UPDATE pedido SET numeroPedido=?,valor=? WHERE id=?";

			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pedido.getNumeroPedido());
				ps.setInt(2, pedido.getValor());
				ps.setInt(3, pedido.getId());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<Pedido> listar() {
		List<Pedido> pedidos = null;
		if (banco.isConnected()) {

			sql = "SELECT * FROM pedido AS pe JOIN pessoa AS ps ON pe.pessoaId = ps.id";

			ResultSet rs = this.executeQuery(sql, banco.conectar());
			pedidos = new ArrayList<Pedido>();
			try {
				while (rs.next()) {
					pedidos.add(this.getPedidoInfo(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return pedidos;
	}
	
	private int geraId(Pedido p) {
		int id = 0;
		sql = "select max(id) AS id from Pedido";
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
	public Pedido buscarPorId(int id) {
		Pedido pedido = null;
		if (banco.isConnected()) {
			sql = "SELECT" + "	pe.id, pe.numeroPedido, pe.pessoaId, pe.valor,"
					+ " ps.ID, ps.ultimoNome, ps.primeiroNome, ps.idade, ps.profissao"
					+ " FROM pedido AS pe JOIN pessoa AS ps ON pe.PessoaId = ps.id AND pe.id =" + id;
			ResultSet rs = this.executeQuery(sql, banco.conectar());
			try {
				while (rs.next()) {
					pedido = getPedidoInfo(rs);
				}
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		return pedido;
	}

	private Pedido getPedidoInfo(ResultSet rs) throws SQLException {
		Pedido pedido = new Pedido();

		pedido.setId(rs.getInt("pe.id"));
		pedido.setNumeroPedido(rs.getInt("pe.numeroPedido"));

		Pessoa pessoa = new Pessoa();
		pessoa.setId(Integer.valueOf(rs.getString("ps.id")));
		pessoa.setIdade(Integer.valueOf(rs.getString("ps.idade")));
		pessoa.setPrimeiroNome(rs.getString("ps.primeiroNome"));
		pessoa.setUltimoNome(rs.getString("ps.ultimoNome"));
		pessoa.setProfissao(rs.getString("ps.profissao"));

		pedido.setPessoa(pessoa);
		pedido.setValor(rs.getInt("pe.valor"));
		return pedido;
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
