package br.net.bomservico.controller;

import java.io.Serializable;
import br.net.bomservico.beans.Cidade;
import br.net.bomservico.beans.Endereco;
import br.net.bomservico.beans.Servico;
import br.net.bomservico.beans.Uf;
import br.net.bomservico.beans.Usuario;
import br.net.bomservico.beans.Usuario_Servico;
import br.net.bomservico.dao.CidadeDAO;
import br.net.bomservico.dao.EnderecoDAO;
import br.net.bomservico.dao.ServicoDAO;
import br.net.bomservico.email.EnviarEmailGmail;
import br.net.bomservico.dao.UfDAO;
import br.net.bomservico.dao.UsuarioDAO;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "Login_Alteracao_ServicoMB")
@SessionScoped
public class Login_Alteracao_Servico implements Serializable {
	private static final long serialVersionUID = -1021837887392444266L;

	// Estado e Cidade
	String estadoSelecionado, cidadeSelecionada;
	int idCidadeSelecionada;
	private Map<String, String> estados = new HashMap<String, String>();
	private Map<String, Map<String, String>> suburbsData = new HashMap<String, Map<String, String>>();
	private Map<String, String> suburbs = new HashMap<String, String>();
	Map<String, String> cidades;
	Uf uf = null;
	UfDAO UfDAO = null;
	Cidade cidade = null;
	CidadeDAO cidadeDao = null;
	// Fim

	// Login
	boolean sessionLogin = false;
	// Parte de Envio de Email
	EnviarEmailGmail envioEmail = null;
	String emailIndicado;
	// Fim

	Usuario_Servico usuario_servico = null;
	ArrayList<Uf> arrayUf = null;
	ArrayList<Cidade> arrayCidade = null;
	ArrayList<Endereco> arrayEndereco = null;
	ArrayList<Usuario> arrayUsuario = null;
	ArrayList<Servico> arrayServico = null;
	Endereco endereco = null;
	EnderecoDAO enderecoDao = null;
	Usuario usuario = null;
	UsuarioDAO usuarioDao = null;
	Servico servico = null;
	ServicoDAO servicoDao = null;

	// Login e Alteração do Serviço
	public Login_Alteracao_Servico() {
		// Envia Email
		envioEmail = new EnviarEmailGmail();

		usuario_servico = new Usuario_Servico();
		uf = new Uf();
		UfDAO = new UfDAO();

		cidade = new Cidade();
		cidadeDao = new CidadeDAO();

		endereco = new Endereco();
		enderecoDao = new EnderecoDAO();

		usuario = new Usuario();
		usuarioDao = new UsuarioDAO();
		servico = new Servico();
		servicoDao = new ServicoDAO();

	}

