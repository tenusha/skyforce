package Game_Register;

import Game_SignIn.SignIn;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static Game_SignIn.SignIn.dbConn;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Register extends javax.swing.JFrame implements Game_Validate{

   
    public Register() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rUsername = new javax.swing.JLabel();
        un = new javax.swing.JTextField();
        rPassword = new javax.swing.JLabel();
        Register = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        pw = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 150));
        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        rUsername.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rUsername.setForeground(new java.awt.Color(255, 255, 255));
        rUsername.setText("Username");
        getContentPane().add(rUsername);
        rUsername.setBounds(40, 180, 128, 38);
        getContentPane().add(un);
        un.setBounds(190, 180, 150, 40);

        rPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rPassword.setForeground(new java.awt.Color(255, 255, 255));
        rPassword.setText("Password");
        getContentPane().add(rPassword);
        rPassword.setBounds(40, 240, 128, 38);

        Register.setBackground(new java.awt.Color(51, 255, 255));
        Register.setText("Register");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });
        getContentPane().add(Register);
        Register.setBounds(70, 340, 106, 40);

        Cancel.setBackground(new java.awt.Color(51, 255, 255));
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        getContentPane().add(Cancel);
        Cancel.setBounds(220, 340, 106, 40);
        getContentPane().add(pw);
        pw.setBounds(190, 250, 150, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Register.jpg"))); // NOI18N
        jLabel1.setName(""); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -10, 400, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.setVisible(false);
        new SignIn().setVisible(true);
        
    }//GEN-LAST:event_CancelActionPerformed

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        boolean user=false, pwd=false;
        
        user=validateUsername(un.getText());
        pwd=validatePassword(pw.getText());
        
        if(user==true && pwd==true){
            try {
                dbConn.prepareStatement("INSERT INTO user VALUES('" +un.getText()+ "','" +pw.getText()+ "',"+0+")").execute();
                dbConn.prepareStatement("INSERT INTO highscore VALUES('" +un.getText()+ "'," +0+ ")").execute();
                ResultSet query1 = dbConn.createStatement().executeQuery("SELECT `username` FROM user WHERE `username` = '" +un.getText()+ "'");

               if(query1.last()){
                   JOptionPane.showMessageDialog(null,"Registration Successfull !","Successfull",JOptionPane.INFORMATION_MESSAGE);
                   this.setVisible(false);
                   new SignIn().setVisible(true);
               }
            } catch (SQLException ex) {
                 Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }//GEN-LAST:event_RegisterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }
    public void dbconnect(){    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/skyforce?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("can't connect to db");
        }
    }
    @Override
    public boolean validateUsername(String username){
        dbconnect();
        boolean valid=false;
        try{
            ResultSet query = dbConn.createStatement().executeQuery("SELECT username FROM user WHERE username = '" +un.getText()+ "'");
            if(query.last()){//check username has taken or not
                valid = true;
            }
            if(valid==false){
                if(username.length()==0){ //check username is null 
                    JOptionPane.showMessageDialog(null,"Username Cannot be EMPTY !","Error",JOptionPane.OK_OPTION);
                    return false;
                }
                else if(username.length()>=10 ){ //check username length
                    JOptionPane.showMessageDialog(null,"Username must Contain less than 10 Characters !","Error",JOptionPane.OK_OPTION);
                    return false;
                }
                else{
                    return true;
                }
            }
            else{
                 JOptionPane.showMessageDialog(null,"This Username is Already Taken Try Another !","Error",JOptionPane.OK_OPTION);
                 return false;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public boolean validatePassword(String password){
        dbconnect();
        boolean plength=false;
        int pvalid=0, pvalid1=0;
        for(int a=0; a<password.length(); a++){ //check password has a number
                if(Character.isLetter(password.charAt(a))){
                   pvalid++;
                }

                if(Character.isDigit(password.charAt(a))){
                   pvalid1++;
               }
        }
        if(pvalid==0 || pvalid1==0){
            JOptionPane.showMessageDialog(null,"Password Should Contain Atleat One Letter and a Number !","Error",JOptionPane.OK_OPTION);
            return false;
        }
        if(password.length()>=5 && password.length()<=15){//check password length
            plength=true;
        }
        else{
            JOptionPane.showMessageDialog(null,"Password Should Contain Minimum 5 Characters and Maximum 15 Characters !","Error",JOptionPane.OK_OPTION);
            return false;
        }
        if(password.length()==0){
            JOptionPane.showMessageDialog(null,"Password Cannot be EMPTY !","Error",JOptionPane.OK_OPTION);
            return false;
        }
        
        if((pvalid>=1 && pvalid1>=1)&&(plength==true)){
            return true;
        }
        else{
            return false;
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField pw;
    private javax.swing.JLabel rPassword;
    private javax.swing.JLabel rUsername;
    private javax.swing.JTextField un;
    // End of variables declaration//GEN-END:variables
}
