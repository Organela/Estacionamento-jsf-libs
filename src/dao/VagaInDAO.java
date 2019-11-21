package dao;

import java.sql.SQLException;
import java.util.List;

import model.Vaga;

public interface VagaInDAO {
	
	void Inserir(Vaga _objeto) throws SQLException;
	
	List<Vaga> listarTodos() throws SQLException;
	
	Boolean Excluir(int _id) throws SQLException;
	
	Boolean Atualizar(Vaga _objeto) throws SQLException;
	
	Vaga buscarPorId(int _id) throws SQLException;
	
	/*public List<Vaga> listarVagaPorHistorico(java.util.Date data) throws SQLException;*/
}