	public String cLogin() throws MalformedURLException, IOException {

		boolean r = false;
		setArrayUsuario(usuarioDao.buscarLogin(usuario.getLogin(),
				usuario.getSenha()));
		if (!arrayUsuario.isEmpty()) {
			usuario.setIdusuario(arrayUsuario.get(0).getIdusuario());
			usuario.setNome(arrayUsuario.get(0).getNome());
			usuario.setSobrenome(arrayUsuario.get(0).getSobrenome());
			usuario.setEmail(arrayUsuario.get(0).getEmail());
			usuario.setSexo(arrayUsuario.get(0).getSexo());
			usuario.setLogin(arrayUsuario.get(0).getLogin());
			usuario.setSenha(arrayUsuario.get(0).getSenha());
			usuario.setCpf(arrayUsuario.get(0).getCpf());
			usuario.setFoto(arrayUsuario.get(0).getFoto());
			usuario.setCelular(arrayUsuario.get(0).getCelular());
			usuario.setTelefone_fixo(arrayUsuario.get(0).getTelefone_fixo());
			usuario.setData_cadastro(arrayUsuario.get(0).getData_cadastro());

			r = true;
			sessionLogin = true;
			return "index_login.xhtml";

		}

		if (r == false) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Login ou Senha Incorretos!!!");
			context.addMessage(null, facesMessage);
			r = false;
			return "";
		}
		return "";
	}

	public void alterarCliente() {
	}

	public void alterarCliente1() {
		usuarioDao.atualizar(usuario, usuario);
	}

	public String alterarPrestador() {
		servicoDao.atualizar(servico, servico);
		idCidadeSelecionada = Integer.parseInt(cidadeSelecionada);
		endereco.setFk_idcidade(idCidadeSelecionada);
		endereco.setFk_idusuario(usuario.getIdusuario());
		enderecoDao.atualizar(endereco, endereco);

		return "";
	}

	//Cadastra o Serviço
	public String cadastroServico() {

		setArrayUsuario(usuarioDao.buscarLogin(usuario.getLogin(),
				usuario.getSenha()));

		servico.setFk_idusuario(arrayUsuario.get(0).getIdusuario());
		servicoDao.inserir(servico);

		usuario.setIdusuario(arrayUsuario.get(0).getIdusuario());
		usuarioDao.atualizar(usuario, usuario);

		endereco.setFk_idusuario(usuario.getIdusuario());
		endereco.setFk_idcidade(idCidadeSelecionada);
		enderecoDao.inserir(endereco);

		return "servico_sucesso.xhtml";
	}

	//Perfil do Prestador
	public String perfil() {
		usuario.setIdusuario(usuario_servico.getIdusuario());
		usuario.setNome(usuario_servico.getNome());
		usuario.setSobrenome(usuario_servico.getSobrenome());
		usuario.setSexo(usuario_servico.getSexo());
		usuario.setEmail(usuario_servico.getEmail());
		usuario.setLogin(usuario_servico.getLogin());
		usuario.setSenha(usuario_servico.getSenha());
		usuario.setCpf(usuario_servico.getCpf());
		usuario.setFoto(usuario_servico.getFoto());
		usuario.setCelular(usuario_servico.getCelular());
		usuario.setTelefone_fixo(usuario_servico.getTelefone_fixo());
		usuario.setData_cadastro(usuario_servico.getUsuario_data_cadastro());

		return "perfil_cliente.xhtml";
	}

	//Sai do Sistema
	public String deslogar() {

		usuario = null;
		usuario = new Usuario();
		usuario_servico = null;
		usuario_servico = new Usuario_Servico();
		servico.setAtividade("");
		servico.setDescricao("\"Exemplo: Lava, passa, cozinha, arruma. Disponibilidade de segunda a sexta das 8h - 12h e 14h - 18h.\"");
		servico.setSite("Exemplo: pagina do facebook, twitter, etc.");
		servico.setValor_servico(0);
		sessionLogin = false;
		return "index.xhtml";
	}

	//Envia email para usuaios
	public String emailEnviar() {
		String texto = "Prezado(a) Senhor(a). Venho através do senhor(a) "
				+ "'"
				+ usuario.getNome()
				+ "'"
				+ " convidá-lo(a) a participar do nosso sistema.\nwww.bomservico.net.br";
		try {
			envioEmail.enviarEmail(emailIndicado, texto);
		} catch (HeadlessException e) {
		}

		return "email_sucesso.xhtml";
	}

	//Verifica Perfil
	public String verificarPerfil() {
		setArrayServico(servicoDao.buscarIdusuario(usuario.getIdusuario()));
		if (!arrayServico.isEmpty()) {
			servico.setIdservico(arrayServico.get(0).getIdservico());
			servico.setAtividade(arrayServico.get(0).getAtividade());
			servico.setDescricao(arrayServico.get(0).getDescricao());
			servico.setSite(arrayServico.get(0).getSite());
			servico.setData_cadastro(arrayServico.get(0).getData_cadastro());
			servico.setValor_servico(arrayServico.get(0).getValor_servico());
			servico.setFk_idsituacao(arrayServico.get(0).getFk_idsituacao());
			servico.setFk_idusuario(arrayServico.get(0).getFk_idusuario());

			setArrayEndereco(enderecoDao.buscarIdusuario(servico
					.getFk_idusuario()));
			if (!arrayEndereco.isEmpty()) {
				endereco.setIdendereco(arrayEndereco.get(0).getIdendereco());
				endereco.setCep(arrayEndereco.get(0).getCep());
				endereco.setQuadra(arrayEndereco.get(0).getQuadra());
				endereco.setRua(arrayEndereco.get(0).getRua());
				endereco.setNumero(arrayEndereco.get(0).getNumero());
				endereco.setComplemento(arrayEndereco.get(0).getComplemento());
				endereco.setSetor(arrayEndereco.get(0).getSetor());
				endereco.setFk_idcidade(arrayEndereco.get(0).getFk_idcidade());

				setArrayCidade(cidadeDao.buscar(endereco.getFk_idcidade()));
				if (!arrayCidade.isEmpty()) {
					cidade.setId(arrayCidade.get(0).getId());
					cidade.setCidade(arrayCidade.get(0).getCidade());
					cidade.setUf(arrayCidade.get(0).getUf());
					cidadeSelecionada = cidade.getCidade();
					setArrayUf(UfDAO.buscar(cidade.getUf()));
					if (!arrayUf.isEmpty()) {
						uf.setUf(arrayUf.get(0).getUf());
						uf.setEstado(arrayUf.get(0).getEstado());
						estadoSelecionado = uf.getUf();
						uf();
						carregaCidades();
					}
				}
			}

			return "perfil_prestador.xhtml";
		} else {
			return "perfil_cliente.xhtml";
		}
	}

	public String redirecionarEmail() {

		return "index.xhtml";
	}

	public String redirecionarEmailLogin() {

		return "index_login.xhtml";
	}

	public String redirecionarServico() {

		return "cadastro_servico.xhtml";
	}

	public void redirecionar() {
		usuario.setNome(null);
		endereco.setCep("");
		endereco.setComplemento("");
		endereco.setNumero("");
		endereco.setQuadra("");
		endereco.setRua("");
		endereco.setSetor("");
		servico.setAtividade("");
		servico.setDescricao("Exemplo: Lava, passa, cozinha, arruma. Disponibilidade de segunda a sexta das 8h - 12h e 14h - 18h.");
		servico.setSite("Exemplo: pagina do facebook, twitter, etc.");

		setArrayUsuario(usuarioDao.buscarUltimoRegistro());
		setArrayUsuario(usuarioDao.buscar(arrayUsuario.get(0).getIdusuario()));
		usuario.setIdusuario(arrayUsuario.get(0).getIdusuario());
		usuario.setNome(arrayUsuario.get(0).getNome());
		usuario.setSobrenome(arrayUsuario.get(0).getSobrenome());
		usuario.setEmail(arrayUsuario.get(0).getEmail());
		usuario.setSexo(arrayUsuario.get(0).getSexo());
		usuario.setLogin(arrayUsuario.get(0).getLogin());
		usuario.setSenha(arrayUsuario.get(0).getSenha());
		usuario.setCpf(arrayUsuario.get(0).getCpf());
		usuario.setFoto(arrayUsuario.get(0).getFoto());
		usuario.setCelular(arrayUsuario.get(0).getCelular());
		usuario.setTelefone_fixo(arrayUsuario.get(0).getTelefone_fixo());
		usuario.setData_cadastro(arrayUsuario.get(0).getData_cadastro());
		// Thread.sleep(3000);

	}

	// Estado e Cidades.
	private void uf() {
		cidades = new HashMap<String, String>();

		ArrayList<Uf> lista = UfDAO.buscar();
		// preenche a lista de estados
		for (int i = 0; i < lista.size(); i++) {
			estados.put(lista.get(i).getEstado(), lista.get(i).getUf());
		}
	}

	public void carregaCidades() {
		CidadeDAO cdDao = new CidadeDAO();
		List<Cidade> listCidade = cdDao.buscarPorUf(estadoSelecionado);

		cidades = new HashMap<String, String>();

		System.out.println(listCidade.size() + " Quantidade de cidades ");

		System.out.println("Carregando dados....." + estadoSelecionado);

		for (int i = 0; i < listCidade.size(); i++) {
			cidades.put(listCidade.get(i).getCidade(), listCidade.get(i)
					.getId() + "");
		}

	}


	//GETS AND SETS
	public Map<String, String> getEstados() {
		return estados;
	}

	public void setEstados(Map<String, String> estados) {
		this.estados = estados;
	}

	public Map<String, Map<String, String>> getSuburbsData() {
		return suburbsData;
	}

	public void setSuburbsData(Map<String, Map<String, String>> suburbsData) {
		this.suburbsData = suburbsData;
	}

	public Map<String, String> getSuburbs() {
		return suburbs;
	}

	public void setSuburbs(Map<String, String> suburbs) {
		this.suburbs = suburbs;
	}

	public Map<String, String> getCidades() {
		return cidades;
	}

	public void setCidades(Map<String, String> cidades) {
		this.cidades = cidades;
	}

	public String getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(String cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}
	
	public ArrayList<Usuario> getArrayUsuario() {
		return arrayUsuario;
	}

	public void setArrayUsuario(ArrayList<Usuario> arrayUsuario) {
		this.arrayUsuario = arrayUsuario;
	}

	public ArrayList<Servico> getArrayServico() {
		return arrayServico;
	}

	public void setArrayServico(ArrayList<Servico> arrayServico) {
		this.arrayServico = arrayServico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public ServicoDAO getServicoDao() {
		return servicoDao;
	}

	public void setServicoDao(ServicoDAO servicoDao) {
		this.servicoDao = servicoDao;
	}

	public String getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(String estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public int getIdCidadeSelecionada() {
		return idCidadeSelecionada;
	}

	public void setIdCidadeSelecionada(int idCidadeSelecionada) {
		this.idCidadeSelecionada = idCidadeSelecionada;
	}

	public ArrayList<Uf> getArrayUf() {
		return arrayUf;
	}

	public void setArrayUf(ArrayList<Uf> arrayUf) {
		this.arrayUf = arrayUf;
	}

	public ArrayList<Cidade> getArrayCidade() {
		return arrayCidade;
	}

	public void setArrayCidade(ArrayList<Cidade> arrayCidade) {
		this.arrayCidade = arrayCidade;
	}

	public ArrayList<Endereco> getArrayEndereco() {
		return arrayEndereco;
	}

	public void setArrayEndereco(ArrayList<Endereco> arrayEndereco) {
		this.arrayEndereco = arrayEndereco;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public UfDAO getUfDAO() {
		return UfDAO;
	}

	public void setUfDAO(UfDAO UfDAO) {
		this.UfDAO = UfDAO;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public CidadeDAO getCidadeDao() {
		return cidadeDao;
	}

	public void setCidadeDao(CidadeDAO cidadeDao) {
		this.cidadeDao = cidadeDao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public EnderecoDAO getEnderecoDao() {
		return enderecoDao;
	}

	public void setEnderecoDao(EnderecoDAO enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

	public Usuario_Servico getUsuario_servico() {
		return usuario_servico;
	}

	public void setUsuario_servico(Usuario_Servico usuario_servico) {
		this.usuario_servico = usuario_servico;
	}

	public String getEmailIndicado() {
		return emailIndicado;
	}
	public void setEmailIndicado(String emailIndicado) {
		this.emailIndicado = emailIndicado;
	}

	public EnviarEmailGmail getEnvioEmail() {
		return envioEmail;
	}

	public void setEnvioEmail(EnviarEmailGmail envioEmail) {
		this.envioEmail = envioEmail;
	}

}
