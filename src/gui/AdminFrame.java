package gui;

import api.Administrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class AdminFrame {

    private static JFrame adminFrame=new JFrame("Supermarket e-shop Administrator Console");
    private static JPanel userInfo=new JPanel();
    private static final JButton productsButton=new JButton("Προϊόντα");
    private static final JButton logoutButton=new JButton("Έξοδος");
    private static final JButton searchButton=new JButton("Αναζήτηση");
    private static final JButton addProductButton=new JButton("Νέο προϊόν");
    private static final JButton noInventoryButton=new JButton("Χωρίς Απόθεμα");
    private static final JButton mostSoldButton=new JButton("Πιο Δημοφιλή");
    private static JTextField searchTextField=new JTextField();
    private static JPanel searchPanel=new JPanel();
    private static JPanel sparePanel=new JPanel();
    private static final JPanel sparePanel2=new JPanel();
    private static JLabel username=new JLabel();
    private static final JLabel statsLabel=new JLabel("Στατιστικά προϊόντων:");
    private static final JLabel searchLabel=new JLabel("Αναζήτηση προϊόντος:                        ");
    private static final JLabel titleLabel=new JLabel("Τίτλος");
    private static final JLabel categoryLabel=new JLabel("Κατηγορία");
    private static final JLabel subcategoryLabel=new JLabel("Υποκατηγορία");
    private static JComboBox<String> categoryBox;
    private static JComboBox<String> subcategoryBox=new JComboBox<>();
    private String [] categories;
    private String [] subcategories;



    public AdminFrame(Administrator admin){
        adminFrame.setSize(1280,720);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setLayout(new BorderLayout());
        adminFrame.getContentPane().setBackground(Color.orange);
        adminFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new LoginFrame();
            }
        });

        username.setText(admin.getUsername());
        username.setFont(new Font("Serif",Font.BOLD,16));

        statsLabel.setFont(new Font("Serif",Font.BOLD,16));

        productsButton.setPreferredSize(new Dimension(130,40));
        logoutButton.setPreferredSize(new Dimension(130,40));
        addProductButton.setPreferredSize(new Dimension(130,40));
        noInventoryButton.setPreferredSize(new Dimension(130,40));
        mostSoldButton.setPreferredSize(new Dimension(130,40));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();
                new LoginFrame();
            }
        });

        userInfo.setPreferredSize(new Dimension(170,900));
        sparePanel.setPreferredSize(new Dimension(160,50));
        sparePanel2.setPreferredSize(new Dimension(160,50));

        userInfo.add(username);
        userInfo.add(logoutButton);
        userInfo.add(sparePanel);
        userInfo.add(productsButton);
        userInfo.add(addProductButton);
        userInfo.add(sparePanel2);
        userInfo.add(statsLabel);
        userInfo.add(noInventoryButton);
        userInfo.add(mostSoldButton);


        searchLabel.setFont(new Font("Serif",Font.BOLD,14));
        titleLabel.setFont(new Font("Serif",Font.BOLD,14));
        categoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        subcategoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        searchTextField.setPreferredSize(new Dimension(200,25));
        searchButton.setPreferredSize(new Dimension(100,25));


        categories= admin.getCategories();
        categoryBox=new JComboBox<>(categories);

        categoryBox.setPreferredSize(new Dimension(200,25));
        subcategoryBox.setPreferredSize(new Dimension(200,25));

        subcategoryBox.addItem("Όλες οι υποκατηγορίες");
        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subcategoryBox.removeAllItems();
                if (categoryBox.getSelectedIndex()!=0) {
                    subcategories= admin.getSubcategories(categoryBox.getSelectedItem().toString());

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





        adminFrame.add(userInfo,BorderLayout.WEST);
        adminFrame.add(searchPanel,BorderLayout.NORTH);
        adminFrame.setVisible(true);
    }



}
