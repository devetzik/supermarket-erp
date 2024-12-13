package gui;

import api.Administrator;
import api.Customer;
import api.User;
import api.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame {
    static JFrame frame;
    private static JPanel panel=new JPanel();
    private static JPanel signupPanel=new JPanel();
    private static JPanel loginPanel=new JPanel();
    private static JPanel eastPanel=new JPanel();
    private static JPanel westPanel=new JPanel();
    private static final JLabel welcome=new JLabel("Καλωσήρθατε στο e-shop του Supermarket",SwingConstants.CENTER);
    private static final JLabel loginLabel=new JLabel("\nΕίσοδος",SwingConstants.CENTER);
    private static final JLabel usernameLabel=new JLabel("Username");
    private static final JLabel passwordLabel=new JLabel("Password ");
    private static JTextField usernameTextfield=new JTextField();
    private static JPasswordField passwordField=new JPasswordField();
    private static JButton loginButton=new JButton("Είσοδος");
    private static JButton signupButton=new JButton("Εγγραφή");
    private static JLabel signupLabel=new JLabel("Δεν έχετε λογαριασμό;");
    static Utilities util = new Utilities();
    static User currnentUser;
    public LoginFrame(){
        frame=new JFrame();
        frame.setTitle("Supermarket e-shop");
        frame.setSize(900,600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.orange);
        frame.setLocationRelativeTo(null);

        welcome.setFont(new Font("Serif",Font.BOLD,26));
        welcome.setPreferredSize(new Dimension(800,100));

        loginLabel.setPreferredSize(new Dimension(300,100));
        loginLabel.setFont(new Font("Serif",Font.BOLD,20));

        loginPanel.setPreferredSize(new Dimension(300,300));

        usernameLabel.setFont(new Font("Serif",Font.BOLD,16));
        usernameTextfield.setPreferredSize(new Dimension(190,25));
        passwordLabel.setFont(new Font("Serif",Font.BOLD,16));
        passwordField.setPreferredSize(new Dimension(190,25));

        loginButton.setPreferredSize(new Dimension(100,30));

        loginPanel.add(loginLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextfield);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.setBackground(Color.orange);

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a;
                String username = usernameTextfield.getText();
                String password = passwordField.getText();

                try {
                    a = util.loginCheck(username, password);
                    if (a==0){
                        usernameTextfield.setText("");
                        passwordField.setText("");
                        currnentUser= util.getCurrentUser(username);
                        if (currnentUser instanceof Administrator){
                            new AdminFrame((Administrator) currnentUser);
                        }else
                            new CustomerFrame((Customer) currnentUser);
                        frame.setVisible(false);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                new LoginResultDialog(a);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a;
                String username = usernameTextfield.getText();
                String password = passwordField.getText();

                try {
                    a = util.loginCheck(username, password);
                    if (a==0){
                        usernameTextfield.setText("");
                        passwordField.setText("");
                        currnentUser= util.getCurrentUser(username);
                        if (currnentUser instanceof Administrator){
                            new AdminFrame((Administrator) currnentUser);
                        }else
                            new CustomerFrame((Customer) currnentUser);
                        frame.setVisible(false);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                new LoginResultDialog(a);
            }
        });


        panel.setPreferredSize(new Dimension(300,700));
        panel.setBackground(Color.orange);


        signupLabel.setFont(new Font("Serif",Font.BOLD,20));
        signupButton.setPreferredSize(new Dimension(100,30));
        signupPanel.setPreferredSize(new Dimension(200,100));
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

        eastPanel.setPreferredSize(new Dimension(300,600));
        eastPanel.setBackground(Color.orange);
        westPanel.setPreferredSize(new Dimension(300,600));
        westPanel.setBackground(Color.orange);

        frame.add(welcome,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.add(eastPanel,BorderLayout.EAST);
        frame.add(westPanel,BorderLayout.WEST);

        frame.setVisible(true);
    }
}
