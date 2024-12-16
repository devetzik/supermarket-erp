package gui;

import api.Customer;
import api.Order;
import api.Product;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewOrderDialog {
    private static String[] str;
    private static JDialog dialog=new JDialog();
    private static final JButton closeButton=new JButton("Κλείσιμο");
    private static final JPanel panel=new JPanel();
    private static final JLabel totalLabel=new JLabel("",SwingConstants.CENTER);
    private static final JLabel productLabel=new JLabel("          Προϊόν");
    private static final JLabel priceLabel=new JLabel("            Τιμή    ");
    private static final JLabel qtyLabel=new JLabel("Ποσότητα       ");
    private static final JLabel localcostLabel=new JLabel("    Κόστος");
    private static JScrollPane scrollPane=new JScrollPane();
    private Customer customer;

    {
        try {
            customer = new Customer("","","","");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public ViewOrderDialog(Order order){
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);
        dialog.setTitle("Προβολή Παραγγελίας");
        dialog.setSize(500,600);

        String[][] pr = order.getPr();
        int counter=0;
        for (int i=0;i< pr.length;i++){
            if (pr[i][0]!=null){
                counter++;
            }
        }

        str=new String[counter];

        for (int i = 0; i< counter; i++){
            Product product=customer.getProduct(pr[i][0]);
            str[i]= "          "+pr[i][1]+ product.getUnit() +"               "+ pr[i][0];
        }

        JList <String> cartList = new JList<>(str);
        cartList.setFont(new Font("Serif",Font.BOLD,16));


        scrollPane.setViewportView(cartList);
        cartList.setLayoutOrientation(JList.VERTICAL);

        scrollPane.setPreferredSize(new Dimension(450,400));

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
        productLabel.setPreferredSize(new Dimension(250,20));
        priceLabel.setFont(new Font("Serif",Font.BOLD,18));
        qtyLabel.setFont(new Font("Serif",Font.BOLD,18));
        localcostLabel.setFont(new Font("Serif",Font.BOLD,18));



        //panel.add(priceLabel);
        panel.add(qtyLabel);
        panel.add(productLabel);
        //panel.add(localcostLabel);
        panel.add(scrollPane);
        panel.add(totalLabel);
        panel.add(closeButton);

        dialog.add(panel);

        dialog.setVisible(true);
    }
}
