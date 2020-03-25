package br.ifsc.praticas.dao;

import java.util.List;

import br.ifsc.praticas.model.Pessoa;

public interface PessoaDAO {
	public void salvar(Pessoa pessoa);

	public void remover(Pessoa pessoa);

	public void atualizar(Pessoa pessoa);

	public List<Pessoa> listar();

	public Pessoa buscarPorId(int id);
}
