package gui;

import api.Order;
import api.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MostSoldDialog {
    private static HashMap<String,Integer> sales=new HashMap<>();
    private String [] str;
    private static JDialog dialog=new JDialog();
    private static final JLabel productLabel=new JLabel("Προϊόν",SwingConstants.LEFT);
    private static final JLabel ordersLabel=new JLabel("Παραγγελίες",SwingConstants.RIGHT);
    private static final JButton closeButton=new JButton("Κλείσιμο");
    private static JList salesList;
    private static JScrollPane scrollPane=new JScrollPane();
    private static JPanel panel=new JPanel();


    public MostSoldDialog(HashMap<String,Integer> s){
        sales=s;
        str=new String[sales.keySet().size()];

        dialog.setLocationRelativeTo(null);
        dialog.setSize(400,550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Πιο Δημοφιλή Προϊόντα");
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);


        int j=-1;
        for (String i: sales.keySet()){
            j++;
            str[j]="      "+sales.get(i) +"       " + i;
        }

        salesList=new JList<>(str);
        salesList.setFont(new Font("Serif",Font.BOLD,16));


        scrollPane.setViewportView(salesList);
        salesList.setLayoutOrientation(JList.VERTICAL);

        scrollPane.setPreferredSize(new Dimension(380,400));

        closeButton.setFont(new Font("Serif",Font.BOLD,18));
        closeButton.setPreferredSize(new Dimension(120,40));


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        productLabel.setFont((new Font("Serif",Font.BOLD,18)));
        ordersLabel.setFont((new Font("Serif",Font.BOLD,18)));

        panel.add(ordersLabel);
        panel.add(productLabel);
        panel.add(scrollPane);
        panel.add(closeButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
