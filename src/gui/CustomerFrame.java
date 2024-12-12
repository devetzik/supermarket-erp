package gui;

import api.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerFrame {
    private static JFrame custFrame=new JFrame("Supermarket e-shop Customer Console");
    private static JLabel username=new JLabel();
    private static JLabel fName=new JLabel();
    private static JLabel lName=new JLabel();
    private static JLabel searchLabel=new JLabel("Αναζήτηση προϊόντος:                        ");
    private static JLabel titleLabel=new JLabel("Τίτλος");
    private static JLabel categoryLabel=new JLabel("Κατηγορία");
    private static JLabel subcategoryLabel=new JLabel("Υποκατηγορία");
    private static JButton logoutButton=new JButton("Έξοδος");
    private static JButton cartButton=new JButton("Καλάθι");
    private static JButton historyButton=new JButton("Ιστορικό");
    private static JButton productsButton=new JButton("Προϊόντα");
    private static JButton searchButton=new JButton("Αναζήτηση");
    private static JTextField searchTextField=new JTextField();
    private static JPanel userInfo=new JPanel();
    private static JPanel searchPanel=new JPanel();

    public CustomerFrame(Customer customer){
        custFrame.setSize(1280,720);
        custFrame.setLocationRelativeTo(null);
        custFrame.getContentPane().setBackground(Color.orange);
        custFrame.setLayout(new BorderLayout());
        custFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new LoginFrame();
            }
        });


        username.setText("("+customer.getUsername()+")");
        username.setFont(new Font("Serif",Font.BOLD,16));
        fName.setText(customer.getfName());
        fName.setFont(new Font("Serif",Font.BOLD,16));
        lName.setText(customer.getlName());
        lName.setFont(new Font("Serif",Font.BOLD,16));

        cartButton.setPreferredSize(new Dimension(100,40));
        logoutButton.setPreferredSize(new Dimension(100,40));
        historyButton.setPreferredSize(new Dimension(100,40));
        productsButton.setPreferredSize(new Dimension(100,40));

        userInfo.setPreferredSize(new Dimension(130,900));

        userInfo.add(fName);
        userInfo.add(lName);
        userInfo.add(username);
        userInfo.add(productsButton);
        userInfo.add(cartButton);
        userInfo.add(historyButton);
        userInfo.add(logoutButton);


        searchLabel.setFont(new Font("Serif",Font.BOLD,14));
        titleLabel.setFont(new Font("Serif",Font.BOLD,14));
        searchTextField.setPreferredSize(new Dimension(160,30));
        searchButton.setPreferredSize(new Dimension(100,30));

        searchPanel.add(searchLabel);
        searchPanel.add(titleLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);









        custFrame.add(userInfo,BorderLayout.WEST);
        custFrame.add(searchPanel,BorderLayout.NORTH);

        custFrame.setVisible(true);
    }
}
