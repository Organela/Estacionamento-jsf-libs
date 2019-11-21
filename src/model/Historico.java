package model;

import java.io.Serializable;
import java.util.Date;

/*
CREATE TABLE`Historico` (
  	`Carro_id` INT NOT NULL,
  	`Carro_Cliente_id` INT NOT NULL,
  	`Vaga_id` INT NOT NULL,
  	`horas`TIME NULL,
  	`data` DATE NULL,
    `Funcionario_id` INT NOT NULL,
    
  
  	PRIMARY KEY (`Carro_id`,`data`),
    
  	FOREIGN KEY (`Carro_id`)REFERENCES carro(`id`),
  	FOREIGN KEY (`Carro_Cliente_id`)REFERENCES cliente(`id`),
  	FOREIGN KEY (`Vaga_id`) REFERENCES vaga(`id`),
    FOREIGN KEY (`Funcionario_id`) REFERENCES funcionario(`id`)   
);
);
 */

public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Date data;
	
	private float preco;
	
	private Cliente cliente;
	private Carro carro;
	private Vaga vaga;
	private Funcionario funcionario;
	
	
	
	public Historico() {
		
		this.cliente = new Cliente();
		this.carro = new Carro();
		this.vaga = new Vaga();
		this.funcionario = new Funcionario();
	
	}



	public Historico(Date data, float preco, Cliente cliente, Carro carro, Vaga vaga,
			Funcionario funcionario) {
		super();
		this.data = data;
		this.preco = preco;
		this.cliente = cliente;
		this.carro = carro;
		this.vaga = vaga;
		this.funcionario = funcionario;
	}



	public Historico(Date data, float preco) {
		
		this.data = data;
		this.preco = preco;
	
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public float getPreco() {
		return preco;
	}



	public void setPreco(float preco) {
		this.preco = preco;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Carro getCarro() {
		return carro;
	}



	public void setCarro(Carro carro) {
		this.carro = carro;
	}



	public Vaga getVaga() {
		return vaga;
	}



	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}



	public Funcionario getFuncionario() {
		return funcionario;
	}



	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Historico [data=" + data + ", preco=" + preco + ", cliente=" + cliente + ", carro=" + carro + ", vaga="
				+ vaga + ", funcionario=" + funcionario + "]";
	}

	public void restaurarHistorico(Historico _historico) {
		this.data = _historico.data;
		this.preco = _historico.preco;
		
		
	}
	
	@Override
	public Historico clone() {
		return new Historico(this.data, this.preco);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carro == null) ? 0 : carro.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + Float.floatToIntBits(preco);
		result = prime * result + ((vaga == null) ? 0 : vaga.hashCode());
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
		Historico other = (Historico) obj;
		if (carro == null) {
			if (other.carro != null)
				return false;
		} else if (!carro.equals(other.carro))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (Float.floatToIntBits(preco) != Float.floatToIntBits(other.preco))
			return false;
		if (vaga == null) {
			if (other.vaga != null)
				return false;
		} else if (!vaga.equals(other.vaga))
			return false;
		return true;
	}



	
	


	



	
	
}