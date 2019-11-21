package control;

import java.io.Serializable;
import java.sql.Connection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.HistoricoDAO;

import util.FabricaConexao;
import util.JSFUtil;
import model.Historico;

@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Historico historico;
	private ListDataModel<Historico> historicos;
	private Boolean editando;
	private Historico historicoAntesDaEdicao;
	
	public HistoricoBean() {
		this.historico = new Historico();
		this.historicos = new ListDataModel<Historico>();
		this.editando = false;
	}
	
	public Historico getHistorico() {
		return historico;
	}
	
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public ListDataModel<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(ListDataModel<Historico> historicos) {
		this.historicos = historicos;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.historico = new Historico();
	}
	
	public void Editar(Historico _historico) {
		this.historicoAntesDaEdicao = _historico.clone();
		this.historico = _historico;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.historico.restaurarHistorico(this.historicoAntesDaEdicao);
		this.historico = new Historico();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.historico = this.historicos.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			HistoricoDAO dao = new HistoricoDAO(conexao);
			
			dao.Atualizar(this.historico);
			
			JSFUtil.adicionarMensagemSucesso("Historico alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.historico = new Historico();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			HistoricoDAO dao = new HistoricoDAO(conexao);
			
			dao.Inserir(this.historico);
			
			ListDataModel<Historico> listaHistoricos = new ListDataModel<>(dao.listarTodos());
			this.historicos = listaHistoricos;
			
			this.historico = new Historico();
			
			JSFUtil.adicionarMensagemSucesso("Historico cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			HistoricoDAO dao = new HistoricoDAO(conexao);
			
			dao.Excluir(this.historico.getData());
			
			ListDataModel<Historico> listaHistoricos = new ListDataModel<>(dao.listarTodos());
			this.historicos = listaHistoricos;
			
			this.historico = new Historico();
			
			JSFUtil.adicionarMensagemSucesso("Historico excluída com sucesso!");
			
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
			
			HistoricoDAO dao = new HistoricoDAO(conexao);
			
			this.historicos = new ListDataModel<>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
