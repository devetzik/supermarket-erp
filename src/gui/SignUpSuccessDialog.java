package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpSuccessDialog {
    private static final JDialog resultDialog=new JDialog();
    private static final JLabel resultLabel=new JLabel("Επιτυχής εγγραφή",SwingConstants.CENTER);
    private static final JButton ok=new JButton("OK");
    private static final JPanel resultPanel=new JPanel();

    public SignUpSuccessDialog(){
        resultDialog.setSize(350,150);
        resultDialog.setLocationRelativeTo(null);
        resultDialog.setLayout(new BorderLayout());
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);
        resultDialog.setTitle("Επιτυχής Εγγραφή");

        resultLabel.setFont(new Font("Serif",Font.BOLD,18));
        resultLabel.setPreferredSize(new Dimension(300,50));
        ok.setPreferredSize(new Dimension(100,30));

        resultPanel.add(resultLabel);
        resultPanel.add(ok);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultDialog.dispose();
            }
        });

        resultDialog.add(resultPanel,BorderLayout.CENTER);
        resultDialog.setVisible(true);
    }
}
