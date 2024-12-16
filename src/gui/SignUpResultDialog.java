package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpResultDialog {
    private static JDialog resultDialog=new JDialog();
    private static JLabel resultLabel=new JLabel();
    private static JButton ok=new JButton();
    private static JPanel resultPanel=new JPanel();

    public SignUpResultDialog(int result){
        if (result==0){
            resultLabel.setText("Επιτυχής εγγραφή");
        } else if (result==1) {
            resultLabel.setText("Το username χρησιμοποιείται");
        } else if (result==3) {
            resultLabel.setText("Συμπληρώστε τα κενά πεδία");
        } else if (result==4) {
            resultLabel.setText("Το όνομα και το επίθετο δεν μπορούν να περιέχουν αριθμούς");
        }
        resultLabel.setFont(new Font("Serif",Font.BOLD,16));

        resultDialog.setSize(500,150);
        resultDialog.setLocationRelativeTo(null);
        resultDialog.setLayout(new BorderLayout());
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        resultPanel.add(resultLabel);

        ok.setText("OK");
        ok.setBounds(200,60,100,30);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultDialog.dispose();
            }
        });

        resultDialog.add(ok);

        resultDialog.add(resultPanel,BorderLayout.CENTER);
        resultDialog.setVisible(true);
    }
}
