package client.gui;

import communication.ClientServerCommunication;
import primitives.CreditCard;
import primitives.Token;
import server.Session;

import javax.swing.*;

public class InSessionFrame extends javax.swing.JFrame {

    private Session session;
    private ClientServerCommunication communication;

    private javax.swing.JButton btnExportCreditCards;
    private javax.swing.JButton btnExportTokens;
    private javax.swing.JButton btnGetCard;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRegCard;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblWelcome;

    public InSessionFrame() {
        createComponents();
    }


    public InSessionFrame(Session session, ClientServerCommunication commChannel) {
        this();
        this.session = session;
        this.communication = commChannel;
        lblUser.setText(session.getUser().getUsername());
    }

    @SuppressWarnings("unchecked")
    private void createComponents() {
        initializeButtons();
        initializeLabels();

        GroupLayout layout = createLayout();
        getContentPane().setLayout(layout);

        setHorizontalBounds(layout);
        setVerticalBounds(layout);

        pack();
    }

    private void setVerticalBounds(GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRegCard)
                                .addGap(8, 8, 8)
                                .addComponent(btnExportTokens)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExportCreditCards)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGetCard)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(btnLogout)
                                .addContainerGap())
        );
    }

    private void setHorizontalBounds(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(btnExportCreditCards)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(63, 63, 63)
                                                .addComponent(btnLogout)
                                                .addGap(0, 51, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnGetCard, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(22, 22, 22)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(btnRegCard)
                                                                        .addComponent(btnExportTokens))
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addContainerGap())
        );
    }

    private GroupLayout createLayout() {
        return new GroupLayout(getContentPane());
    }

    private void initializeButtons() {
        createRegCardBtn();
        createExportTokensBtn();
        createExportCardsBtn();
        createGetCardNumberBtn();
        createLogOutBtn();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    private void createRegCardBtn() {
        btnRegCard = new JButton();
        btnRegCard.setText("Register Card");
        btnRegCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegCardActionPerformed(evt);
            }
        });
    }

    private void createExportTokensBtn() {
        btnExportTokens = new JButton();
        btnExportTokens.setText("Export Tokens");
        btnExportTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportTokensActionPerformed(evt);
            }
        });
    }

    private void createExportCardsBtn() {
        btnExportCreditCards = new JButton();
        btnExportCreditCards.setText("Export Credit Cards");
        btnExportCreditCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportCreditCardsActionPerformed(evt);
            }
        });
    }

    private void createGetCardNumberBtn() {
        btnGetCard = new JButton();
        btnGetCard.setText("Get credit card number");
        btnGetCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetCardActionPerformed(evt);
            }
        });
    }

    private void createLogOutBtn() {
        btnLogout = new JButton();
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
    }

    private void initializeLabels() {
        lblWelcome = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();

        lblWelcome.setFont(new java.awt.Font("Tahoma", 2, 18));
        lblUser.setFont(new java.awt.Font("Tempus Sans ITC", 2, 18));

        lblWelcome.setText("Welcome,");
        lblUser.setText("user");
    }

    private void btnRegCardActionPerformed(java.awt.event.ActionEvent evt) {
        CardRegFrame regFrame = new CardRegFrame(session, communication);
        regFrame.setVisible(true);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {

        SignFrame sf = new SignFrame();
        dispose();
        communication.sendMessage(null, "Close");
        sf.setVisible(true);

    }

    private void btnExportTokensActionPerformed(java.awt.event.ActionEvent evt) {
        communication.sendMessage(session, "Export Tokens");
        String ack = (String) communication.receiveObject();
        communication.handleAck(ack);
    }

    private void btnExportCreditCardsActionPerformed(java.awt.event.ActionEvent evt) {
        communication.sendMessage(session, "Export Credit Card");
        String ack = (String) communication.receiveObject();
        communication.handleAck(ack);
    }

    private void btnGetCardActionPerformed(java.awt.event.ActionEvent evt) {
        //boolean success;
        String token = JOptionPane.showInputDialog(null, "Enter the token: ");
        communication.sendMessage(new Token(token), "Get card");
        String ack = (String) communication.receiveObject();

        if (communication.handleAck(ack)) {
            CreditCard card = (CreditCard) communication.receiveObject();
            JOptionPane.showMessageDialog(null, "The card is: " + card);
        }
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
            java.util.logging.Logger.getLogger(InSessionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InSessionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InSessionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InSessionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InSessionFrame().setVisible(true);
            }
        });
    }
}
