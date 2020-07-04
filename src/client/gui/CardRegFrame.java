package client.gui;

import communication.ClientServerCommunication;
import exceptions.InvalidCreditCardNumber;
import exceptions.InvalidFirstNumber;

import javax.swing.*;

import primitives.CreditCard;
import primitives.Token;
import server.Session;

public class CardRegFrame extends javax.swing.JFrame {

    private CreditCard cardId = null;
    private Session session;
    private ClientServerCommunication communication;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRegister;
    private javax.swing.JTextField txtCardId;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form CardRegFrame
     */
    public CardRegFrame() {
        createComponents();
        setLocationRelativeTo(null);
    }

    public CardRegFrame(Session session, ClientServerCommunication communication) {
        this();
        this.session = session;
        this.communication = communication;
    }


    @SuppressWarnings("unchecked")
    private void createComponents() {
        initializeComponents();

        GroupLayout layout = createLayout();
        getContentPane().setLayout(layout);

        setHorizontalBounds(layout);
        setVerticalBounds(layout);

        pack();
    }
    
    private void initializeComponents(){
        btnRegister = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtCardId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
    }

    private GroupLayout createLayout(){
        return new GroupLayout(getContentPane());
    }

    private void setHorizontalBounds(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnRegister)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCancel))
                                        .addComponent(txtCardId, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
        );
    }

    private void setVerticalBounds(GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtCardId, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnRegister)
                                        .addComponent(btnCancel))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {

        String cardIdString = txtCardId.getText();
        boolean success = false;
        try {
            cardId = new CreditCard(cardIdString);
            success = true;
        } catch (InvalidFirstNumber ex) {
            JOptionPane.showMessageDialog(null, "Invalid credit card number!\n"
                    + "The first number must start with 3,4,5 or 6",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        } catch (InvalidCreditCardNumber ex) {
            JOptionPane.showMessageDialog(null, "Invalid credit card number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (success) {
            communication.sendMessage(cardId, "Register Card");

            Token token = (Token) communication.recieveObj();
            JOptionPane.showMessageDialog(null, "Card registred, token is: " + token,
                    "Success!", JOptionPane.INFORMATION_MESSAGE);

            dispose();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CardRegFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardRegFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardRegFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardRegFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CardRegFrame().setVisible(true);
            }
        });
    }
}
