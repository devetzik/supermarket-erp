package gui;

import api.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static gui.LoginFrame.util;

public class CustomerFrame {
    private static JFrame custFrame=new JFrame("Supermarket e-shop Customer Console");
    private static JLabel username=new JLabel();
    private static JLabel fName=new JLabel();
    private static JLabel lName=new JLabel();
    private static final JLabel searchLabel=new JLabel("Αναζήτηση προϊόντος:                        ");
    private static final JLabel titleLabel=new JLabel("Τίτλος");
    private static final JLabel categoryLabel=new JLabel("Κατηγορία");
    private static final JLabel subcategoryLabel=new JLabel("Υποκατηγορία");
    private static final JButton logoutButton=new JButton("Έξοδος");
    private static final JButton cartButton=new JButton("Καλάθι");
    private static final JButton historyButton=new JButton("Ιστορικό");
    private static final JButton productsButton=new JButton("Προϊόντα");
    private static final JButton searchButton=new JButton("Αναζήτηση");
    private static JTextField searchTextField=new JTextField();
    private static JPanel userInfo=new JPanel();
    private static JPanel searchPanel=new JPanel();
    private static JPanel sparePanel=new JPanel();
    private static final JPanel sparePanel2=new JPanel();
    private static JComboBox<String> categoryBox;
    private static JComboBox<String> subcategoryBox=new JComboBox<>();
    private String [] categories;
    private String [] subcategories;


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
        sparePanel.setPreferredSize(new Dimension(120,50));

        userInfo.add(fName);
        userInfo.add(lName);
        userInfo.add(username);
        userInfo.add(logoutButton);
        userInfo.add(sparePanel);
        userInfo.add(productsButton);
        userInfo.add(cartButton);
        userInfo.add(historyButton);

        searchLabel.setFont(new Font("Serif",Font.BOLD,14));
        titleLabel.setFont(new Font("Serif",Font.BOLD,14));
        categoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        subcategoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        searchTextField.setPreferredSize(new Dimension(200,25));
        searchButton.setPreferredSize(new Dimension(100,25));


        categories= customer.getCategories();
        categoryBox=new JComboBox<>(categories);

        categoryBox.setPreferredSize(new Dimension(200,25));
        subcategoryBox.setPreferredSize(new Dimension(200,25));

        subcategoryBox.addItem("Όλες οι υποκατηγορίες");
        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subcategoryBox.removeAllItems();
                if (categoryBox.getSelectedIndex()!=0) {
                    subcategories= customer.getSubcategories(categoryBox.getSelectedItem().toString());

                    for (String i:subcategories){
                        subcategoryBox.addItem(i);
                    }
                }else {
                    subcategoryBox.addItem("Όλες οι υποκατηγορίες");
                }
            }
        });


        searchPanel.add(searchLabel);
        searchPanel.add(titleLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(categoryLabel);
        searchPanel.add(categoryBox);
        searchPanel.add(subcategoryLabel);
        searchPanel.add(subcategoryBox);
        searchPanel.add(searchButton);

        custFrame.add(userInfo,BorderLayout.WEST);
        custFrame.add(searchPanel,BorderLayout.NORTH);
        custFrame.setVisible(true);
    }
}
