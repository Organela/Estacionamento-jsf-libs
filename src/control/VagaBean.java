package control;

import java.io.Serializable;
import java.sql.Connection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.VagaDAO;

import util.FabricaConexao;
import util.JSFUtil;
import model.Vaga;

@ManagedBean
@ViewScoped
public class VagaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Vaga vaga;
	private ListDataModel<Vaga> vagas;
	private Boolean editando;
	private Vaga vagaAntesDaEdicao;
	
	public VagaBean() {
		this.vaga = new Vaga();
		this.vagas = new ListDataModel<Vaga>();
		this.editando = false;
	}
	
	public Vaga getVaga() {
		return vaga;
	}
	
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public ListDataModel<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(ListDataModel<Vaga> vagas) {
		this.vagas = vagas;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.vaga = new Vaga();
	}
	
	public void Editar(Vaga _vaga) {
		this.vagaAntesDaEdicao = _vaga.clone();
		this.vaga = _vaga;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.vaga.restaurarVaga(this.vagaAntesDaEdicao);
		this.vaga = new Vaga();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.vaga = this.vagas.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			VagaDAO dao = new VagaDAO(conexao);
			
			dao.Atualizar(this.vaga);
			
			JSFUtil.adicionarMensagemSucesso("Vaga alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.vaga = new Vaga();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			VagaDAO dao = new VagaDAO(conexao);
			
			dao.Inserir(this.vaga);
			
			ListDataModel<Vaga> listaVagas = new ListDataModel<>(dao.listarTodos());
			this.vagas = listaVagas;
			
			this.vaga = new Vaga();
			
			JSFUtil.adicionarMensagemSucesso("Vaga cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			VagaDAO dao = new VagaDAO(conexao);
			
			dao.Excluir(this.vaga.getId());
			
			ListDataModel<Vaga> listaVagas = new ListDataModel<>(dao.listarTodos());
			this.vagas = listaVagas;
			
			this.vaga = new Vaga();
			
			JSFUtil.adicionarMensagemSucesso("Vaga excluída com sucesso!");
			
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
			
			VagaDAO dao = new VagaDAO(conexao);
			
			this.vagas = new ListDataModel<>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
