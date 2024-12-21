package gui;

import api.Administrator;
import api.Product;
import api.User;
import api.Utilities;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

import static gui.Main.frame;

public class AdminFrame {
    private static final JFrame adminFrame=new JFrame("Supermarket e-shop Administrator Console");
    private static final JPanel userInfo=new JPanel();
    private static final JButton editProductButton=new JButton("Επεξεργασία");
    private static final JButton deleteProductButton=new JButton("Διαγραφή");
    private static final JButton productsButton=new JButton("Προϊόντα");
    private static final JButton logoutButton=new JButton("Έξοδος");
    private static final JButton searchButton=new JButton("Αναζήτηση");
    private static final JButton addProductButton=new JButton("Νέο προϊόν");
    private static final JButton noInventoryButton=new JButton("Χωρίς Απόθεμα");
    private static final JButton mostSoldButton=new JButton("Πιο Δημοφιλή");
    private static final JTextField searchTextField=new JTextField();
    private static final JPanel searchPanel=new JPanel();
    private static final JPanel sparePanel=new JPanel();
    private static final JPanel sparePanel2=new JPanel();
    private static final JLabel username=new JLabel();
    private static final JLabel statsLabel=new JLabel("Στατιστικά προϊόντων:");
    private static final JLabel searchLabel=new JLabel("Αναζήτηση προϊόντος:                        ");
    private static final JLabel titleLabel=new JLabel("Τίτλος");
    private static final JLabel categoryLabel=new JLabel("Κατηγορία");
    private static final JLabel subcategoryLabel=new JLabel("Υποκατηγορία");
    private static final JComboBox<String> categoryBox=new JComboBox<>(User.getCategories());
    private static final JComboBox<String> subcategoryBox=new JComboBox<>(User.getSubcategories(Objects.requireNonNull(categoryBox.getSelectedItem()).toString()));
    private static Administrator admin;
    private static final JLabel productTitle=new JLabel();
    private static final JLabel productDetails=new JLabel();
    private static final JLabel productCategory=new JLabel();
    private static final JLabel productSubcategory=new JLabel();
    private static final JLabel productPrice=new JLabel();
    private static final JLabel productQty=new JLabel();
    private static final JLabel spareLabel=new JLabel();
    private static final JLabel upLabel=new JLabel("Όλα τα προϊόντα",SwingConstants.CENTER);
    private static final JLabel unit=new JLabel();
    private static final JPanel sparePanel3=new JPanel();
    private static final JPanel detailsPanel=new JPanel();
    private static final JPanel productsPanel=new JPanel();
    private static JList<String> productsList;
    private static final JScrollPane scrollPane=new JScrollPane();

