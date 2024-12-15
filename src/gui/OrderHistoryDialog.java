package gui;

import api.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderHistoryDialog {
    private ArrayList<Order> orderHistory=new ArrayList<>();
    private String [] str;
    private static JDialog dialog=new JDialog();
    private static final JButton closeButton=new JButton("Κλείσιμο");
    private static final JButton viewButton=new JButton("Προβολή");
    private static JList ordersList;
    private static JScrollPane scrollPane=new JScrollPane();
    private static JPanel panel=new JPanel();


    public OrderHistoryDialog(ArrayList<Order> ordHis){
        orderHistory=ordHis;
        str=new String[orderHistory.size()];

        dialog.setLocationRelativeTo(null);
        dialog.setSize(600,500);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Ιστορικό παραγγελιών");
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        for (int i=0; i<orderHistory.size();i++){
            str[i]="Παραγγελία  #"+(i+1)+ "       Ημερομηνία: "+ orderHistory.get(i).getDatetime()+"       Συνολικό Κόστος: "+orderHistory.get(i).getTotal()+"€";
        }

        ordersList=new JList<>(str);
        ordersList.setFont(new Font("Serif",Font.BOLD,16));


        scrollPane.setViewportView(ordersList);
        ordersList.setLayoutOrientation(JList.VERTICAL);

        scrollPane.setPreferredSize(new Dimension(500,400));

        viewButton.setFont(new Font("Serif",Font.BOLD,18));
        viewButton.setPreferredSize(new Dimension(120,40));
        closeButton.setFont(new Font("Serif",Font.BOLD,18));
        closeButton.setPreferredSize(new Dimension(120,40));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });


        panel.add(scrollPane);
        panel.add(viewButton);
        panel.add(closeButton);

        dialog.add(panel);

        dialog.setVisible(true);
    }
}
