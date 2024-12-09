package gui;

import api.Administrator;
import api.User;
import api.Utilities;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class login implements ActionListener {

    Utilities util=new Utilities();
    public login(){};
    private int a;
    private JLabel usernameLabel,passwordLabel,welcomeLabel, loginLabel, signupLabel, fNameLabel,lNameLabel, unLabel, pwLabel;
    private static JTextField usernameText, passwordText, unText, fNText, lNText;
    private JPasswordField  pwText;
    private JButton button, signupButton;
    private static JLabel result;




    JFrame frame = new JFrame();
    JPanel panel= new JPanel(null);
    JPanel signupPanel= new JPanel(null);

    JFrame resultWindow=new JFrame();
    JPanel resultPanel=new JPanel(null);

    public void log(){
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Supermarket e-shop");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(panel);
        frame.add(signupPanel);
        frame.getContentPane().setBackground(Color.lightGray);







        panel.setBounds(100,200,500,400);
        panel.setBackground(Color.cyan);

        signupPanel.setBounds(680,200,500,400);
        signupPanel.setBackground(Color.red);



        welcomeLabel=new JLabel("Καλωσήρθατε στο eshop του Supermarket");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30 ));
        welcomeLabel.setBounds(360,30,600,90);
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
        passwordText.addActionListener(new login());

        button=new JButton("Login");
        button.setBounds(185,260,100,25);
        button.addActionListener(new login());
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

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=unText.getText();
                String password= pwText.getText();
                String fName=fNText.getText();
                String lName=lNText.getText();

                JButton ok=new JButton("OK");
                result=new JLabel();
                result.setFont(new Font("Times New Roman", Font.BOLD,18));
                result.setBounds(10,10,480,40);
                resultPanel.add(result);

                try {
                    a=util.addCustomer(username,password,fName,lName);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                if (a==0){
                    result.setText("Επιτυχής εγγραφή");
                } else if (a==1) {
                    result.setText("Το username χρησιμοποιείται");
                } else if (a==3) {
                    result.setText("Συμπληρώστε τα κενά πεδία");
                } else if (a==4) {
                    result.setText("Το όνομα και το επίθετο δεν μπορούν να περιέχουν αριθμούς");
                }

                resultWindow.setSize(500,200);
                resultWindow.setLocationRelativeTo(null);
                resultWindow.setLayout(null);
                resultWindow.add(resultPanel);

                resultPanel.setBounds(10,10,490,195);


                ok.setBounds(200,90,100,40);
                resultPanel.add(ok);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resultWindow.setVisible(false);
                        result.setText("");
                    }
                });

                resultWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                resultWindow.setVisible(true);
            }
        });

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username=usernameText.getText();
        String password=passwordText.getText();
        JButton ok=new JButton("OK");
        result=new JLabel();
        result.setFont(new Font("Times New Roman", Font.BOLD,18));
        result.setBounds(10,10,480,40);
        resultPanel.add(result);

        try {
            a=util.loginCheck(username,password);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        if (a==0){
            result.setText("Καλωσήρθατε "+username);
        } else if (a==1) {
            result.setText("Λάθος password, προσπαθήστε ξανά");
        } else if (a==2) {
            result.setText("Δεν βρέθηκε χρήστης με αυτό το username");
        } else if (a==3) {
            result.setText("Συμπληρώστε τα κενά πεδία");
        }

        resultWindow.setSize(500,200);
        resultWindow.setLocationRelativeTo(null);
        resultWindow.setLayout(null);
        resultWindow.add(resultPanel);

        resultPanel.setBounds(10,10,490,195);


        ok.setBounds(200,90,100,40);
        resultPanel.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultWindow.setVisible(false);
                result.setText("");
            }
        });

        resultWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        resultWindow.setVisible(true);
    }
}



