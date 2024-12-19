package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProductResultDialog {
    private static final JDialog resultDialog=new JDialog();
    private static final JLabel resultLabel=new JLabel();
    private static final JButton ok=new JButton();
    private static final JPanel resultPanel=new JPanel();

    public NewProductResultDialog(int result){
        resultDialog.setTitle("Προσθήκη νέου προϊόντος");
        if (result==0){
            resultLabel.setText("Επιτυχής προσθήκη προϊόντος");
        } else if (result==1) {
            resultLabel.setText("Συμπληρώστε τα κενά πεδία");
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
