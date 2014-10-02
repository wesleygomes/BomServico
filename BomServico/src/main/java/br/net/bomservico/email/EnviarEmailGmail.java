package br.net.bomservico.email;

import java.util.Date;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@ManagedBean (name="emailGmailMB")
@ViewScoped
public class EnviarEmailGmail {
    public static final String SERVIDOR_SMTP="smtp.gmail.com";
    public static final String PORTA_SERVIDOR_SMTP="465";
    public static final String CONTA_GMAIL="admbomservico@gmail.com";
    public static final String SENHA_GMAIL="mfca1226";
    
    private String de="falecomweslley@gmail.com";
    
    private String assunto="Bom Servi�o - Prestadores de Servi�o";
    
    
    public void enviarEmail(String para, String mensagem){
     FacesContext context = FacesContext.getCurrentInstance();
     AutenticaUsuario autenticaUsuario= new AutenticaUsuario(EnviarEmailGmail.CONTA_GMAIL, EnviarEmailGmail.SENHA_GMAIL);
     Session session = Session.getDefaultInstance(this.configuracaoEmail(), autenticaUsuario);
     //Habilita o LOG das ações executadas durante o envio do email
     session.setDebug(true);
     
     try{
         Transport envio=null;
         MimeMessage email = new MimeMessage(session);
         email.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
         email.setFrom(new InternetAddress(this.de));
         email.setSubject(this.assunto);
         email.setContent(mensagem, "text/plain");
         email.setSentDate(new Date());
         
         envio= session.getTransport("smtp");
         envio.connect(EnviarEmailGmail.SERVIDOR_SMTP, EnviarEmailGmail.CONTA_GMAIL, EnviarEmailGmail.SENHA_GMAIL);
         email.saveChanges();
         envio.sendMessage(email, email.getAllRecipients());
         envio.close();
         
         context.addMessage(null, new FacesMessage("E-mail enviado com sucesso"));
     }
     catch(AddressException e){
         FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: "+e.getMessage());
         context.addMessage(null, msg);
         return;
     }
     catch(MessagingException e){
         FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: "+e.getMessage());
         context.addMessage(null, msg);
         return;
     }
    }
    

    private Properties configuracaoEmail() {
         Properties config = new Properties();
         //Configuração adicional para servidor proxy
         //Descomentar somente se utiliza servidor com proxy
         /*
         props.setProperty("proxySet","true");
         props.setProperty("socksProxyHost","127.0.0.1"); //Ip do servidor de proxy
         props.setProperty("socksProxyHost","8080"); // porta do servidor proxy
         */
         
         config.put("mail.transport.protocol", "smtp");
         config.put("mail.smtp.starttls.enable", "true");
         config.put("mail.smtp.host", SERVIDOR_SMTP);
         config.put("mail.smtp.auth", "true");
         config.put("mail.smtp.user", EnviarEmailGmail.CONTA_GMAIL);
         config.put("mail.debug", "true");
         config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
         config.put("mail.smtp.socketFactory.port", PORTA_SERVIDOR_SMTP);
         config.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         config.put("mail.smtp.socketFactory.fallback", "false");
         return config;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
}



