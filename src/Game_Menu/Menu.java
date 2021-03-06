/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Menu;

import static Game_Display.Display.frame;
import Game_Highscore.Highscore;
import Game_SetUp.GameSetUp;
import static Game_SignIn.SignIn.dbConn;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 2016
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public static String Uname;

    public Menu() {
        initComponents();
    }

    public Menu(String name) {
        initComponents();
        Uname = name;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Play = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        Highscore = new javax.swing.JButton();
        AboutUs = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 150));
        setPreferredSize(new java.awt.Dimension(400, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        Play.setBackground(new java.awt.Color(121, 28, 21));
        Play.setText("Play");
        Play.setBorder(null);
        Play.setMaximumSize(new java.awt.Dimension(0, 0));
        Play.setMinimumSize(new java.awt.Dimension(0, 0));
        Play.setPreferredSize(new java.awt.Dimension(50, 25));
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });
        getContentPane().add(Play);
        Play.setBounds(119, 109, 149, 52);

        Exit.setBackground(new java.awt.Color(121, 28, 21));
        Exit.setText("Exit");
        Exit.setMaximumSize(new java.awt.Dimension(0, 0));
        Exit.setMinimumSize(new java.awt.Dimension(0, 0));
        Exit.setPreferredSize(new java.awt.Dimension(50, 25));
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        getContentPane().add(Exit);
        Exit.setBounds(120, 380, 149, 52);

        Highscore.setBackground(new java.awt.Color(121, 28, 21));
        Highscore.setText("HighScore");
        Highscore.setMaximumSize(new java.awt.Dimension(0, 0));
        Highscore.setMinimumSize(new java.awt.Dimension(0, 0));
        Highscore.setPreferredSize(new java.awt.Dimension(50, 25));
        Highscore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HighscoreActionPerformed(evt);
            }
        });
        getContentPane().add(Highscore);
        Highscore.setBounds(119, 200, 149, 52);

        AboutUs.setBackground(new java.awt.Color(121, 28, 21));
        AboutUs.setText("About Us");
        AboutUs.setMaximumSize(new java.awt.Dimension(0, 0));
        AboutUs.setMinimumSize(new java.awt.Dimension(0, 0));
        AboutUs.setPreferredSize(new java.awt.Dimension(50, 20));
        AboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutUsActionPerformed(evt);
            }
        });
        getContentPane().add(AboutUs);
        AboutUs.setBounds(119, 290, 149, 52);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Menu.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed

        this.setVisible(false);
        GameSetUp game = new GameSetUp("SkyForce Game  User Name  :- " + Uname, 500, 600);
        game.start();

    }//GEN-LAST:event_PlayActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void AboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutUsActionPerformed
        JOptionPane.showMessageDialog(AboutUs, "Developed By ALPHA Team \n Members : \n Tenusha Guruge \n Vimukthi Rajapaksha \n Aravinda Kulasooriya \n Ranmal Dewage ", "About Us", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AboutUsActionPerformed

    private void HighscoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HighscoreActionPerformed
        this.setVisible(false);
        Highscore highscore = new Highscore();
        highscore.setVisible(true);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/skyforce?", "root", "");

            ResultSet query = dbConn.createStatement().executeQuery("SELECT username, score FROM highscore ORDER BY 2 DESC");
                DefaultTableModel model = new DefaultTableModel(new Object[]{"User", "Highscore"}, 0);
                while (query.next()) {
                    String c1 = query.getString("username");
                    String c2 = query.getString("score");
                    Object[] row = {c1, c2};                   
                    model.addRow(row);                   
                }
                highscore.setModel(model);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_HighscoreActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AboutUs;
    private javax.swing.JButton Exit;
    private javax.swing.JButton Highscore;
    private javax.swing.JButton Play;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
