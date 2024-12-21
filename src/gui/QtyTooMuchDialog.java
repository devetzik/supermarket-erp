package gui;

import api.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QtyTooMuchDialog {
    private static final JDialog dialog=new JDialog();
    private static final JPanel panel=new JPanel();
    private static final JLabel label=new JLabel("Δεν είναι διαθέσιμη η επιλεγμένη ποσότητα. ",SwingConstants.CENTER);
    private static final JLabel label2=new JLabel();
    private static final JButton button=new JButton("OK");

    public QtyTooMuchDialog(Product product){
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400,150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Μη επαρκές απόθεμα");
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        label.setFont(new Font("Serif",Font.BOLD,16));
        label.setPreferredSize(new Dimension(350,30));
        label2.setFont(new Font("Serif",Font.BOLD,16));
        label2.setPreferredSize(new Dimension(350,30));

        if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
            label2.setText("Επιλέξτε ποσότητα μικρότερη από "+ product.getQty()+product.getUnit());
        }else {
            label2.setText("Επιλέξτε ποσότητα μικρότερη από "+ (int)product.getQty()+product.getUnit());
        }

        button.setPreferredSize(new Dimension(100,30));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        panel.add(label);
        panel.add(label2);
        panel.add(button);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
