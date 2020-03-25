package br.ifsc.praticas.hibernate;

import java.util.List;

import org.hibernate.Session;

import br.ifsc.praticas.dao.PedidoDAO;
import br.ifsc.praticas.model.Pedido;

public class HibernatePedidoDAO implements PedidoDAO {

	@Override
	public void salvar(Pedido pedido) {
		Session session = HibernateUtil.getInstance().getSession();
		session.beginTransaction();
		session.save(pedido);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void remover(Pedido pedido) {
		Session session = HibernateUtil.getInstance().getSession();
		session.beginTransaction();
		session.remove(pedido);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void atualizar(Pedido pedido) {
		Session session = HibernateUtil.getInstance().getSession();
		session.beginTransaction();
		Pedido pedidoToEdit = session.get(Pedido.class, pedido.getId());

		pedidoToEdit.setNumeroPedido(pedido.getNumeroPedido());
		pedidoToEdit.setPessoa(pedido.getPessoa());
		pedidoToEdit.setValor(pedido.getValor());
		session.clear(); 
		session.update(pedidoToEdit);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Pedido> listar() {
		Session session = HibernateUtil.getInstance().getSession();
		List<Pedido> pedidos = session.createQuery("from Pedido", Pedido.class).list();
		session.close();
		return pedidos;
	}

	@Override
	public Pedido buscarPorId(int id) {
		Session session = HibernateUtil.getInstance().getSession();
		Pedido pedido = session.get(Pedido.class, id);
		session.close();
		return pedido;
	}

}
