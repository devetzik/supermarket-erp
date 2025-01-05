package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */
public class Main implements Serializable,ActionListener {
    public static JFrame frame=new JFrame();
    private static final JPanel panel = new JPanel();
    private static final JPanel signupPanel = new JPanel();
    private static final JPanel loginPanel = new JPanel();
    private static final JPanel eastPanel = new JPanel();
    private static final JPanel westPanel = new JPanel();
    private static final JLabel welcome = new JLabel("Καλωσήρθατε στο e-shop του Supermarket", SwingConstants.CENTER);
    private static final JLabel loginLabel = new JLabel("\nΕίσοδος", SwingConstants.CENTER);
    private static final JLabel usernameLabel = new JLabel("Username");
    private static final JLabel passwordLabel = new JLabel("Password ");
    private static final JTextField usernameTextfield = new JTextField();
    private static final JPasswordField passwordField = new JPasswordField();
    private static final JButton loginButton = new JButton("Είσοδος");
    private static final JButton signupButton = new JButton("Εγγραφή");
    private static final JLabel signupLabel = new JLabel("Δεν έχετε λογαριασμό;");

    public static void main (String[] args){
        frame.setTitle("Supermarket e-shop");
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.orange);
        frame.setLocationRelativeTo(null);

        welcome.setFont(new Font("Serif", Font.BOLD, 26));
        welcome.setPreferredSize(new Dimension(800, 100));

        loginLabel.setPreferredSize(new Dimension(300, 100));
        loginLabel.setFont(new Font("Serif", Font.BOLD, 20));

        loginPanel.setPreferredSize(new Dimension(300, 300));

        usernameLabel.setFont(new Font("Serif", Font.BOLD, 16));
        usernameTextfield.setPreferredSize(new Dimension(190, 25));
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 16));
        passwordField.setPreferredSize(new Dimension(190, 25));

        loginButton.setPreferredSize(new Dimension(100, 30));

        loginPanel.add(loginLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextfield);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.setBackground(Color.orange);

        passwordField.addActionListener(new Main());

        loginButton.addActionListener(new Main());

        panel.setPreferredSize(new Dimension(300, 700));
        panel.setBackground(Color.orange);

        signupLabel.setFont(new Font("Serif", Font.BOLD, 20));
        signupButton.setPreferredSize(new Dimension(100, 30));
        signupPanel.setPreferredSize(new Dimension(200, 100));
        signupPanel.add(signupLabel);
        signupPanel.add(signupButton);
        signupPanel.setBackground(Color.orange);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpDialog();
            }
        });

        panel.add(loginPanel);
        panel.add(signupPanel);

        eastPanel.setPreferredSize(new Dimension(300, 600));
        eastPanel.setBackground(Color.orange);
        westPanel.setPreferredSize(new Dimension(300, 600));
        westPanel.setBackground(Color.orange);

        frame.add(welcome, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(westPanel, BorderLayout.WEST);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int a;
        String username = usernameTextfield.getText();
        String password = passwordField.getText();

        a = Utilities.loginCheck(username, password);
        if (a == 0) {
            frame.dispose();
            usernameTextfield.setText("");
            passwordField.setText("");
            User currnentUser = Utilities.getCurrentUser(username);
            if (currnentUser instanceof Administrator) {
                new AdminFrame((Administrator) currnentUser);
            } else {
                new CustomerFrame((Customer) currnentUser);
            }
        }
        new LoginResultDialog(a);
    }
}