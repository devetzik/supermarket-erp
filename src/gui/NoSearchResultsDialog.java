package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoSearchResultsDialog {
    private static final JDialog dialog=new JDialog();
    private static final JPanel panel=new JPanel();
    private static final JLabel label=new JLabel("Δεν βρέθηκαν αποτελέσματα αναζήτησης",SwingConstants.CENTER);
    private static final JButton button=new JButton("OK");

    public NoSearchResultsDialog(){
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400,150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Αποτυχημένη αναζήτηση");
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        label.setFont(new Font("Serif",Font.BOLD,16));
        label.setPreferredSize(new Dimension(350,50));
        button.setPreferredSize(new Dimension(100,30));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        panel.add(label);
        panel.add(button);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
