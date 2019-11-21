package control;

import java.io.Serializable;
import java.sql.Connection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.CarroDAO;

import util.FabricaConexao;
import util.JSFUtil;
import model.Carro;

@ManagedBean
@ViewScoped
public class CarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Carro carro;
	private ListDataModel<Carro> carros;
	private Boolean editando;
	private Carro carroAntesDaEdicao;
	
	public CarroBean() {
		this.carro = new Carro();
		this.carros = new ListDataModel<Carro>();
		this.editando = false;
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public ListDataModel<Carro> getCarros() {
		return carros;
	}

	public void setCarros(ListDataModel<Carro> carros) {
		this.carros = carros;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.carro = new Carro();
	}
	
	public void Editar(Carro _carro) {
		this.carroAntesDaEdicao = _carro.clone();
		this.carro = _carro;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.carro.restaurarCarro(this.carroAntesDaEdicao);
		this.carro = new Carro();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.carro = this.carros.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CarroDAO dao = new CarroDAO(conexao);
			
			dao.Atualizar(this.carro);
			
			JSFUtil.adicionarMensagemSucesso("Carro alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.carro = new Carro();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CarroDAO dao = new CarroDAO(conexao);
			
			dao.Inserir(this.carro);
			
			ListDataModel<Carro> listaCarros = new ListDataModel<>(dao.listarTodos());
			this.carros = listaCarros;
			
			this.carro = new Carro();
			
			JSFUtil.adicionarMensagemSucesso("Carro cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CarroDAO dao = new CarroDAO(conexao);
			
			dao.Excluir(this.carro.getId());
			
			ListDataModel<Carro> listaCarros = new ListDataModel<>(dao.listarTodos());
			this.carros = listaCarros;
			
			this.carro = new Carro();
			
			JSFUtil.adicionarMensagemSucesso("Carro excluída com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CarroDAO dao = new CarroDAO(conexao);
			
			this.carros = new ListDataModel<>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
