package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Carro;


public interface CarroInDAO {

	void Inserir(Carro _objeto) throws SQLException;
	
	List<Carro> listarTodos() throws SQLException;
	
	Boolean Excluir(int _id) throws SQLException;
	
	Boolean Atualizar(Carro _objeto) throws SQLException;
	
	Carro buscarPorId(int _id) throws SQLException;

	//List<Carro> listarCarroPorHistorico(String _idCarro) throws SQLException;

	/*List<Carro> listarCarroPorHistorico(Date data) throws SQLException;*/
	
	
}