package mvc.controller;

import mvc.model.UsersModel;
import mvc.view.LogInTemplate;
import mvc.view.WelcomeTemplate;
import org.jetbrains.annotations.NotNull;
import org.utils.PasswordUtils;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LogInController {

    private static UsersModel modelLogIn;
    private static LogInTemplate viewLogIn;

    public LogInController(UsersModel model, LogInTemplate view) {
        modelLogIn = model;
        viewLogIn = view;

        viewLogIn.addLogInListener(new LoginListener());
    }

    private static class LoginListener implements ActionListener{
        JButton loginButton = viewLogIn.getLoginButton();
        JButton resetButton = viewLogIn.getResetButton();
        JLabel messageLabel = viewLogIn.getMessageLabel();

        @Override
        public void actionPerformed(@NotNull ActionEvent e) {

            if(e.getSource() == resetButton){
                viewLogIn.reset("");
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
                            SwingUtilities.invokeLater(WelcomeTemplate::new);
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
}
