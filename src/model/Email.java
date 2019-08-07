/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.HeadlessException;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.pereira
 */
public class Email {
    private File[] Anexo;
    private String emailDestinatario;
    private String corpoEmail;

    public File[] getAnexo() {
        return Anexo;
    }

    public void setAnexo(File[] Anexo) {
        this.Anexo = Anexo;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getCorpoEmail() {
        return corpoEmail;
    }

    public void setCorpoEmail(String corpoEmail) {
        this.corpoEmail = corpoEmail;
    }
    
    public void enviarEmail() throws AddressException, MessagingException {
        try {
            //config. do gmail
            Properties mailProps = new Properties();
            mailProps.put("mail.transport.protocol", "smtp");
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.put("mail.smtp.host", "smtp.gmail.com");
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.user", "SEU_EMAIL");
            mailProps.put("mail.debug", "true");
            mailProps.put("mail.smtp.port", "465");
            mailProps.put("mail.smtp.socketFactory.port", "465");
            mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            mailProps.put("mail.smtp.socketFactory.fallback", "false");

            //autenticação
            Session mailSession = Session.getInstance(mailProps, new javax.mail.Authenticator() {
                @Override
                public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication("SEU_EMAIL", "SENHA_EMAIL");
                }
            });
            
            mailSession.setDebug(false);

            //config. da mensagem
            Message mailMessage = new MimeMessage(mailSession);

            //remetente
            mailMessage.setFrom(new InternetAddress("SEU_EMAIL"));

            //destinatário
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));

            //corpo do e-mail
            MimeBodyPart mbpMensagem = new MimeBodyPart();
            mbpMensagem.setText(corpoEmail);

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbpMensagem);
            
            //carregando anexos
            String Endereco_Anexo = "";
            if (Anexo != null) {
                for (File element : Anexo) {
                    Endereco_Anexo = element.getPath();
                    String imagem = Endereco_Anexo;
                    File Arquivo = new File(imagem);
                    
                    //setando o anexo
                    MimeBodyPart mbpAnexo = new MimeBodyPart();
                    mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(Arquivo)));
                    mbpAnexo.setFileName(Arquivo.getName());
                    mp.addBodyPart(mbpAnexo);
                }
            }

            //assunto
            mailMessage.setSubject("Sistema LogTec");

            //seleciona o conteúdo 
            mailMessage.setContent(mp);

            //envia o e-mail
            Transport.send(mailMessage);
            JOptionPane.showMessageDialog(null, "Logs enviados com sucesso!", "Sucesso no envio", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro no envio do e-mail!\nErro: ".concat(e.getMessage().trim()), "Falha no envio", JOptionPane.ERROR_MESSAGE);
        }
    }
}
