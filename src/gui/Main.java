package gui;

import api.*;
import gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static gui.CustomerFrame.custFrame;

/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */
public class Main implements Serializable,ActionListener {
    public static JFrame frame=new JFrame();
    private static JPanel panel = new JPanel();
    private static JPanel signupPanel = new JPanel();
    private static JPanel loginPanel = new JPanel();
    private static JPanel eastPanel = new JPanel();
    private static JPanel westPanel = new JPanel();
    private static final JLabel welcome = new JLabel("Καλωσήρθατε στο e-shop του Supermarket", SwingConstants.CENTER);
    private static final JLabel loginLabel = new JLabel("\nΕίσοδος", SwingConstants.CENTER);
    private static final JLabel usernameLabel = new JLabel("Username");
    private static final JLabel passwordLabel = new JLabel("Password ");
    private static JTextField usernameTextfield = new JTextField();
    private static JPasswordField passwordField = new JPasswordField();
    private static JButton loginButton = new JButton("Είσοδος");
    private static JButton signupButton = new JButton("Εγγραφή");
    private static JLabel signupLabel = new JLabel("Δεν έχετε λογαριασμό;");
    private static Utilities util = new Utilities();
    private static User currnentUser;

    public static void main (String[] args) throws IOException, ClassNotFoundException {
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

        try {
            a = util.loginCheck(username, password);
            if (a == 0) {
                frame.dispose();
                usernameTextfield.setText("");
                passwordField.setText("");
                currnentUser = util.getCurrentUser(username);
                if (currnentUser instanceof Administrator) {
                    new AdminFrame((Administrator) currnentUser);
                } else {
                    new CustomerFrame((Customer) currnentUser);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        new LoginResultDialog(a);
    }
}










        /*
        orders=util.orderHistoryLoader();
        for (Order i: orders){
            for (int j=0;j<i.getPr().length;j++){
                System.out.println(i.getPr()[j][0]);
            }
        }

         */




        /*
        Scanner scanner = new Scanner(System.in);
        int x;

            //USER IS ADMIN

        while (currnentUser instanceof Administrator) {
            Administrator admin = (Administrator) currnentUser;
            System.out.println("Επιλέξτε λειτουργία:\nΚαταχώρηση νέου προϊόντος (1)\nΑναζήτηση προϊόντος (2)\nΣτατιστικά προϊόντων (3)\nΑποσύνδεση χρήστη(4)");
            x = scanner.nextInt();
            if (x == 1) {
                admin.addProduct();
            } else if (x == 2) {
                admin.productSearch(admin);
            } else if (x == 3) {
                admin.adminStats();
            } else if (x == 4) {
                currnentUser = null;
            }
        }


            //USER IS CUSTOMER

        while (currnentUser instanceof Customer) {
            Customer customer = (Customer) currnentUser;
            System.out.println("Επιλέξτε λειτουργία:\nΠροβολή Ιστορικού Παραγγελιών (1)\nΑναζήτηση προϊόντος (2)\nΠροβολή καλαθιού (3)\nΑποσύνδεση χρήστη(4)");
            x = scanner.nextInt();
            if (x == 1) {
                customer.viewOrderHistory(customer);
            } else if (x == 2) {
                customer.productSearch(customer);
            } else if (x == 3) {
                customer.viewShoppingCart(customer);
            } else if (x == 4) {
                currnentUser = null;
            }
        }
    }

         */


        /*

        LOADER TESTS
        for (int i=0; i< admins.size(); i++){
            System.out.println(admins.get(i).getUsername()+"\n"+admins.get(i).getPassword());
        }
        for (int i=0; i<customers.size();i++) {
            System.out.println(customers.get(i).getUsername() + "\n" + customers.get(i).getPassword());
        }
        for (api.Product i : products){
            System.out.println(i.getDetails()+"\n");
        }

        for (int i=0;i<cat.length;i++) {
            for (int j = 0; j < cat[i].length; j++) {
                if (cat[i][j] != null) {
                    if (j == 0) {
                        System.out.println("\n\n\n");
                    }
                    System.out.println(cat[i][j]);
                    if (j == 0) {
                        System.out.println("\n");
                    }
                }
            }
        }
         */
