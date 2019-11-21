package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.Connection;
import java.sql.Connection;

import model.Carro;
import model.Funcionario;

public class FuncionarioDAO implements FuncionarioInDAO {

	private Connection conexao;
	
	public FuncionarioDAO(Connection _conn) {
		this.conexao = _conn;
	}

	@Override
	public void Inserir(Funcionario _objeto) throws SQLException {
		// TODO Auto-generated method stub
		
		String SQL = "INSERT INTO funcionario (nome, tel) VALUES (?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		
			
				ps.setString(1, _objeto.getNome());//Seta o paramentro do segundo `?`
				ps.setString(2, _objeto.getTel());
			
		
		
		ps.execute();
	}

	@Override
	public List<Funcionario> listarTodos() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Funcionario> funcionario = new ArrayList<Funcionario>();
		ResultSet rs = null;
		
		String SQL = "select id, nome, tel from funcionario";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery(); // Retorna um ResultSet com os resultados diferentemente do execute() que retorna apenas booleano
		
		while(rs.next()) { // Percorre a tabela enquanto tiverem registros
			// Caso tenham 5 funcionarios cadastradoss, esse while rodará 5 vezes (No caso nessa query)
			Funcionario f = new Funcionario();
			
			// Configurando os atributos de funcionario a ser adicionada a lista
			/*int id = rs.getInt(1);
			String nome = rs.getString(2);
			String telefone = rs.getString(3);
			String email = rs.getString(4);*/
			f.setId(rs.getInt(1)); 
			f.setNome(rs.getString(2)); // rs.getString(2)-> vai retornar o nome do funcionario do banco de dados
			f.setTel(rs.getString(3));
			
			// Get baseado no tipo da coluna (getInt, getString) e o inteiro é o número da coluna na query,
			
			

			
			funcionario.add(f); // A pessoa é adicionada a lista de funcionarios
		}
		
		return funcionario;
		
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
		// TODO Auto-generated method stub
		boolean rs = false;
		
		String SQL = "delete from funcionario where id=?";

				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.execute(); // Caso a query seja executada com sucesso retornará um valor booleano
		
		return rs; 
		
	}

	@Override
	public Boolean Atualizar(Funcionario _objeto) throws SQLException {
		// TODO Auto-generated method stub
		boolean rs = false;
		
		String SQL = "update funcionario set tel=?, nome=? where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		

		ps.setInt(1, _objeto.getId());
		ps.setString(2, _objeto.getNome());
		ps.setString(3, _objeto.getTel());
		
		
		rs = ps.execute(); // Caso a query seja executada com sucesso retornará um valor booleano
		
		return rs;
		
	}

	@Override
	public Funcionario buscarPorId(int _id) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String SQL = "Select * from funcionario where id=?"; // Com base no id passado por parâmetro ele vai encontrar o funcionário
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id); // Faz a substituição do ? pelo id passado por parâmetro
		// É utilizado o prepared statement ao invés da concatenação dos parâmetros por questão de segurança
		rs = ps.executeQuery();
		

		
		if(rs.next()) {
			Funcionario f = new Funcionario();
			int id = rs.getInt(1);
			f.setId(id); 
			f.setNome(rs.getString(2)); 
			f.setTel(rs.getString(3));
			 
			
			
			
	
			return f; // Ao encotrar funcionario
		}
		
	
	
		return null;
	}

	/*public List<Funcionario> listarFuncionarioPorHistorico(java.util.Date data) throws SQLException {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		List<Funcionario> funcionario = new ArrayList<Funcionario>();
		String SQL = "SELECT * from endereco where pessoa_id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		ps.setDate(1, new Date(data.getTime()));
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			Funcionario f = new Funcionario();
			
			f.setId(rs.getInt(1));
			f.setNome(rs.getString(2));
			f.setTel(rs.getString(3));
			
			funcionario.add(f);
		}
		
		return funcionario;
		
		
	}*/

}