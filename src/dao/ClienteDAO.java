package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDAO implements ClienteInDAO{

	private Connection conexao;
	
	public ClienteDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	
	@Override
	public void Inserir(Cliente _objeto) throws SQLException {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO cliente (nome, tel) VALUES (?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
				
				ps.setString(1, _objeto.getNome());//Seta o parametro do primeiro `?`
				ps.setString(2, _objeto.getTel());
				
			
		
		
		ps.execute();//Executa query
		
	}

	@Override
	public List<Cliente> listarTodos() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Cliente> cliente = new ArrayList<Cliente>();
		ResultSet rs = null;
		
		String SQL = "select id, nome, tel from cliente";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery(); // Retorna um ResultSet com os resultados diferentemente do execute() que retorna apenas booleano
		
		while(rs.next()) { // Percorre a tabela enquanto tiverem registros
			// Caso tenham 5 pessoas cadastradas, esse while rodará 5 vezes (No caso nessa query)
			Cliente c = new Cliente();
			
			// Configurando os atributos da pessoa a ser adicionada a lista
			/*int id = rs.getInt(1);
			String nome = rs.getString(2);
			String telefone = rs.getString(3);
			String email = rs.getString(4);*/
			c.setId(rs.getInt(1)); 
			c.setNome(rs.getString(2)); // rs.getString(2)-> vai retornar o nome da pessoa do banco de dados
			c.setTel(rs.getString(3));
			
			// Get baseado no tipo da coluna (getInt, getString) e o inteiro é o número da coluna na query,
			
			cliente.add(c); // A pessoa é adicionada a lista de pessoas
		}
		
		return cliente;
		
	
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
		// TODO Auto-generated method stub
		boolean rs = false;
		
		String SQL = "delete from cliente where id=?";

				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.execute(); // Caso a query seja executada com sucesso retornará um valor booleano
		return rs; 
		
	}

	@Override
	public Boolean Atualizar(Cliente _objeto) throws SQLException {//Verificar
		// TODO Auto-generated method stub
		
		boolean rs = false;
		
		String SQL = "update cliente set nome=?, tel=? where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		

		
		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getTel());
		ps.setInt(3, _objeto.getId());
		
		rs = ps.execute(); // Caso a query seja executada com sucesso retornará um valor booleano
		
		return rs;
		
		
	}

	@Override
	public Cliente buscarPorId(int _id) throws SQLException {
		// TODO Auto-generated method stub
ResultSet rs = null;
		
		String SQL = "Select * from cliente where id=?"; // Com base no id passado por parâmetro ele vai encontrar a pessoa
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id); // Faz a substituição do ? pelo id passado por parâmetro
		// É utilizado o prepared statement ao invés da concatenação dos parâmetros por questão de segurança
		rs = ps.executeQuery();
		

		
		if(rs.next()) {
			Cliente c = new Cliente();
			int id = rs.getInt(1);
			c.setId(id); 
			c.setNome(rs.getString(2)); 
			c.setTel(rs.getString(3));
			
			
			
	
			return c; // Ao encotrar a cliente
		}
		
		return null;
	

		
	}

	/*@Override
	public List<Cliente> listarClientePorCarro(int _idCliente) throws SQLException {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		List<Cliente> cliente = new ArrayList<Cliente>();
		String SQL = "SELECT id, nome, tel  from cliente where Cliente_id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		ps.setInt(1, _idCliente); 
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String tel = rs.getString(3);
			
			Cliente c = new Cliente(id, nome , tel);
			
			cliente.add(c);
		}
		
		return cliente;
	}


	public List<Cliente> listarClientePorHistorico(java.util.Date data) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List<Cliente> cliente = new ArrayList<Cliente>();
		String SQL = "SELECT id, nome, tel  from cliente where Cliente_id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);

		ps.setDate(1, new Date(data.getTime()));
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			String tel = rs.getString(3);
			
			Cliente c = new Cliente(id, nome , tel);
			
			cliente.add(c);
		}
		
		return cliente;
		
		
		}*/
	}


