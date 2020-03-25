package br.ifsc.praticas.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	private String primeiroNome;
	private int id;
	private String ultimoNome;
	private String profissao;
	private int idade;

	public Pessoa() {
	}

	public Pessoa(int id, String primeiroNome, String ultimoNome, int idade, String profissao) {
		this.id = id;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.idade = idade;
		this.profissao = profissao;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
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

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Pessoa)) {
			return false;
		}
		Pessoa pessoa = (Pessoa) obj;
		return id == pessoa.id && Objects.equals(primeiroNome, pessoa.primeiroNome)
				&& Objects.equals(ultimoNome, pessoa.ultimoNome) && Objects.equals(idade, pessoa.idade)
				&& Objects.equals(profissao, pessoa.profissao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, primeiroNome, ultimoNome, idade, profissao);
	}

}