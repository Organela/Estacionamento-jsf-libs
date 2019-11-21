package model;

import java.io.Serializable;

/*
 CREATE TABLE `Funcionario` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `tel` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
 */
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id ;
	private String nome;
	private String tel;
	
	
	public Funcionario() {
		super();
	}

	public Funcionario(int id, String nome, String tel) {
		super();
		this.id = id;
		this.nome = nome;
		this.tel = tel;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	

	public void restaurarFuncionario(Funcionario _funcionario) {
		this.id = _funcionario.id;
		this.nome = _funcionario.nome;
		this.tel = _funcionario.tel;
		
	}
	
	@Override
	public Funcionario clone() {
		return new Funcionario(this.id, this.nome, this.tel);
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", tel=" + tel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

	
	
	
}