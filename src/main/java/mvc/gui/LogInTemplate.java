package mvc.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class LogInTemplate extends JFrame {

    private final JButton loginButton = new JButton("Login");
    private final JButton resetButton = new JButton("Reset");
    private final JTextField userIDField = new JTextField();
    private final JPasswordField userPasswordField = new JPasswordField();
    private final JLabel messageLabel = new JLabel();

    public LogInTemplate() {

        JLabel userIDLabel = new JLabel("UserID:");
        userIDLabel.setBounds(50, 100, 75, 25);

        JLabel userPasswordLabel = new JLabel("Password:");
        userPasswordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(125, 250, 250, 25);
        messageLabel.setFont(new Font(null, Font.ITALIC, 20));

        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);

        this.add(userIDLabel);
        this.add(userPasswordLabel);
        this.add(messageLabel);
        this.add(userIDField);
        this.add(userPasswordField);
        this.add(loginButton);
        this.add(resetButton);

        this.setTitle("Log In");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(420, 420);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public String getUserName() {
        return userIDField.getText();
    }

    public String getUserPassword() {
        return String.valueOf(userPasswordField.getPassword());
    }

    public void reset(String reset){
        userIDField.setText(reset);
        userPasswordField.setText(reset);
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JPasswordField getUserPasswordField() {
        return userPasswordField;
    }

    public void addLogInActionListener(ActionListener listenForButtons){
        loginButton.addActionListener(listenForButtons);
        resetButton.addActionListener(listenForButtons);
    }

    public void addLogInKeyListener(KeyListener listenForKeys){
        userIDField.addKeyListener(listenForKeys);
        userPasswordField.addKeyListener(listenForKeys);
    }
}