    public AdminFrame(Administrator administrator){
        detailsPanel.setVisible(false);

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
                searchPanel.setVisible(false);
                detailsPanel.setVisible(false);
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
                searchPanel.setVisible(false);
                detailsPanel.setVisible(false);
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

        mostSoldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MostSoldDialog(admin.mostSold());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        userInfo.setPreferredSize(new Dimension(170,900));
        sparePanel.setPreferredSize(new Dimension(160,50));
        sparePanel2.setPreferredSize(new Dimension(160,50));

        userInfo.add(username);
        userInfo.add(logoutButton);
        userInfo.add(sparePanel);
        sparePanel.setBackground(Color.lightGray);
        userInfo.add(productsButton);
        userInfo.add(addProductButton);
        userInfo.add(sparePanel2);
        sparePanel2.setBackground(Color.lightGray);
        userInfo.add(statsLabel);
        userInfo.add(noInventoryButton);
        userInfo.add(mostSoldButton);
        userInfo.setBackground(Color.lightGray);

        searchLabel.setFont(new Font("Serif",Font.BOLD,14));
        titleLabel.setFont(new Font("Serif",Font.BOLD,14));
        categoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        subcategoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        searchTextField.setPreferredSize(new Dimension(200,25));
        searchButton.setPreferredSize(new Dimension(100,25));

        categoryBox.setPreferredSize(new Dimension(200,25));
        subcategoryBox.setPreferredSize(new Dimension(200,25));

        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subcategoryBox.removeAllItems();
                if (categoryBox.getSelectedIndex()!=0) {
                    for (String i:admin.getSubcategories(Objects.requireNonNull(categoryBox.getSelectedItem()).toString())){
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
                String category = Objects.requireNonNull(categoryBox.getSelectedItem()).toString();
                String subcategory = Objects.requireNonNull(subcategoryBox.getSelectedItem()).toString();

                String[] sR;
                try {
                    sR = admin.productSearch(title, category, subcategory);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (sR == null) {
                    new NoSearchResultsDialog();
                } else {
                    upLabel.setText("Αποτελέσματα αναζήτησης");

                    detailsPanel.setVisible(false);

                    productsList = new JList<>(sR);
                    productsList.setFont(new Font("Serif", Font.BOLD, 16));

                    scrollPane.setViewportView(productsList);
                    productsList.setLayoutOrientation(JList.VERTICAL);

                    productsList.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            detailsPanel.setVisible(true);

                            if (productsList.getValueIsAdjusting()) {
                                String selectedProduct = productsList.getSelectedValue();

                                Product product = admin.getProduct(selectedProduct);

                                productTitle.setText(product.getTitle());
                                productDetails.setText(product.getDescription());
                                productCategory.setText("Κατηγορία: " + product.getCategory());
                                productSubcategory.setText("Υποκατηγορία: " + product.getSubcategory());
                                productPrice.setText("Τιμή: " + product.getPrice() + "0" + "€");
                                if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                                    productQty.setText("Διαθέσιμο απόθεμα: " + product.getQty() + product.getUnit());
                                } else {
                                    productQty.setText("Διαθέσιμο απόθεμα: " + (int) product.getQty() + product.getUnit());
                                }
                            }
                        }
                    });
                }
            }
        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utilities.productsRemover(admin.getProduct(productsList.getSelectedValue()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                admin.setProducts();

                new ProductDeletedDialog();

                detailsPanel.setVisible(false);

                productsList = new JList<>(admin.getProductsNames());
                productsList.setFont(new Font("Serif", Font.BOLD, 16));

                scrollPane.setViewportView(productsList);
                productsList.setLayoutOrientation(JList.VERTICAL);

                productsList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        detailsPanel.setVisible(true);

                        if (productsList.getValueIsAdjusting()) {
                            String selectedProduct = productsList.getSelectedValue();

                            Product product = admin.getProduct(selectedProduct);

                            productTitle.setText(product.getTitle());
                            productDetails.setText(product.getDescription());
                            productCategory.setText("Κατηγορία: " + product.getCategory());
                            productSubcategory.setText("Υποκατηγορία: " + product.getSubcategory());
                            productPrice.setText("Τιμή: " + product.getPrice() + "0" + "€");
                            if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                                productQty.setText("Διαθέσιμο απόθεμα: " + product.getQty() + product.getUnit());
                            } else {
                                productQty.setText("Διαθέσιμο απόθεμα: " + (int) product.getQty() + product.getUnit());
                            }
                        }
                    }
                });
            }
        });

        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditProductDialog(admin,admin.getProduct(productsList.getSelectedValue()));
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
        sparePanel2.setBackground(Color.orange);
        sparePanel3.setPreferredSize(new Dimension(30,adminFrame.getHeight()-200));
        sparePanel3.setBackground(Color.orange);

        productsList=new JList<>(admin.getProductsNames());
        productsList.setFont(new Font("Serif",Font.BOLD,16));

        scrollPane.setViewportView(productsList);
        productsList.setLayoutOrientation(JList.VERTICAL);

        productsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                detailsPanel.setVisible(true);

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
                    productPrice.setText("Τιμή: " + product.getPrice() + "0" + "€");
                    if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                        productQty.setText("Διαθέσιμο απόθεμα: " + product.getQty() + product.getUnit());
                    } else {
                        productQty.setText("Διαθέσιμο απόθεμα: " + (int) product.getQty() + product.getUnit());
                    }
                }
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsPanel.setVisible(false);

                productsList=new JList<>(admin.getProductsNames());
                productsList.setFont(new Font("Serif",Font.BOLD,16));

                scrollPane.setViewportView(productsList);
                productsList.setLayoutOrientation(JList.VERTICAL);

                productsList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        detailsPanel.setVisible(true);

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
                            productPrice.setText("Τιμή: " + product.getPrice() + "0" + "€");
                            if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")) {
                                productQty.setText("Διαθέσιμο απόθεμα: " + product.getQty() + product.getUnit());
                            } else {
                                productQty.setText("Διαθέσιμο απόθεμα: " + (int) product.getQty() + product.getUnit());
                            }
                        }
                    }
                });
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProductDialog(admin);
            }

        });

        detailsPanel.setBackground(Color.lightGray);

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

        productsPanel.setBackground(Color.orange);

        adminFrame.add(userInfo, BorderLayout.WEST);
        adminFrame.add(searchPanel,BorderLayout.NORTH);
        adminFrame.add(productsPanel,BorderLayout.CENTER);

        scrollPane.setVisible(true);
        searchPanel.setVisible(true);
        adminFrame.setVisible(true);
    }
}