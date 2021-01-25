/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import dao.Problema;
import dao.ProblemaDAO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import serial.SerialRxTx;
import telas.JanelaBairro;
import telas.JanelaEquipamento;
import telas.JanelaOcorrencia;
import telas.JanelaUsuarios;

/**
 *
 * @author Leo
 */
public class MenuPrincipal extends javax.swing.JFrame implements Runnable {

    private List<Problema> lista;
    private int idproblemaatual;
    private int idprimeiraconsultaproblema;

    public MenuPrincipal() {
        Login login = new Login(this, true);
        login.setLocationRelativeTo(null);
        login.setVisible(true);

        if (login.acesso) {
            initComponents();
            setLookAndFeel();
            primeiraConsultaProblema();
            Thread processo = new Thread(this);
            processo.start();
            SerialRxTx serial = new SerialRxTx();
            
        } else {
            System.exit(0);
        }

        try {

            img = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("fundoprincipal.jpg"), "fundoprincipal.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        jDesktopPane1 = new JDesktopPane() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) {
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

                } else {
                    g.drawString("Image not found", 50, 50);
                }
            }
        };

        setContentPane(jDesktopPane1);

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                verificandoProblema(); //vai no banco buscar o id atual 
                if (idproblemaatual > idprimeiraconsultaproblema) { //se id atual for maior que o id pego ao iniciar avisa que teve insert no problema
                    System.out.println("Id atual " + idproblemaatual);
                    JOptionPane.showMessageDialog(null, "Um equipamento apresentou problema, codigo do problema: "+ idproblemaatual);
                    break;
                } else if (idproblemaatual <= idprimeiraconsultaproblema) { //se for menor ou continuar igual continua a execução
                    System.out.println("Id atual " + idproblemaatual);
                    continue;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Banco sem Conexão");
            }
        }
    }

    public void primeiraConsultaProblema() { 
        ProblemaDAO primeirodao = new ProblemaDAO();
        lista = primeirodao.PrimeiraConsultaProblema();
        for (Problema pb1 : lista) {
            pb1.getIdprimeiraconsulta();
            idprimeiraconsultaproblema = pb1.getIdprimeiraconsulta(); //pega o ultimo codigo da tabela problema e guarda ao iniciar o sistema
        };

        System.out.println("Primeiro Codigo " + idprimeiraconsultaproblema);
    }

    public void verificandoProblema() {
        ProblemaDAO segundodao = new ProblemaDAO();
        lista = segundodao.CodigoProblemaAtual();
        for (Problema pb2 : lista) {
            pb2.getIdproblemaatual();
            idproblemaatual = pb2.getIdproblemaatual();

        };
    }

    public void comparaId() {
        while (true) {
            if (idproblemaatual > idprimeiraconsultaproblema) {
                System.out.println("Id atual " + idproblemaatual);
                JOptionPane.showMessageDialog(null, "Equipamento com problema, codigo do problema: " + idproblemaatual);
                break;
            } else if (idproblemaatual <= idprimeiraconsultaproblema) {
                System.out.println("Id atual " + idproblemaatual);

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Monitoramento");
        setExtendedState(6);

        jMenu1.setText("Cadastro");

        jMenuItem3.setText("Bairro");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Usuarios");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Equipamento");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Monitoramento");

        jMenuItem5.setText("Ocorrencias");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JanelaUsuarios jusuario = new JanelaUsuarios();
        jusuario.setVisible(true);
        jDesktopPane1.add(jusuario);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JanelaBairro jbairro = new JanelaBairro();
        jbairro.setVisible(true);
        jDesktopPane1.add(jbairro);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JanelaEquipamento jbairro = new JanelaEquipamento();
        jbairro.setVisible(true);
        jDesktopPane1.add(jbairro);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JanelaOcorrencia janelaocorrencia = new JanelaOcorrencia();
       janelaocorrencia.setVisible(true);
       jDesktopPane1.add(janelaocorrencia);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    private void setLookAndFeel() {
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            System.out.println(laf);

            //if (laf.getName().equalsIgnoreCase("Nimbus")){
            try {

                UIManager.setLookAndFeel(laf.getClassName());
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

            } catch (Exception e) {
                e.printStackTrace();
            }
            // }

        }
    }

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);

            }
        });

    }

    private javax.swing.JDesktopPane jDesktopPane1;
    private Image img;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}