package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class login{

    public login(){};
    private JLabel usernameLabel,passwordLabel,welcomeLabel, loginLabel, signupLabel, fNameLabel,lNameLabel, unLabel, pwLabel;
    private JTextField usernameText, unText, fNText, lNText;
    private JPasswordField passwordText, pwText;
    private JButton button, signupButton;
    private JLabel success;




    JFrame frame = new JFrame();
    JPanel panel= new JPanel(null);
    JPanel signupPanel= new JPanel(null);

    public void log(){
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Supermarket e-shop");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(panel);
        frame.add(signupPanel);
        //frame.setResizable(false);
        //frame.setBackground(Color.cyan);



        panel.setBounds(100,200,500,400);
        panel.setBackground(Color.cyan);

        signupPanel.setBounds(680,200,500,400);
        signupPanel.setBackground(Color.red);
        //panel.setSize(300,350);
        //panel.setPreferredSize(new Dimension(300,350));
        //panel.setVisible(true);


        welcomeLabel=new JLabel("Καλωσήρθατε στο eshop του Supermarket");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 26 ));
        welcomeLabel.setBounds(360,30,600,90);
        //welcomeLabel.setBackground(Color.orange);
        //welcomeLabel.setVisible(true);
        frame.add(welcomeLabel);

        loginLabel=new JLabel("Είσοδος");
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD,24));
        loginLabel.setBounds(200,90,90,35);
        panel.add(loginLabel);

        usernameLabel=new JLabel("Username");
        usernameLabel.setBounds(70,160,70,20);
        panel.add(usernameLabel);
        usernameText=new JTextField();
        usernameText.setBounds(160,160,165,20);
        panel.add(usernameText);

        passwordLabel =new JLabel("Password");
        passwordLabel.setBounds(70,205,70,20);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(160,205,165,20);
        panel.add(passwordText);

        button=new JButton("Login");
        button.setBounds(185,260,100,25);
        panel.add(button);


        signupLabel=new JLabel("Εγγραφή");
        signupLabel.setFont(new Font("Times New Roman", Font.BOLD,24));
        signupLabel.setBounds(200,30,100,45);
        signupPanel.add(signupLabel);

        fNameLabel=new JLabel("Όνομα");
        fNameLabel.setFont(new Font("Times New Roman", Font.BOLD,18));
        fNameLabel.setBounds(70,90,70,20);
        signupPanel.add(fNameLabel);

        fNText=new JTextField();
        fNText.setBounds(170,90,165,20);
        signupPanel.add(fNText);

        lNameLabel=new JLabel("Επώνυμο");
        lNameLabel.setFont(new Font("Times New Roman", Font.BOLD,18));
        lNameLabel.setBounds(70,140,80,20);
        signupPanel.add(lNameLabel);

        lNText=new JTextField();
        lNText.setBounds(170,140,165,20);
        signupPanel.add(lNText);

        unLabel=new JLabel("Username");
        unLabel.setFont(new Font("Times New Roman", Font.BOLD,18));
        unLabel.setBounds(70,190,80,20);
        signupPanel.add(unLabel);

        unText=new JTextField();
        unText.setBounds(170,190,165,20);
        signupPanel.add(unText);

        pwLabel=new JLabel("Password");
        pwLabel.setFont(new Font("Times New Roman", Font.BOLD,18));
        pwLabel.setBounds(70,240,80,20);
        signupPanel.add(pwLabel);

        pwText=new JPasswordField();
        pwText.setBounds(170,240,165,20);
        signupPanel.add(pwText);

        signupButton=new JButton("Εγγραφή");
        signupButton.setBounds(185,300,100,25);
        signupPanel.add(signupButton);

        frame.setVisible(true);

    }

}



