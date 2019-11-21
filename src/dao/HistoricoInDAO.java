package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Historico;

public interface HistoricoInDAO {
	
void Inserir(Historico _objeto) throws SQLException;
	
	List<Historico> listarTodos() throws SQLException;
	
	Boolean Excluir(Date _id) throws SQLException;
	
	Boolean Atualizar(Historico _objeto) throws SQLException;
	
	Historico buscarPorData(String _data)throws SQLException;
}