package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginResultDialog {
    private static final JDialog dialog =new JDialog();
    private static final JPanel panel =new JPanel();
    private static final JLabel label =new JLabel("",SwingConstants.CENTER);
    private static final JButton button =new JButton("OK");

    public LoginResultDialog(int result){
        dialog.setSize(400,150);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);

        if (result==0){
            dialog.setTitle("Καλωσήρθατε");
            label.setText("Καλωσήρθατε");
        } else if (result==1) {
            dialog.setTitle("Λάθος password");
            label.setText("Λάθος password, προσπαθήστε ξανά");
        } else if (result==2) {
            dialog.setTitle("Μη εγγεγραμμένος χρήστης");
            label.setText("Δεν βρέθηκε χρήστης με αυτό το username");
        } else if (result==3) {
            dialog.setTitle("Κενά πεδία");
            label.setText("Συμπληρώστε τα κενά πεδία");
        }

        label.setPreferredSize(new Dimension(350,50));
        label.setFont(new Font("Serif",Font.BOLD,18));

        button.setPreferredSize(new Dimension(100,30));
        button.setFont(new Font("Serif", Font.BOLD, 16));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        panel.add(label);
        panel.add(button);

        dialog.add(panel,BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}
