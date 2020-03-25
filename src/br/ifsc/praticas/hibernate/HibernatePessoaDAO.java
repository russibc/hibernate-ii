package br.ifsc.praticas.hibernate;

import java.util.List;

import org.hibernate.Session;

import br.ifsc.praticas.dao.PessoaDAO;
import br.ifsc.praticas.model.Pessoa;

public class HibernatePessoaDAO implements PessoaDAO {

	@Override
	public void salvar(Pessoa pessoa) {
		Session session = HibernateUtil.getInstance().getSession();
		session.beginTransaction();
		session.save(pessoa);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void remover(Pessoa pessoa) {
		Session session = HibernateUtil.getInstance().getSession();
		session.beginTransaction();
		session.remove(pessoa);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void atualizar(Pessoa pessoa) {
		Session session = HibernateUtil.getInstance().getSession();
		session.beginTransaction();
		Pessoa pessoaToEdit = session.get(Pessoa.class, pessoa.getId());

		pessoaToEdit.setIdade(pessoa.getIdade());
		pessoaToEdit.setPrimeiroNome(pessoa.getPrimeiroNome());
		pessoaToEdit.setUltimoNome(pessoa.getUltimoNome());
		pessoaToEdit.setProfissao(pessoa.getProfissao());

		session.update(pessoaToEdit);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Pessoa> listar() {
		Session session = HibernateUtil.getInstance().getSession();
		List<Pessoa> alunos = session.createQuery("from Pessoa", Pessoa.class).list();
		session.close();
		return alunos;
	}

	@Override
	public Pessoa buscarPorId(int id) {
		Session session = HibernateUtil.getInstance().getSession();
		Pessoa aluno = (Pessoa) session.get(Pessoa.class, id);
		session.close();
		return aluno;
	}

}
