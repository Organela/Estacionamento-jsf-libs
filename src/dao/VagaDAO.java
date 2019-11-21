package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import model.Carro;
import model.Vaga;

public class VagaDAO implements VagaInDAO {
	
	private Connection conexao;
	
	
	public VagaDAO(Connection _conn) {
		this.conexao = _conn;
	}
	
	@Override
	public void Inserir(Vaga _objeto) throws SQLException {
		// TODO Auto-generated method stub
		String SQL = "insert into vaga (status) values (?)"; // Os ? são parâmetros para o sql
		
		java.sql.PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		
		ps.setBoolean(2, _objeto.isStatus()); // Ele seta o parâmetro do segundo ?
	
		
		
		ps.execute(); // Executa a query
	}

	@Override
	public List<Vaga> listarTodos() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Vaga> vaga = new ArrayList<Vaga>();
		ResultSet rs = null;
		
		String SQL = "select id, status from vaga";
				
		java.sql.PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery(); // Retorna um ResultSet com os resultados diferentemente do execute() que retorna apenas booleano
		
		while(rs.next()) { // Percorre a tabela enquanto tiverem registros
			// Caso tenham 5 vagas cadastradas, esse while rodará 5 vezes (No caso nessa query)
			
			Vaga v = new Vaga();
			
			// Configurando os atributos da vaga a ser adicionada a lista
			/*int id = rs.getInt(1);
			String nome = rs.getString(2);
			String telefone = rs.getString(3);
			String email = rs.getString(4);*/
			
			
			v.setId(rs.getInt(1)); 
			v.setStatus(rs.getBoolean(2));
			// Get baseado no tipo da coluna (getInt, getString) e o inteiro é o número da coluna na query,
			
			vaga.add(v); // A vaga é adicionada a lista de vaga
		}
		
		return vaga;
		
		
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
		// TODO Auto-generated method stub
		boolean rs = false;
		
		String SQL = "delete from vaga where id=?";

				
		java.sql.PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.execute(); // Caso a query seja executada com sucesso retornará um valor booleano
		return rs; 
		
	}

	@Override
	public Boolean Atualizar(Vaga _objeto) throws SQLException {
		// TODO Auto-generated method stub
		boolean rs = false;
		
		String SQL = "update vaga set status=? where id=?";
				
		java.sql.PreparedStatement ps = this.conexao.prepareStatement(SQL);
		

		
		ps.setBoolean(1, _objeto.isStatus());
		ps.setInt(2, _objeto.getId());
		
		rs = ps.execute(); // Caso a query seja executada com sucesso retornará um valor booleano
		
		return rs;
		
	}

	@Override
	public Vaga buscarPorId(int _id) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String SQL = "Select * from vaga where id=?"; // Com base no id passado por parâmetro ele vai encontrar a vaga
				
		java.sql.PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id); // Faz a substituição do ? pelo id passado por parâmetro
		// É utilizado o prepared statement ao invés da concatenação dos parâmetros por questão de segurança
		rs = ps.executeQuery();
		

		
		if(rs.next()) {
			Vaga v = new Vaga();
			
			int id = rs.getInt(1);
			v.setId(id); 
			
			
			
			
	
			return v; // Ao encotrar a vaga
		}
		return null;
	}

	/*@Override
	public List<Vaga> listarVagaPorHistorico(java.util.Date data) throws SQLException {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		List<Vaga> vaga = new ArrayList<Vaga>();
		String SQL = "SELECT id, status from vaga where Vaga_id = ?";
		
		java.sql.PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setDate(1, new Date(data.getTime()));
		rs = ps.executeQuery();
		
		while (rs.next()) {
			
			Vaga v = new Vaga();
			
			v.setId(rs.getInt(1));
			v.setStatus(rs.getBoolean(2));
			
		
			
			
			
			vaga.add(v);
		}
		
		return vaga;
	}*/
		
}
	
	
		
	
