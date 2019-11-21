package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Carro;
import model.Cliente;




public class CarroDAO implements CarroInDAO {//Vai lidar com a parte de banco de dados referente a Carro
	
	private Connection conexao = null;
	
	public CarroDAO(Connection _conn) {
		this.conexao = _conn;
	}
	@Override
	public void Inserir(Carro _objeto) throws SQLException {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO carro (placa, nome) VALUES (?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		
				ps.setString(1, _objeto.getNome());//Seta o parametro do primeiro `?`
				ps.setString(2, _objeto.getPlaca());//Seta o paramentro do segundo `?`
				
			
		
		
		ps.execute();
	}

	@Override
	public List<Carro> listarTodos() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Carro> carros = new ArrayList<Carro>();
		ResultSet rs = null;
		
		String SQL = "SELECT id, placa, nome FROM carro";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {//Adicionando informações de no Banco em sua Classe
			
			Carro carro = new Carro();
			
			int id = rs.getInt(1);
			String placa = rs.getString(2);
			String nome = rs.getString(3);
			
			carro.setId(rs.getInt(1));
			carro.setPlaca(rs.getString(2));
			carro.setNome(rs.getString(3));
			/*
			ClienteDAO daoCli = new ClienteDAO(this.conexao);
			List<Cliente> cliente = daoCli.listarClientePorCarro(id);
			carro.setCliente(cliente);*/
			
			carros.add(carro);
			
			
		}
		
		return carros;
		
		
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
		// TODO Auto-generated method stub
		String SQL = "DELETE FROM carro WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		boolean rs;
		rs = ps.execute();
		
		return rs;
		
	}

	@Override
	public Boolean Atualizar(Carro _objeto) throws SQLException {
		// TODO Auto-generated method stub
		String SQL = "UPDATE carro placa = ?, nome = ? WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		
		ps.setString(1, _objeto.getPlaca());
		ps.setString(2, _objeto.getNome());
		ps.setInt(3, _objeto.getId());
	
		boolean rs;
		rs = ps.execute();
		
		return rs;//Caso a query seja executada com sucesso retornará um valor boleano
		
	}

	@Override
	public Carro buscarPorId(int _id) throws SQLException {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
	
		
		String SQL = "SELECT * FROM pessoa WHERE id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.executeQuery();
		
		if (rs.next()) {
			Carro carro = new Carro();
			int id = rs.getInt(1);
			
			carro.setId(rs.getInt(1));
			carro.setNome(rs.getString(2));
			carro.setPlaca(rs.getString(3));
			/*
			ClienteDAO daoCli = new ClienteDAO(this.conexao);
			List<Cliente> cliente = daoCli.listarClientePorCarro(id);
			*/
			
			return carro;
			
		}
		
		return null;
		
		
	}
	/*@Override
	public List<Carro> listarCarroPorHistorico(java.util.Date data) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List<Carro> carro = new ArrayList<Carro>();
		String SQL = "SELECT id, placa, nome from carro where Carro_id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		 
		ps.setDate(1, new Date(data.getTime()));
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			Carro car = new Carro();
			
			car.setId(rs.getInt(1));
			car.setPlaca(rs.getString(2));
			car.setNome(rs.getString(3));
		
			
			
			
			carro.add(car);
		}
		
		return carro;
		
	}*/
	


}