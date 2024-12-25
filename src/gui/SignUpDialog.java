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
    private static final JDialog dialog=new JDialog();
    private static final JButton ok=new JButton("Εγγραφή");
    private static final JPanel panel=new JPanel();
    private static final JLabel label=new JLabel("Συμπληρώστε τα στοιχεία σας\n");
    private static final JLabel spare=new JLabel("                                                                                                    ");
    private static final JLabel spare2=new JLabel("                                                                                                    ");
    private static final JLabel spare3=new JLabel("                                                                                                    ");
    private static final JLabel fNameLabel=new JLabel("Όνομα: ", SwingConstants.RIGHT);
    private static final JLabel lNameLabel=new JLabel("Επώνυμο: ",SwingConstants.RIGHT);
    private static final JLabel usernameLabel=new JLabel("Username: ",SwingConstants.RIGHT);
    private static final JLabel passwordLabel=new JLabel("Password: ",SwingConstants.RIGHT);
    private static final JTextField fNameTextField=new JTextField();
    private static final JTextField lNameTextField=new JTextField();
    private static final JTextField usernameTextField=new JTextField();
    private static final JPasswordField passwordField=new JPasswordField();
    private static final JLabel failedLabel= new JLabel("",SwingConstants.CENTER);

    public SignUpDialog(){

        failedLabel.setVisible(false);
        dialog.setSize(430,380);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Φόρμα εγγραφής νέου χρήστη");
        dialog.setLayout(new BorderLayout());
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

        ok.setPreferredSize(new Dimension(110,40));
        ok.setFont(new Font("Serif",Font.BOLD,18));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fName = fNameTextField.getText();
                String lName = lNameTextField.getText();
                String username = usernameTextField.getText();
                String password = passwordField.getText();
                int b;

                b = Utilities.addCustomer(username, password, fName, lName);
                if (b==0){
                    new SignUpSuccessDialog();
                    dialog.dispose();
                    fNameTextField.setText("");
                    lNameTextField.setText("");
                    usernameTextField.setText("");
                    passwordField.setText("");
                }else {
                    failedLabel.setVisible(true);
                    if (b==1) {
                        failedLabel.setText("Το username χρησιμοποιείται");
                    } else if (b==3) {
                        failedLabel.setText("Συμπληρώστε τα κενά πεδία");
                    } else if (b==4) {
                        failedLabel.setText("Όνομα και επίθετο δεν μπορούν να περιέχουν αριθμούς");
                    }
                }
            }
        });

        fNameLabel.setPreferredSize(new Dimension(100,30));
        lNameLabel.setPreferredSize(new Dimension(100,30));
        usernameLabel.setPreferredSize(new Dimension(100,30));
        passwordLabel.setPreferredSize(new Dimension(100,30));

        failedLabel.setFont(new Font("Serif",Font.BOLD,16));
        failedLabel.setPreferredSize(new Dimension(400,30));

        spare3.setPreferredSize(new Dimension(400,15));
        spare.setPreferredSize(new Dimension(400,20));
        spare2.setPreferredSize(new Dimension(400,20));

        panel.setPreferredSize(new Dimension(400,450));

        panel.add(spare3);
        panel.add(label);
        panel.add(spare);
        panel.add(fNameLabel);
        panel.add(fNameTextField);
        panel.add(lNameLabel);
        panel.add(lNameTextField);
        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(spare2);
        panel.add(ok);
        panel.add(failedLabel);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fNameTextField.setText("");
                lNameTextField.setText("");
                usernameTextField.setText("");
                passwordField.setText("");
                dialog.dispose();
            }
        });

        dialog.add(panel,BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}
