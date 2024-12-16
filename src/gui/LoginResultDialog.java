package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginResultDialog {

    private static JDialog loginResultDialog=new JDialog();
    private static JPanel resultPanel=new JPanel();
    private static JLabel resultLabel=new JLabel();
    private static JButton ok=new JButton();
    public LoginResultDialog(int result){
        if (result==0){
            resultLabel.setText("Καλωσήρθατε");
        } else if (result==1) {
            resultLabel.setText("Λάθος password, προσπαθήστε ξανά");
        } else if (result==2) {
            resultLabel.setText("Δεν βρέθηκε χρήστης με αυτό το username");
        } else if (result==3) {
            resultLabel.setText("Συμπληρώστε τα κενά πεδία");
        }
        resultLabel.setFont(new Font("Serif",Font.BOLD,16));

        loginResultDialog.setSize(400,150);
        loginResultDialog.setLocationRelativeTo(null);
        loginResultDialog.setLayout(new BorderLayout());
        loginResultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        loginResultDialog.setResizable(false);

        resultPanel.add(resultLabel);

        ok.setText("OK");
        ok.setBounds(150,60,100,30);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginResultDialog.dispose();
            }
        });

        loginResultDialog.add(ok);

        loginResultDialog.add(resultPanel,BorderLayout.CENTER);
        loginResultDialog.setVisible(true);
    }
}
