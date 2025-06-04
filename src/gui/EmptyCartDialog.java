package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmptyCartDialog {

    private static final JDialog dialog=new JDialog();
    private static final JLabel label=new JLabel("Το καλάθι είναι άδειο",SwingConstants.CENTER);
    private static final JButton button=new JButton("OK");
    private static final JPanel panel=new JPanel();
    public EmptyCartDialog(){
        dialog.setSize(300,150);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Άδειο καλάθι");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        label.setFont(new Font("Serif",Font.BOLD,16));
        label.setPreferredSize(new Dimension(250,50));
        button.setPreferredSize(new Dimension(100,30));

        panel.add(label);
        panel.add(button);

        dialog.add(panel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }
}


