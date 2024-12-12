package gui;

import api.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class SignUpDialog {
    private Utilities util=new Utilities();
    private static JDialog dialog=new JDialog();
    private static JButton ok=new JButton("Εγγραφή");
    private static JPanel labelsPanel=new JPanel();
    private static JPanel textFieldsPanel=new JPanel();
    private static JPanel fNamePanel=new JPanel();
    private static JPanel up=new JPanel();
    private static final JLabel label=new JLabel("Συμπληρώστε τα στοιχεία σας\n");
    private static final JLabel spare=new JLabel("                                                                                                    ");
    private static final JLabel spare2=new JLabel("                                                                                                    ");
    private static final JLabel spare3=new JLabel("                                                                                                    ");
    private static final JLabel fNameLabel=new JLabel("Όνομα     ");
    private static final JLabel lNameLabel=new JLabel("Επώνυμο ");
    private static final JLabel usernameLabel=new JLabel("Username");
    private static final JLabel passwordLabel=new JLabel("Password ");
    private static JTextField fNameTextField=new JTextField();
    private static JTextField lNameTextField=new JTextField();
    private static JTextField usernameTextField=new JTextField();
    private static JPasswordField passwordField=new JPasswordField();




    public SignUpDialog(){
        dialog.setSize(370,450);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Φόρμα εγγραφής νέου χρήστη");
        dialog.setLayout(new FlowLayout());
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        label.setFont(new Font("Times New Roman", Font.BOLD, 22));

        fNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

        fNameTextField.setPreferredSize(new Dimension(200,30));
        lNameTextField.setPreferredSize(new Dimension(200,30));
        usernameTextField.setPreferredSize(new Dimension(200,30));
        passwordField.setPreferredSize(new Dimension(200,30));

        ok.setPreferredSize(new Dimension(100,30));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fName = fNameTextField.getText();
                String lName = lNameTextField.getText();
                String username = usernameTextField.getText();
                String password = passwordField.getText();
                int b;

                try {
                    b = util.addCustomer(username, password, fName, lName);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                if (b==0){
                    dialog.dispose();
                    fNameTextField.setText("");
                    lNameTextField.setText("");
                    usernameTextField.setText("");
                    passwordField.setText("");
                }
                new SignUpResultDialog(b);
            }
        });

        dialog.add(spare3);
        dialog.add(label);
        dialog.add(spare);
        dialog.add(fNameLabel);
        dialog.add(fNameTextField);
        dialog.add(lNameLabel);
        dialog.add(lNameTextField);
        dialog.add(usernameLabel);
        dialog.add(usernameTextField);
        dialog.add(passwordLabel);
        dialog.add(passwordField);
        dialog.add(spare2);
        dialog.add(ok);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fNameTextField.setText("");
                lNameTextField.setText("");
                usernameTextField.setText("");
                passwordField.setText("");
            }
        });

        dialog.setVisible(true);
    }
}
