package GUI;
import Database.database;
import Entitas.pengguna;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Silva_Jen_Retno
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    
    private database Database;

    public Login (database Database) {
        this.Database = Database;
        
    }
    public Login() throws ClassNotFoundException, SQLException{
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kolomuserid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        kolompass = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 204, 255));
        jLabel1.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("KitchenScheduler");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 30));

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Sign in to KitchenScheduler");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, 20));

        kolomuserid.setBackground(new java.awt.Color(255, 255, 255));
        kolomuserid.setFont(new java.awt.Font("Swis721 BT", 0, 12)); // NOI18N
        kolomuserid.setForeground(new java.awt.Color(0, 0, 0));
        kolomuserid.setCaretColor(new java.awt.Color(255, 255, 255));
        kolomuserid.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        kolomuserid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kolomuseridActionPerformed(evt);
            }
        });
        getContentPane().add(kolomuserid, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 195, 190, 30));

        jButton1.setBackground(new java.awt.Color(232, 183, 217));
        jButton1.setFont(new java.awt.Font("Schadow BT", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Log in");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 366, 160, 40));

        kolompass.setBackground(new java.awt.Color(255, 255, 255));
        kolompass.setFont(new java.awt.Font("Swis721 BT", 0, 12)); // NOI18N
        kolompass.setForeground(new java.awt.Color(0, 0, 0));
        kolompass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kolompassActionPerformed(evt);
            }
        });
        getContentPane().add(kolompass, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 190, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/login user.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kolomuseridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kolomuseridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kolomuseridActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String ID_User = kolomuserid.getText();
        String Password = kolompass.getText();
        
        database db = new database();
        
        try {
            db.openConnection();
            Connection connection = db.connection;
            String sql = "SELECT * FROM pengguna WHERE ID_User = ? AND Password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ID_User);
            preparedStatement.setString(2, Password);

//            ResultSet resultSet = preparedStatement.executeQuery();
            java.sql.ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                pengguna pengguna =new pengguna (
                       rs.getString("ID_User"),
                        rs.getString("Password")
                );
                JOptionPane.showMessageDialog(null, "Login Berhasil");
                this.dispose();
                try {
                    new MenuUtama(pengguna).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Login Gagal");
                try {
                new Login().setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
            }

        } catch (SQLException ex) {
            database.displayErrors(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kolompassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kolompassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kolompassActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField kolompass;
    private javax.swing.JTextField kolomuserid;
    // End of variables declaration//GEN-END:variables
}