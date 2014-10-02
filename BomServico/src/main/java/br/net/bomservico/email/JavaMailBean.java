package br.net.bomservico.email;

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.*;

@ManagedBean(name = "javaMailBeanMB")
@ViewScoped
public class JavaMailBean {

	public static final String SERVIDOR_SMTP = "localhost";
	public static final int PORTA_SERVIDOR_SMTP = 25;
	public static final String CONTA_PADRAO = "adm@localhost";
	public static final String SENHA_CONTA_PADRAO = "123";
	private String de = "hito.pg@hotmail.com";
	private String para;
	private String destinatariosNormais;
	private String destinatariosOcultos;
	private String assunto;
	private String mensagem;
	private String anexo;

	public void enviarAutenticado() throws MessagingException {
		FacesContext context = FacesContext.getCurrentInstance();
		Properties config = new Properties();
		config.put("mail.smtp.host", JavaMailBean.SERVIDOR_SMTP);
		config.put("mail.smtp.host", JavaMailBean.PORTA_SERVIDOR_SMTP);
		config.put("mail.smtp.auth", "true");

		Session sessao = Session.getInstance(config, new AutenticaUsuario(
				JavaMailBean.CONTA_PADRAO, JavaMailBean.SENHA_CONTA_PADRAO));

		try {
			MimeMessage email = new MimeMessage(sessao);
			email.setFrom(new InternetAddress(this.de));
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(
					this.para));

			InternetAddress[] normais = this
					.montaDestinatarios(this.destinatariosNormais);
			if (normais != null) {
				email.setRecipients(Message.RecipientType.TO, normais);
			}
			InternetAddress[] ocultos = this
					.montaDestinatarios(this.destinatariosOcultos);
			if (ocultos != null) {
				email.setRecipients(Message.RecipientType.TO, ocultos);
			}
			email.setSubject(this.assunto);
			email.setSentDate(new Date());

			// Preparando o corpo de email
			MimeMultipart partesEmail = new MimeMultipart();
			MimeBodyPart corpoEmail = new MimeBodyPart();
			corpoEmail.setContent(this.mensagem, "text/html");
			partesEmail.addBodyPart(corpoEmail);

			// anexando arquivo
			if (this.anexo != null && this.anexo.trim().length() > 0) {
				MimeBodyPart anexo = new MimeBodyPart();
				FileDataSource arquivo = new FileDataSource(this.anexo);
				anexo.setDataHandler(new DataHandler(arquivo));
				anexo.setFileName(arquivo.getName());
				partesEmail.addBodyPart(anexo);
			}

			email.setContent(partesEmail);

			Transport.send(email);

			context.addMessage(null, new FacesMessage(
					"E-mail enviado com sucesso"));

		} catch (AddressException e) {
			FacesMessage msg = new FacesMessage(
					"Erro ao montar mensagem de e-mail! Erro: "
							+ e.getMessage());
			context.addMessage(null, msg);
			return;
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail Erro: "
					+ e.getMessage());
			context.addMessage(null, msg);
			return;
		}
	}

	public void enviarSimples() throws MessagingException {
		FacesContext context = FacesContext.getCurrentInstance();
		Properties config = new Properties();
		config.put("mail.smtp.host", JavaMailBean.SERVIDOR_SMTP);
		config.put("mail.smtp.host", JavaMailBean.PORTA_SERVIDOR_SMTP);

		Session sessao = Session.getInstance(config);

		try {
			MimeMessage email = new MimeMessage(sessao);
			email.setFrom(new InternetAddress(this.de));
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(
					this.para));

			InternetAddress[] normais = this
					.montaDestinatarios(this.destinatariosNormais);
			if (normais != null) {
				email.setRecipients(Message.RecipientType.CC, normais);
			}

			InternetAddress[] ocultos = this
					.montaDestinatarios(this.destinatariosOcultos);
			if (ocultos != null) {
				email.setRecipients(Message.RecipientType.CC, ocultos);
			}

			email.setSubject(this.assunto);
			email.setSentDate(new Date());
			email.setText(this.mensagem);
			Transport.send(email);

			context.addMessage(null, new FacesMessage(
					"E-mail enviado com sucesso"));
		} catch (AddressException e) {
			FacesMessage msg = new FacesMessage(
					"Erro ao montar mensagem de e-mail! Erro: "
							+ e.getMessage());
			context.addMessage(null, msg);
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: "
					+ e.getMessage());
			context.addMessage(null, msg);
		}

	}

	private InternetAddress[] montaDestinatarios(String destinatarios)
			throws AddressException {
		InternetAddress[] emails = null;
		if (destinatarios != null && destinatarios.trim().length() > 0) {
			String[] lista = destinatarios.split(";");
			emails = new InternetAddress[lista.length];
			for (int i = 0; i < lista.length; i++) {
				emails[i] = new InternetAddress(lista[i]);
			}
		}
		return emails;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getDestinatariosNormais() {
		return destinatariosNormais;
	}

	public void setDestinatariosNormais(String destinatariosNormais) {
		this.destinatariosNormais = destinatariosNormais;
	}

	public String getDestinatariosOcultos() {
		return destinatariosOcultos;
	}

	public void setDestinatariosOcultos(String destinatariosOcultos) {
		this.destinatariosOcultos = destinatariosOcultos;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

}
