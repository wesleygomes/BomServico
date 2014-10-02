package br.net.bomservico.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarEmail {

    public void enviarEmail(String email, String assunto, String texto) {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Hotmail
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("falecomweslley@gmail.com", "wgs012345");
                    }
                });
        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);
        //Altere os seguintes campos:
        //“seuemail@hotmail.com” para a sua conta do Hotmail, que inclusive, serve também para contas de email com domínio: @live, @msn, @passport
        //“suasenha123” utilizando a senha da sua conta.
        //“seuamigo@hotmail.com” endereço do email destinatário. Pode ser qualquer provedor de email.
        //Pronto! É só compilar! 
        //Email enviado com sucesso!!!
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("falecomweslley@gmail.com")); //Remetente
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email)); //Destinatário(s)
            message.setSubject(assunto);//Assunto
            message.setText(texto);
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
