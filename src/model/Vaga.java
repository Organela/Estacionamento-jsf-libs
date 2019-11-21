package model;
/*
CREATE TABLE `Vaga` (
  `id` INT NOT NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`id`))
  */
import java.io.Serializable;


public class Vaga implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private boolean status;

	public Vaga() {
	
	}
	
	public Vaga(int id, boolean status) {
		super();
		this.status = status;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
/*_________________________________________*/
	
	
	@Override
	public String toString() {
		return "Vaga [id=" + id + ", status=" + status + "]";
	}
	

	public void restaurarVaga(Vaga _vaga) {
		this.id = _vaga.id;
		this.status = _vaga.status;
		
	}
	
	@Override
	public Vaga clone() {
		return new Vaga(this.id, this.status);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (status ? 1231 : 1237);
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
		Vaga other = (Vaga) obj;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
	
	
}