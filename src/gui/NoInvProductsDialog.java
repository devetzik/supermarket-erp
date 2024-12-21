package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoInvProductsDialog {
    private static final JDialog dialog = new JDialog();
    private static final JLabel productLabel = new JLabel("Προϊόν", SwingConstants.CENTER);
    private static final JButton closeButton = new JButton("Κλείσιμο");
    private static JList<String> noInvProductsList;
    private static final JScrollPane scrollPane = new JScrollPane();
    private static final JPanel panel = new JPanel();


    public NoInvProductsDialog(String[] noInvProducts) {
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Μηδενικό Απόθεμα");
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        for (int i=0;i<noInvProducts.length;i++){
            System.out.println(noInvProducts[i]);
        }

        noInvProductsList = new JList<>(noInvProducts);
        noInvProductsList.setFont(new Font("Serif", Font.BOLD, 16));

        scrollPane.setViewportView(noInvProductsList);
        noInvProductsList.setLayoutOrientation(JList.VERTICAL);

        scrollPane.setPreferredSize(new Dimension(350, 350));

        closeButton.setFont(new Font("Serif", Font.BOLD, 18));
        closeButton.setPreferredSize(new Dimension(120, 40));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        productLabel.setFont((new Font("Serif", Font.BOLD, 18)));

        panel.add(productLabel);
        panel.add(scrollPane);
        panel.add(closeButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
