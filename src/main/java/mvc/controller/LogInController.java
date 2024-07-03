package mvc.controller;

import mvc.gui.admin.AdminMainTemplate;
import mvc.gui.admin.UserCardDetails;
import mvc.model.UsersModel;
import mvc.gui.LogInTemplate;
import org.jetbrains.annotations.NotNull;
import org.utils.validations.PasswordUtils;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LogInController {

    private static UsersModel modelLogIn;
    private static LogInTemplate viewLogIn;

    public LogInController(UsersModel model, LogInTemplate view) {
        modelLogIn = model;
        viewLogIn = view;

        viewLogIn.addLogInActionListener(new LoginButtonsListener());
        viewLogIn.addLogInKeyListener(new LogInEnterKey());
    }

    private static class LoginButtonsListener implements ActionListener {
        JButton loginButton = viewLogIn.getLoginButton();
        JButton resetButton = viewLogIn.getResetButton();
        JLabel messageLabel = viewLogIn.getMessageLabel();

        @Override
        public void actionPerformed(@NotNull ActionEvent e) {

            if(e.getSource() == resetButton){
                viewLogIn.reset("");
                messageLabel.setText("");
            }

            if(e.getSource() == loginButton){
                String userID = viewLogIn.getUserName();
                String userPASS = viewLogIn.getUserPassword();
                String dbPASS = modelLogIn.authenticate(userID);

                if(!dbPASS.equals("not found")){
                    try {
                        if(PasswordUtils.verifyPassword(userPASS, dbPASS)){
                            messageLabel.setForeground(Color.green);
                            messageLabel.setText("Login Successful");
                            viewLogIn.dispose();
                            SwingUtilities.invokeLater(AdminMainTemplate::new);
                        }else {
                            messageLabel.setForeground(Color.red);
                            messageLabel.setText("Wrong Password");
                        }
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Username not found");
                }
            }
        }
    }

    private static class LogInEnterKey implements KeyListener{
        JLabel messageLabel = viewLogIn.getMessageLabel();

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(@NotNull KeyEvent e) {
            if(e.getKeyChar() == KeyEvent.VK_ENTER){
                String userID = viewLogIn.getUserName();
                String userPASS = viewLogIn.getUserPassword();
                String dbPASS = modelLogIn.authenticate(userID);

                if(!dbPASS.equals("not found")){
                    try {
                        if(PasswordUtils.verifyPassword(userPASS, dbPASS)){
                            messageLabel.setForeground(Color.green);
                            messageLabel.setText("Login Successful");
                            viewLogIn.dispose();
                            SwingUtilities.invokeLater(AdminMainTemplate::new);
                        }else {
                            messageLabel.setForeground(Color.red);
                            messageLabel.setText("Wrong Password");
                        }
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Username not found");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}