package gui;

import api.Administrator;
import api.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static gui.Main.frame;


public class AdminFrame {

    private static JFrame adminFrame=new JFrame("Supermarket e-shop Administrator Console");
    private static JPanel userInfo=new JPanel();
    private static final JButton editProductButton=new JButton("Επεξεργασία");
    private static final JButton deleteProductButton=new JButton("Διαγραφή");
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
    private static String [] categories;
    private static String [] subcategories;
    private static Administrator admin;
    private static JLabel productTitle=new JLabel();
    private static JLabel productDetails=new JLabel();
    private static JLabel productCategory=new JLabel();
    private static JLabel productSubcategory=new JLabel();
    private static JLabel productPrice=new JLabel();
    private static JLabel productQty=new JLabel();
    private static final JLabel spareLabel=new JLabel();
    private static final JLabel upLabel=new JLabel("Όλα τα προϊόντα",SwingConstants.CENTER);
    private static JLabel unit=new JLabel();
    private static final JPanel sparePanel3=new JPanel();
    private static final JPanel detailsPanel=new JPanel();
    private static final JPanel productsPanel=new JPanel();
    private static JList<String> productsList;
    private static JScrollPane scrollPane;



    public AdminFrame(Administrator administrator){
        scrollPane=new JScrollPane();
        editProductButton.setVisible(false);
        deleteProductButton.setVisible(false);

        admin=administrator;
        adminFrame.setSize(1280,720);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setLayout(new BorderLayout());
        adminFrame.getContentPane().setBackground(Color.orange);
        adminFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                adminFrame.dispose();
                frame.setVisible(true);
                scrollPane.setVisible(false);
                searchPanel.setVisible(false);
                categoryBox.setVisible(false);

                productTitle.setText("");
                productDetails.setText("");
                productCategory.setText("");
                productSubcategory.setText("");
                productPrice.setText("");
                productQty.setText("");
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
                frame.setVisible(true);
                scrollPane.setVisible(false);
                searchPanel.setVisible(false);
                categoryBox.setVisible(false);

                productTitle.setText("");
                productDetails.setText("");
                productCategory.setText("");
                productSubcategory.setText("");
                productPrice.setText("");
                productQty.setText("");
            }
        });

        noInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (admin.noInvProducts()==null){
                    new NoNoInvProductsDialog();
                }else {
                    new NoInvProductsDialog(admin.noInvProducts());
                }
            }
        });

        userInfo.setPreferredSize(new Dimension(170,900));
        sparePanel.setPreferredSize(new Dimension(160,50));
        sparePanel2.setPreferredSize(new Dimension(160,50));

        userInfo.add(username);
        userInfo.add(logoutButton);
        userInfo.add(sparePanel);
        sparePanel.setBackground(Color.orange);
        userInfo.add(productsButton);
        userInfo.add(addProductButton);
        userInfo.add(sparePanel2);
        sparePanel2.setBackground(Color.orange);
        userInfo.add(statsLabel);
        userInfo.add(noInventoryButton);
        userInfo.add(mostSoldButton);
        userInfo.setBackground(Color.orange);




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
                    subcategories= administrator.getSubcategories(categoryBox.getSelectedItem().toString());

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
        searchPanel.setBackground(Color.orange);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = searchTextField.getText();
                String category = categoryBox.getSelectedItem().toString();
                String subcategory = subcategoryBox.getSelectedItem().toString();

                String[] sR = new String[0];
                try {
                    sR = admin.productSearch(title, category, subcategory);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                if (sR == null) {
                    new NoSearchResultsDialog();
                } else {
                    upLabel.setText("Αποτελέσματα αναζήτησης");

                    detailsPanel.setBackground(Color.GRAY);

                    editProductButton.setVisible(false);
                    deleteProductButton.setVisible(false);

                    productsList = new JList<>(sR);
                    productsList.setFont(new Font("Serif", Font.BOLD, 16));

                    scrollPane.setViewportView(productsList);
                    productsList.setLayoutOrientation(JList.VERTICAL);

                    productTitle.setText("");
                    productDetails.setText("");
                    productCategory.setText("");
                    productSubcategory.setText("");
                    productPrice.setText("");
                    productQty.setText("");

                    productsList.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            editProductButton.setVisible(true);
                            deleteProductButton.setVisible(true);
                            detailsPanel.setVisible(true);
                            detailsPanel.setBackground(Color.LIGHT_GRAY);

                            if (productsList.getValueIsAdjusting()) {

                                String selectedProduct = productsList.getSelectedValue();

                                Product product = admin.getProduct(selectedProduct);

                                productTitle.setText(product.getTitle());
                                productDetails.setText(product.getDescription());
                                productCategory.setText("Κατηγορία: " + product.getCategory());
                                productSubcategory.setText("Υποκατηγορία: " + product.getSubcategory());
                                productPrice.setText("Τιμή: " + String.valueOf(product.getPrice() + "0") + "€");
                                if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                                    productQty.setText("Διαθέσιμο απόθεμα: " + String.valueOf(product.getQty()) + product.getUnit());
                                } else {
                                    productQty.setText("Διαθέσιμο απόθεμα: " + String.valueOf((int) product.getQty()) + product.getUnit());
                                }
                            }
                        }
                    });
                }
            }
        });









        scrollPane.setPreferredSize(new Dimension(400, adminFrame.getHeight() - 200));

        detailsPanel.setPreferredSize(new Dimension(530,adminFrame.getHeight()-200));

        productTitle.setPreferredSize(new Dimension(500,60));
        productTitle.setFont(new Font("Serif",Font.BOLD,22));
        productDetails.setPreferredSize(new Dimension(500,60));
        productDetails.setFont(new Font("Serif",Font.BOLD,18));
        productCategory.setPreferredSize(new Dimension(500,30));
        productCategory.setFont(new Font("Serif",Font.BOLD,18));
        productSubcategory.setPreferredSize(new Dimension(500,30));
        productSubcategory.setFont(new Font("Serif",Font.BOLD,18));
        productPrice.setPreferredSize(new Dimension(500,30));
        productPrice.setFont(new Font("Serif",Font.BOLD,18));
        productQty.setPreferredSize(new Dimension(500,30));
        productQty.setFont(new Font("Serif",Font.BOLD,18));

        sparePanel2.setPreferredSize(new Dimension(2500,50));
        sparePanel2.setBackground(Color.GRAY);
        sparePanel3.setPreferredSize(new Dimension(30,adminFrame.getHeight()-200));
        sparePanel3.setBackground(Color.GRAY);

        productsList=new JList<>(admin.getProductsNames());
        productsList.setFont(new Font("Serif",Font.BOLD,16));

        scrollPane.setViewportView(productsList);
        productsList.setLayoutOrientation(JList.VERTICAL);

        productsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editProductButton.setVisible(true);
                deleteProductButton.setVisible(true);
                detailsPanel.setVisible(true);
                detailsPanel.setBackground(Color.LIGHT_GRAY);

                if (productsList.getValueIsAdjusting()) {

                    String selectedProduct = productsList.getSelectedValue();

                    Product product = admin.getProduct(selectedProduct);
                    if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                        unit.setText("kg");
                    } else {
                        unit.setText("τμχ.");
                    }

                    productTitle.setText(product.getTitle());
                    productDetails.setText(product.getDescription());
                    productCategory.setText("Κατηγορία: " + product.getCategory());
                    productSubcategory.setText("Υποκατηγορία: " + product.getSubcategory());
                    productPrice.setText("Τιμή: " + String.valueOf(product.getPrice() + "0") + "€");
                    if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                        productQty.setText("Διαθέσιμο απόθεμα: " + String.valueOf(product.getQty()) + product.getUnit());
                    } else {
                        productQty.setText("Διαθέσιμο απόθεμα: " + String.valueOf((int) product.getQty()) + product.getUnit());
                    }
                }
            }
        });


        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                detailsPanel.setBackground(Color.GRAY);

                editProductButton.setVisible(false);
                deleteProductButton.setVisible(false);

                productsList=new JList<>(admin.getProductsNames());
                productsList.setFont(new Font("Serif",Font.BOLD,16));

                scrollPane.setViewportView(productsList);
                productsList.setLayoutOrientation(JList.VERTICAL);

                productTitle.setText("");
                productDetails.setText("");
                productCategory.setText("");
                productSubcategory.setText("");
                productPrice.setText("");
                productQty.setText("");

                productsList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        editProductButton.setVisible(true);
                        deleteProductButton.setVisible(true);
                        detailsPanel.setVisible(true);
                        detailsPanel.setBackground(Color.LIGHT_GRAY);

                        if (productsList.getValueIsAdjusting()) {

                            String selectedProduct = productsList.getSelectedValue();

                            Product product = admin.getProduct(selectedProduct);
                            if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                                unit.setText("kg");
                            } else {
                                unit.setText("τμχ.");
                            }

                            productTitle.setText(product.getTitle());
                            productDetails.setText(product.getDescription());
                            productCategory.setText("Κατηγορία: " + product.getCategory());
                            productSubcategory.setText("Υποκατηγορία: " + product.getSubcategory());
                            productPrice.setText("Τιμή: " + String.valueOf(product.getPrice() + "0") + "€");
                            if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                                productQty.setText("Διαθέσιμο απόθεμα: " + String.valueOf(product.getQty()) + product.getUnit());
                            } else {
                                productQty.setText("Διαθέσιμο απόθεμα: " + String.valueOf((int) product.getQty()) + product.getUnit());
                            }
                        }
                    }
                });
            }
        });


        detailsPanel.setBackground(Color.GRAY);

        spareLabel.setPreferredSize(new Dimension(500,100));

        editProductButton.setPreferredSize(new Dimension(180,50));
        editProductButton.setFont(new Font("Serif",Font.BOLD,16));
        deleteProductButton.setPreferredSize(new Dimension(180,50));
        deleteProductButton.setFont(new Font("Serif",Font.BOLD,16));

        detailsPanel.add(productTitle);
        detailsPanel.add(productDetails);
        detailsPanel.add(productCategory);
        detailsPanel.add(productSubcategory);
        detailsPanel.add(productPrice);
        detailsPanel.add(productQty);
        detailsPanel.add(spareLabel);
        detailsPanel.add(editProductButton);
        detailsPanel.add(deleteProductButton);

        sparePanel2.add(upLabel);
        upLabel.setFont(new Font("Serif",Font.BOLD,22));

        productsPanel.add(sparePanel2);
        productsPanel.add(scrollPane);
        productsPanel.add(sparePanel3);
        productsPanel.add(detailsPanel);

        productsPanel.setBackground(Color.GRAY);

        adminFrame.add(userInfo, BorderLayout.WEST);
        adminFrame.add(searchPanel,BorderLayout.NORTH);
        adminFrame.add(productsPanel,BorderLayout.CENTER);

        scrollPane.setVisible(true);
        searchPanel.setVisible(true);
        adminFrame.setVisible(true);
    }
}
