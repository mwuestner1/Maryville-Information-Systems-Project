/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maryvilleproject;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author mattw
 */
public final class Login extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension dim = toolkit.getScreenSize();
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2); 
        conn = db.java_db();
        currentDate();
    }
    
    public void currentDate() {
        Calendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        
        lbl_date.setText((month + 1) + "/" + day + "/" + year);
        
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR);
        
        lbl_time.setText(hour+":"+minute+":"+second);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        txt_combo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        lbl_date = new javax.swing.JMenu();
        lbl_time = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(280, 250, 100, 22);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(280, 310, 100, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Department:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(280, 370, 110, 20);
        jPanel1.add(txt_username);
        txt_username.setBounds(430, 250, 205, 26);

        txt_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Sales", "Marketing", "Customer Service" }));
        jPanel1.add(txt_combo);
        txt_combo.setBounds(430, 370, 205, 26);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(560, 420, 71, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Please enter username, password, and department:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(170, 180, 590, 29);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("LOGIN");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 0, 910, 100);
        jPanel1.add(txt_password);
        txt_password.setBounds(430, 310, 200, 26);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maryvilleproject/images/background.jpg"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 0, 910, 580);

        lbl_date.setText("Date");
        jMenuBar1.add(lbl_date);

        lbl_time.setText("Time");
        jMenuBar1.add(lbl_time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String sql = "SELECT * FROM Users where username = ? and password = ? and department = ?";
        
        try {
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, txt_username.getText());
            pst.setString(2, String.valueOf(txt_password.getPassword()));
            pst.setString(3, txt_combo.getSelectedItem().toString());
            
            rs = pst.executeQuery();
            
            if(rs.next()) {
                int id = rs.getInt(1);
                Employee.EmpID = id;
                JOptionPane.showMessageDialog(null, "Success");
                MainMenu mainmenu = new MainMenu();
                mainmenu.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Username, Password, or Department are wrong");
            }
            
        } catch(HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu lbl_date;
    private javax.swing.JMenu lbl_time;
    private javax.swing.JComboBox<String> txt_combo;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
