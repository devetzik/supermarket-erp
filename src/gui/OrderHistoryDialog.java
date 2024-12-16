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
    private static final JLabel orderLabel=new JLabel("Παραγγελία #     ",SwingConstants.LEFT);
    private static final JLabel dateLabel= new JLabel("Ημερομηνία      ",SwingConstants.CENTER);
    private static final JLabel costLabel=new JLabel("Κόστος",SwingConstants.RIGHT);
    private static final JButton closeButton=new JButton("Κλείσιμο");
    private static final JButton viewButton=new JButton("Προβολή");
    private static JList ordersList;
    private static JScrollPane scrollPane=new JScrollPane();
    private static JPanel panel=new JPanel();


    public OrderHistoryDialog(ArrayList<Order> ordHis){
        orderHistory=ordHis;
        str=new String[orderHistory.size()];

        dialog.setLocationRelativeTo(null);
        dialog.setSize(400,550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Ιστορικό παραγγελιών");
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        for (int i=0; i<orderHistory.size();i++){
            str[i]="            #"+(i+1)+ "                        "+ orderHistory.get(i).getDatetime()+"               "+orderHistory.get(i).getTotal()+"€";
        }

        ordersList=new JList<>(str);
        ordersList.setFont(new Font("Serif",Font.BOLD,16));


        scrollPane.setViewportView(ordersList);
        ordersList.setLayoutOrientation(JList.VERTICAL);

        scrollPane.setPreferredSize(new Dimension(350,400));

        viewButton.setFont(new Font("Serif",Font.BOLD,18));
        viewButton.setPreferredSize(new Dimension(120,40));
        closeButton.setFont(new Font("Serif",Font.BOLD,18));
        closeButton.setPreferredSize(new Dimension(120,40));

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewOrderDialog(orderHistory.get(ordersList.getSelectedIndex()));
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        orderLabel.setFont((new Font("Serif",Font.BOLD,18)));
        dateLabel.setFont((new Font("Serif",Font.BOLD,18)));
        costLabel.setFont((new Font("Serif",Font.BOLD,18)));


        panel.add(orderLabel);
        panel.add(dateLabel);
        panel.add(costLabel);
        panel.add(scrollPane);
        panel.add(viewButton);
        panel.add(closeButton);

        dialog.add(panel);

        dialog.setVisible(true);
    }
}
