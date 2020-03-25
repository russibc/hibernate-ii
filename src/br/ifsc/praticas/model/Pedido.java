package br.ifsc.praticas.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pedido")
public class Pedido {

	private int id;
	private int numeroPedido;
	private int valor;
	private Pessoa pessoa;

	public Pedido() {

	}

	public Pedido(int id, int numeroPedido, int valor, Pessoa pessoa) {
		this.id = id;
		this.numeroPedido = numeroPedido;
		this.valor = valor;
		this.pessoa = pessoa;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoaId")
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Pedido)) {
			return false;
		}
		Pedido pedido = (Pedido) obj;
		return id == pedido.id && Objects.equals(numeroPedido, pedido.numeroPedido)
				&& Objects.equals(valor, pedido.valor) && Objects.equals(pessoa, pedido.pessoa);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numeroPedido, valor, pessoa);
	}

}
