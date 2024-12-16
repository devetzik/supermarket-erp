package gui;

import api.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewOrderDialog {
    private static String[] str;
    private static JDialog dialog=new JDialog();
    private static final JButton closeButton=new JButton("Κλείσιμο");
    private static final JPanel panel=new JPanel();
    private static final JLabel totalLabel=new JLabel("",SwingConstants.CENTER);
    private static final JLabel productLabel=new JLabel("Προϊόν    ");
    private static final JLabel priceLabel=new JLabel("            Τιμή    ");
    private static final JLabel qtyLabel=new JLabel("    Ποσότητα    ");
    private static final JLabel localcostLabel=new JLabel("    Κόστος");
    private static JScrollPane scrollPane=new JScrollPane();



    public ViewOrderDialog(Order order){
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);
        dialog.setTitle("Προβολή Παραγγελίας");
        dialog.setSize(450,600);

        String[][] pr = order.getPr();
        int counter=0;
        for (int i=0;i< pr.length;i++){
            if (pr[i][0]!=null){
                counter++;
            }
        }

        str=new String[counter];

        for (int i = 0; i< counter; i++){
            str[i]= pr[i][0] +"         "+ pr[i][1];
        }

        JList <String> cartList = new JList<>(str);
        cartList.setFont(new Font("Serif",Font.BOLD,16));


        scrollPane.setViewportView(cartList);
        cartList.setLayoutOrientation(JList.VERTICAL);

        scrollPane.setPreferredSize(new Dimension(400,400));

        totalLabel.setText("Συνολικό κόστος παραγγελίας: " + order.getTotal()+"€");
        totalLabel.setPreferredSize(new Dimension(400,50));
        totalLabel.setFont(new Font("Serif",Font.BOLD,22));

        closeButton.setFont(new Font("Serif",Font.BOLD,18));
        closeButton.setPreferredSize(new Dimension(120,40));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        productLabel.setFont(new Font("Serif",Font.BOLD,18));
        productLabel.setPreferredSize(new Dimension(200,20));
        priceLabel.setFont(new Font("Serif",Font.BOLD,18));
        qtyLabel.setFont(new Font("Serif",Font.BOLD,18));
        localcostLabel.setFont(new Font("Serif",Font.BOLD,18));


        panel.add(productLabel);
        //panel.add(priceLabel);
        //panel.add(qtyLabel);
        panel.add(localcostLabel);
        panel.add(scrollPane);
        panel.add(totalLabel);
        panel.add(closeButton);

        dialog.add(panel);

        dialog.setVisible(true);
    }
}
