package control;

import java.io.Serializable;
import java.sql.Connection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.ClienteDAO;

import util.FabricaConexao;
import util.JSFUtil;
import model.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private ListDataModel<Cliente> clientes;
	private Boolean editando;
	private Cliente clienteAntesDaEdicao;
	
	public ClienteBean() {
		this.cliente = new Cliente();
		this.clientes = new ListDataModel<Cliente>();
		this.editando = false;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ListDataModel<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ListDataModel<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.cliente = new Cliente();
	}
	
	public void Editar(Cliente _cliente) {
		this.clienteAntesDaEdicao = _cliente.clone();
		this.cliente = _cliente;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.cliente.restaurarCliente(this.clienteAntesDaEdicao);
		this.cliente = new Cliente();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.cliente = this.clientes.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conexao);
			
			dao.Atualizar(this.cliente);
			
			JSFUtil.adicionarMensagemSucesso("Cliente alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.cliente = new Cliente();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conexao);
			
			dao.Inserir(this.cliente);
			
			ListDataModel<Cliente> listaClientes = new ListDataModel<>(dao.listarTodos());
			this.clientes = listaClientes;
			
			this.cliente = new Cliente();
			
			JSFUtil.adicionarMensagemSucesso("Cliente cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conexao);
			
			dao.Excluir(this.cliente.getId());
			
			ListDataModel<Cliente> listaClientes = new ListDataModel<>(dao.listarTodos());
			this.clientes = listaClientes;
			
			this.cliente = new Cliente();
			
			JSFUtil.adicionarMensagemSucesso("Cliente excluída com sucesso!");
			
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
			
			ClienteDAO dao = new ClienteDAO(conexao);
			
			this.clientes = new ListDataModel<>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
