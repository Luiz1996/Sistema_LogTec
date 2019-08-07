/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ExcelController;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Email;

/**
 *
 * @author luiz.pereira
 */
public class EnvioEmailView extends javax.swing.JFrame {
    Email email = new Email();
    JTable tblResultado;
    ExcelController excC;
    
    /**
     * Creates new form EnvioEmailView
     * @param tblResultado
     * @param excC
     */
    public EnvioEmailView(JTable tblResultado, ExcelController excC) {    
        initComponents();
        setTitle("Sistema LogTec - Enviar Logs");
        setIcon(); 
        this.tblResultado = tblResultado;
        this.excC = excC;
        setVisible(true);
    }
    
    public EnvioEmailView() {
        initComponents();
        setTitle("Sistema LogTec - Enviar Logs");
        setIcon(); 
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEmailDest = new javax.swing.JLabel();
        txtEmailDest = new javax.swing.JTextField();
        separador1 = new javax.swing.JSeparator();
        sclPane = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();
        lblMensg = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblEmailDest.setText("Destinatário");

        txtMensagem.setColumns(20);
        txtMensagem.setRows(5);
        sclPane.setViewportView(txtMensagem);

        lblMensg.setText("Mensagem");

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sclPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addComponent(separador1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMensg)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEmailDest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailDest))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEnviar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmailDest)
                    .addComponent(txtEmailDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMensg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sclPane, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnviar)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        File arq = new File("c:\\Arquivo de Log - LogTec.xlsx");
        
        if(!txtEmailDest.getText().equals("".trim()) && !txtMensagem.getText().equals("".trim())){    
            
            //criando arquivo Excel

            this.setCursor(cursor);
            excC.exportarExcel(false, tblResultado, arq);
            
            //carregando dados a serem enviados no e-mail
            email.setCorpoEmail(txtMensagem.getText().trim());
            email.setEmailDestinatario(txtEmailDest.getText().trim());
            File[] Anexo = new File[1];
            Anexo[0] = arq;
            email.setAnexo(Anexo);
            
            //enviando e-mail
            try {
                email.enviarEmail();
            } catch (MessagingException ex) {
                Logger.getLogger(EnvioEmailView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao enviar e-mail.\nError: ".concat(ex.getMessage().trim()), "Falha no envio de e-mail", JOptionPane.ERROR_MESSAGE);
            }
            
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Certifique-se de que os campos 'Destinatário' e 'Mensagem' estejam preenchidos.", "Falha no envio de e-mail", JOptionPane.ERROR_MESSAGE);
        }
        arq.delete();
        System.gc();
        
        cursor = Cursor.getDefaultCursor();
        this.setCursor(cursor);
    }//GEN-LAST:event_btnEnviarActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EnvioEmailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnvioEmailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnvioEmailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnvioEmailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EnvioEmailView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel lblEmailDest;
    private javax.swing.JLabel lblMensg;
    private javax.swing.JScrollPane sclPane;
    private javax.swing.JSeparator separador1;
    private javax.swing.JTextField txtEmailDest;
    private javax.swing.JTextArea txtMensagem;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icone.png")));
    }
}
