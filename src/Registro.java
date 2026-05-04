


public class Registro extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Registro.class.getName());
    
    public Registro() {
        initComponents();
        setLocationRelativeTo(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNuevaPassword = new javax.swing.JTextField();
        txtNuevoUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText(" contraseña");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 337, -1, 27));

        txtNuevaPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevaPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(txtNuevaPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 365, 170, 30));

        txtNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txtNuevoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 299, 170, 30));

        btnLogin.setBackground(new java.awt.Color(0, 255, 153));
        btnLogin.setText("Registrar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 603, -1, 40));

        jLabel3.setText("nuevo usuario");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 275, 80, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNuevaPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevaPasswordActionPerformed
        // TODO add your handling code her
    }//GEN-LAST:event_txtNuevaPasswordActionPerformed

    private void txtNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevoUsuarioActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
    String user = txtNuevoUsuario.getText();
    String pass = txtNuevaPassword.getText();

    if (user.isEmpty() || pass.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "No se permiten campos vacíos");
        return;
    }
    if (!user.matches("[a-zA-Z0-9_]+")) {
        javax.swing.JOptionPane.showMessageDialog(this, "El usuario solo puede tener letras, números y _");
        return;
    }
    if (pass.length() < 6) {
        javax.swing.JOptionPane.showMessageDialog(this, "La contraseña debe tener mínimo 6 caracteres");
        return;
    }

    Login.usuarios.add(user);
    Login.contraseñas.add(pass);

    javax.swing.JOptionPane.showMessageDialog(this, "Usuario registrado");
    new Login().setVisible(true);
    this.dispose(); 
    }//GEN-LAST:event_btnLoginActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Registro().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNuevaPassword;
    private javax.swing.JTextField txtNuevoUsuario;
    // End of variables declaration//GEN-END:variables
}
