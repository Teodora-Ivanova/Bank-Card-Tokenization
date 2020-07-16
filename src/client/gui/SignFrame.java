package client.gui;

import communication.ClientServerCommunication;
import exceptions.IncorrectUserPassword;
import exceptions.InvalidUsername;
import exceptions.SignUpDenied;
import primitives.User;
import server.Session;

import javax.swing.*;

public class SignFrame extends javax.swing.JFrame {
    private Session session;
    private ClientServerCommunication commChannel = new ClientServerCommunication();
    private javax.swing.JButton btnSignIn;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel panel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;

    public SignFrame() {
        createComponents();
    }

    @SuppressWarnings("unchecked")
    private void createComponents() {
        initializeLabels();
        createTxtFields();
        initializeButtons();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel = new javax.swing.JPanel();
        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        setPanelHorizontalBounds(panelLayout);
        setPanelVerticalBounds(panelLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        setHorizontalBounds(layout);
        setVerticalBounds(layout);

        pack();
    }

    private void initializeLabels() {
        jLabelName = new javax.swing.JLabel();
        jLabelName.setText("Username:");

        jLabelPass = new javax.swing.JLabel();
        jLabelPass.setText("Password:");
    }

    private void createTxtFields() {
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
    }


    private void initializeButtons() {
        btnSignIn = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        btnSignIn.setText("Sign in");
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnSignInActionPerformed(evt);
                } catch (SignUpDenied | InvalidUsername | IncorrectUserPassword signUpDenied) {
                    signUpDenied.printStackTrace();
                }
            }
        });

        btnSignUp.setText("Sign Up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnSignUpActionPerformed(evt);
                } catch (SignUpDenied | InvalidUsername signUpDenied) {
                    signUpDenied.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setVerticalBounds(GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(12, Short.MAX_VALUE)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    private void setHorizontalBounds(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(49, Short.MAX_VALUE))
        );
    }

    private void setPanelVerticalBounds(GroupLayout panelLayout) {
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelName)
                                        .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPass)
                                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSignIn)
                                        .addComponent(btnSignUp))
                                .addContainerGap(34, Short.MAX_VALUE))
        );
    }

    private void setPanelHorizontalBounds(GroupLayout panelLayout) {
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(btnSignIn)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnSignUp))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelName)
                                                        .addComponent(jLabelPass))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtPassword)
                                                        .addComponent(txtUsername))))
                                .addContainerGap())
        );
    }


    private void btnSignInActionPerformed(java.awt.event.ActionEvent evt) throws SignUpDenied, InvalidUsername, IncorrectUserPassword {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        boolean isSuccessful;
        commChannel.sendMessage(new User(username, password), "signIn");
        isSuccessful = commChannel.handleAck((String) commChannel.receiveObject(), username);

        if (isSuccessful) {
            dispose();
            session = (Session) commChannel.receiveObject();
            InSessionFrame inSession = new InSessionFrame(session, commChannel);
            inSession.setVisible(true);
        }
    }

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if(txtUsername.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Empty username", "Error sign up",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if( new String(txtPassword.getPassword()).isEmpty()){
            JOptionPane.showMessageDialog(null,"Empty password", "Error sign up",JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        User newUser = new User(name, password);
        commChannel.sendMessage(newUser, "signUp");
        commChannel.handleAck((String) commChannel.receiveObject(), null);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignFrame().setVisible(true);
            }
        });
    }


}