package gui;

import api.Customer;
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
import java.util.ArrayList;
import java.util.HashMap;

import static gui.LoginFrame.frame;
import static gui.LoginFrame.util;

public class CustomerFrame {
    private static JFrame custFrame=new JFrame("Supermarket e-shop Customer Console");
    private static JLabel username=new JLabel();
    private static JLabel fName=new JLabel();
    private static JLabel lName=new JLabel();
    private static final JLabel searchLabel=new JLabel("Αναζήτηση προϊόντος:          ");
    private static final JLabel titleLabel=new JLabel("Τίτλος:  ");
    private static final JLabel categoryLabel=new JLabel("      Κατηγορία:");
    private static final JLabel subcategoryLabel=new JLabel("     Υποκατηγορία:");
    private static JLabel productTitle=new JLabel();
    private static JLabel productDetails=new JLabel();
    private static JLabel productCategory=new JLabel();
    private static JLabel productSubcategory=new JLabel();
    private static JLabel productPrice=new JLabel();
    private static JLabel productQty=new JLabel();
    private static JLabel spareLabel=new JLabel();
    private static JLabel upLabel=new JLabel("Όλα τα προϊόντα",SwingConstants.CENTER);
    private static JLabel unit=new JLabel();
    private static final JButton logoutButton=new JButton("Έξοδος");
    private static final JButton cartButton=new JButton("Καλάθι");
    private static final JButton historyButton=new JButton("Ιστορικό");
    private static final JButton productsButton=new JButton("Προϊόντα");
    private static final JButton searchButton=new JButton("Αναζήτηση");
    private static final JButton addToCartButton=new JButton("Προσθήκη στο καλάθι");
    private static JTextField searchTextField=new JTextField();
    private static JPanel userInfo=new JPanel();
    private static JPanel searchPanel=new JPanel();
    private static final JPanel sparePanel=new JPanel();
    private static final JPanel sparePanel2=new JPanel();
    private static final JPanel sparePanel3=new JPanel();
    private static final JPanel productsPanel=new JPanel();
    private static final JPanel detailsPanel=new JPanel();
    private static JList<String> productsList;
    private static JComboBox<String> categoryBox;
    private static JComboBox<String> subcategoryBox=new JComboBox<>();
    private String [] categories;
    private String [] subcategories;
    private static JScrollPane scrollPane;
    private static JSpinner qtySpinner;
    private static final JLabel updateQtyLabel= new JLabel("Επιλεγμένη ποσότητα");
    private static final JButton updateButton= new JButton("Ενημέρωση");
    private static final JButton deleteButton=new JButton("Διαγραφή");
    private static HashMap<Product,Integer> shoppingCart;
    private static String[] pro;
    private static Customer cust;

    public CustomerFrame(Customer customer){


        cust =customer;
        scrollPane=new JScrollPane();
        qtySpinner=new JSpinner(new SpinnerNumberModel(0,0,300,1));
        deleteButton.setVisible(false);
        updateButton.setVisible(false);
        updateQtyLabel.setVisible(false);
        custFrame.setSize(1280,720);
        custFrame.setLocationRelativeTo(null);
        custFrame.getContentPane().setBackground(Color.orange);
        custFrame.setLayout(new BorderLayout());
        custFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        custFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
                scrollPane.setVisible(false);
                searchPanel.setVisible(false);
                categoryBox.setVisible(false);
                qtySpinner.setVisible(false);
                productTitle.setText("");
                productDetails.setText("");
                productCategory.setText("");
                productSubcategory.setText("");
                productPrice.setText("");
                productQty.setText("");
            }
        });

        username.setText("("+cust.getUsername()+")");
        username.setFont(new Font("Serif",Font.BOLD,16));
        fName.setText(cust.getfName());
        fName.setFont(new Font("Serif",Font.BOLD,16));
        lName.setText(cust.getlName());
        lName.setFont(new Font("Serif",Font.BOLD,16));

        cartButton.setPreferredSize(new Dimension(100,40));
        historyButton.setPreferredSize(new Dimension(100,40));
        productsButton.setPreferredSize(new Dimension(100,40));
        logoutButton.setPreferredSize(new Dimension(100,40));
        addToCartButton.setPreferredSize(new Dimension(180,50));
        addToCartButton.setFont(new Font("Serif",Font.BOLD,14));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                custFrame.setVisible(false);
                frame.setVisible(true);
                scrollPane.setVisible(false);
                searchPanel.setVisible(false);
                categoryBox.setVisible(false);
                //detailsPanel.setVisible(false);
                qtySpinner.setVisible(false);
                productTitle.setText("");
                productDetails.setText("");
                productCategory.setText("");
                productSubcategory.setText("");
                productPrice.setText("");
                productQty.setText("");
                //shoppingCart.clear();
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product=cust.getProduct(productsList.getSelectedValue());
                cust.addToShoppingCart(product,(int)qtySpinner.getValue());
            }
        });

        upLabel.setFont(new Font("Serif",Font.BOLD,22));
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsPanel.setVisible(true);

                if (cust.getShoppingCart().isEmpty()) {
                    new EmptyCartDialog();
                } else {
                    upLabel.setText("Το καλάθι μου");
                    addToCartButton.setVisible(false);
                    qtySpinner.setVisible(false);
                    updateQtyLabel.setVisible(false);
                    updateButton.setVisible(false);
                    deleteButton.setVisible(false);
                    unit.setVisible(false);
                    detailsPanel.setBackground(Color.GRAY);

                    productTitle.setText("");
                    productDetails.setText("");
                    productCategory.setText("");
                    productSubcategory.setText("");
                    productPrice.setText("");
                    productQty.setText("");


                    HashMap<Product, Integer> shoppingCart = cust.getShoppingCart();
                    int i = 0;
                    String[] pro = new String[shoppingCart.keySet().size()];
                    for (Product p : shoppingCart.keySet()) {
                        pro[i] = p.getTitle();
                        i++;
                    }
                    productsList = new JList<>(pro);
                    productsList.setFont(new Font("Serif", Font.BOLD, 16));


                    scrollPane.setViewportView(productsList);
                    productsList.setLayoutOrientation(JList.VERTICAL);

                    productsList.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            unit.setVisible(true);
                            updateQtyLabel.setVisible(true);
                            deleteButton.setVisible(true);
                            updateButton.setVisible(true);
                            qtySpinner.setValue(shoppingCart.get(cust.getProduct(productsList.getSelectedValue())));
                            qtySpinner.setVisible(true);
                            detailsPanel.setBackground(Color.LIGHT_GRAY);

                            if (productsList.getValueIsAdjusting()) {

                                String selectedProduct = productsList.getSelectedValue();
                                Product product = cust.getProduct(selectedProduct);
                                if(product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
                                    unit.setText("kg");
                                }else {
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
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upLabel.setText("Όλα τα προϊόντα");
                deleteButton.setVisible(false);
                addToCartButton.setVisible(false);
                qtySpinner.setVisible(false);
                updateButton.setVisible(false);
                updateQtyLabel.setVisible(false);
                unit.setVisible(false);
                detailsPanel.setVisible(true);
                detailsPanel.setBackground(Color.GRAY);

                productTitle.setText("");
                productDetails.setText("");
                productCategory.setText("");
                productSubcategory.setText("");
                productPrice.setText("");
                productQty.setText("");

                String[] products=cust.getProductsNames();




                productsList = new JList<>(products);
                productsList.setFont(new Font("Serif",Font.BOLD,16));


                scrollPane.setViewportView(productsList);
                productsList.setLayoutOrientation(JList.VERTICAL);

                productsList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {

                        if (cust.getShoppingCart().keySet().contains(cust.getProduct(productsList.getSelectedValue()))){
                            qtySpinner.setValue(cust.getShoppingCart().get(cust.getProduct(productsList.getSelectedValue())));
                            updateQtyLabel.setVisible(true);
                            updateButton.setVisible(true);
                            //deleteButton.setVisible(true);
                            addToCartButton.setVisible(false);
                        }
                        else {
                            qtySpinner.setValue(0);
                            addToCartButton.setVisible(true);
                            updateButton.setVisible(false);
                            updateQtyLabel.setVisible(false);
                            deleteButton.setVisible(false);
                        }
                        qtySpinner.setVisible(true);
                        unit.setVisible(true);
                        detailsPanel.setBackground(Color.LIGHT_GRAY);

                        if (productsList.getValueIsAdjusting()) {

                            String selectedProduct = productsList.getSelectedValue();
                            Product product = cust.getProduct(selectedProduct);
                            if(product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
                                unit.setText("kg");
                            }else {
                                unit.setText("τμχ.");
                            }

                            productTitle.setText(product.getTitle());
                            productDetails.setText(product.getDescription());
                            productCategory.setText("Κατηγορία: "+product.getCategory());
                            productSubcategory.setText("Υποκατηγορία: "+product.getSubcategory());
                            productPrice.setText("Τιμή: " + String.valueOf(product.getPrice()+"0")+"€");
                            if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
                                productQty.setText("Διαθέσιμο απόθεμα: "+String.valueOf(product.getQty())+product.getUnit());
                            }
                            else {
                                productQty.setText("Διαθέσιμο απόθεμα: "+String.valueOf((int) product.getQty())+product.getUnit());
                            }
                        }
                    }
                });
            }
        });


        userInfo.setPreferredSize(new Dimension(130,900));
        sparePanel.setPreferredSize(new Dimension(120,50));
        userInfo.setBackground(Color.orange);
        sparePanel.setBackground(Color.orange);

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
        categoryLabel.setPreferredSize(new Dimension(100,25));
        categoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        subcategoryLabel.setPreferredSize(new Dimension(120,25));
        subcategoryLabel.setFont(new Font("Serif",Font.BOLD,14));
        searchTextField.setPreferredSize(new Dimension(200,25));
        searchButton.setPreferredSize(new Dimension(100,25));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upLabel.setText("Αποτελέσματα αναζήτησης");
                deleteButton.setVisible(false);
                addToCartButton.setVisible(false);
                qtySpinner.setVisible(false);
                updateButton.setVisible(false);
                updateQtyLabel.setVisible(false);
                unit.setVisible(false);
                detailsPanel.setVisible(true);
                detailsPanel.setBackground(Color.GRAY);


                productTitle.setText("");
                productDetails.setText("");
                productCategory.setText("");
                productSubcategory.setText("");
                productPrice.setText("");
                productQty.setText("");


                String title = searchTextField.getText();
                String category = categoryBox.getSelectedItem().toString();
                String subcategory = subcategoryBox.getSelectedItem().toString();
                ArrayList<String> searchResults;
                try {
                    searchResults = cust.productSearch(title, category, subcategory);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                String [] sR= searchResults.toArray(new String[0]);

                productsList = new JList<>(sR);
                productsList.setFont(new Font("Serif",Font.BOLD,16));


                scrollPane.setViewportView(productsList);
                productsList.setLayoutOrientation(JList.VERTICAL);

                productsList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {

                        if (cust.getShoppingCart().keySet().contains(cust.getProduct(productsList.getSelectedValue()))){
                            qtySpinner.setValue(cust.getShoppingCart().get(cust.getProduct(productsList.getSelectedValue())));
                            updateQtyLabel.setVisible(true);
                            updateButton.setVisible(true);
                            //deleteButton.setVisible(true);
                            addToCartButton.setVisible(false);
                        }
                        else {
                            qtySpinner.setValue(0);
                            addToCartButton.setVisible(true);
                            updateButton.setVisible(false);
                            deleteButton.setVisible(false);
                            updateQtyLabel.setVisible(false);
                        }
                        qtySpinner.setVisible(true);
                        unit.setVisible(true);
                        detailsPanel.setBackground(Color.LIGHT_GRAY);

                        if (productsList.getValueIsAdjusting()) {

                            String selectedProduct = productsList.getSelectedValue();
                            Product product = cust.getProduct(selectedProduct);

                            if(product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
                                unit.setText("kg");
                            }else {
                                unit.setText("τμχ.");
                            }

                            productTitle.setText(product.getTitle());
                            productDetails.setText(product.getDescription());
                            productCategory.setText("Κατηγορία: "+product.getCategory());
                            productSubcategory.setText("Υποκατηγορία: "+product.getSubcategory());
                            productPrice.setText("Τιμή: " + String.valueOf(product.getPrice()+"0")+"€");
                            if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
                                productQty.setText("Διαθέσιμο απόθεμα: "+String.valueOf(product.getQty())+product.getUnit());
                            }
                            else {
                                productQty.setText("Διαθέσιμο απόθεμα: "+String.valueOf((int) product.getQty())+product.getUnit());
                            }
                        }
                    }
                });


            }
        });


        categories= cust.getCategories();
        categoryBox=new JComboBox<>(categories);

        categoryBox.setPreferredSize(new Dimension(200,25));
        subcategoryBox.setPreferredSize(new Dimension(200,25));

        subcategoryBox.addItem("Όλες οι υποκατηγορίες");
        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subcategoryBox.removeAllItems();
                if (categoryBox.getSelectedIndex()!=0) {
                    subcategories= cust.getSubcategories(categoryBox.getSelectedItem().toString());

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


        productsList=new JList<>(cust.getProductsNames());
        productsList.setFont(new Font("Serif",Font.BOLD,16));

        scrollPane.setPreferredSize(new Dimension(400,custFrame.getHeight()-200));

        detailsPanel.setPreferredSize(new Dimension(530,custFrame.getHeight()-200));

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
        sparePanel3.setPreferredSize(new Dimension(30,custFrame.getHeight()-200));
        sparePanel3.setBackground(Color.GRAY);

        scrollPane.setViewportView(productsList);
        productsList.setLayoutOrientation(JList.VERTICAL);
        addToCartButton.setVisible(false);
        qtySpinner.setVisible(false);
        productsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                addToCartButton.setVisible(true);
                updateQtyLabel.setVisible(true);


                unit.setVisible(true);

                qtySpinner.setVisible(true);
                detailsPanel.setVisible(true);
                detailsPanel.setBackground(Color.LIGHT_GRAY);

                if (cust.getShoppingCart().keySet().contains(cust.getProduct(productsList.getSelectedValue()))){
                    qtySpinner.setValue(cust.getShoppingCart().get(cust.getProduct(productsList.getSelectedValue())));
                    updateQtyLabel.setVisible(true);
                    addToCartButton.setVisible(false);
                    updateButton.setVisible(true);
                    //deleteButton.setVisible(true);
                }
                else {
                    qtySpinner.setValue(0);
                    addToCartButton.setVisible(true);
                    updateQtyLabel.setVisible(false);
                    updateButton.setVisible(false);
                    deleteButton.setVisible(false);
                }

                if (productsList.getValueIsAdjusting()) {

                    String selectedProduct = productsList.getSelectedValue();

                    Product product = cust.getProduct(selectedProduct);
                    if(product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
                        unit.setText("kg");
                    }else {
                        unit.setText("τμχ.");
                    }

                    productTitle.setText(product.getTitle());
                    productDetails.setText(product.getDescription());
                    productCategory.setText("Κατηγορία: "+product.getCategory());
                    productSubcategory.setText("Υποκατηγορία: "+product.getSubcategory());
                    productPrice.setText("Τιμή: " + String.valueOf(product.getPrice()+"0")+"€");
                    if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
                        productQty.setText("Διαθέσιμο απόθεμα: "+String.valueOf(product.getQty())+product.getUnit());
                    }
                    else {
                        productQty.setText("Διαθέσιμο απόθεμα: "+String.valueOf((int) product.getQty())+product.getUnit());
                    }
                }
            }
        });


        spareLabel.setPreferredSize(new Dimension(500,100));

        detailsPanel.setBackground(Color.GRAY);

        qtySpinner.setPreferredSize(new Dimension(60,40));
        qtySpinner.setFont(new Font("Serif",Font.BOLD,20));

        unit.setVisible(false);
        unit.setFont(new Font("Serif",Font.BOLD,16));
        unit.setPreferredSize(new Dimension(40,40));

        updateQtyLabel.setFont(new Font("Serif",Font.BOLD,16));
        updateButton.setPreferredSize(new Dimension(120,50));
        updateButton.setFont(new Font("Serif",Font.BOLD,16));
        deleteButton.setPreferredSize(new Dimension(120,50));
        deleteButton.setFont(new Font("Serif",Font.BOLD,16));

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((int)qtySpinner.getValue()==0){
                    cust.removeFromCart(productsList.getSelectedValue());
                    if (cust.getShoppingCart().isEmpty()){
                        new EmptyCartDialog();
                    }
                }else {
                    cust.updateCartQty(productsList.getSelectedValue(), (int) qtySpinner.getValue());
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cust.removeFromCart(productsList.getSelectedValue());

                shoppingCart = cust.getShoppingCart();
                int i = 0;
                pro = new String[shoppingCart.keySet().size()];
                for (Product p : shoppingCart.keySet()) {
                    pro[i] = p.getTitle();
                    i++;
                }
                productsList = new JList<>(pro);
                productsList.setFont(new Font("Serif", Font.BOLD, 16));


                scrollPane.setViewportView(productsList);
                productsList.setLayoutOrientation(JList.VERTICAL);

                if (shoppingCart.isEmpty()){
                    addToCartButton.setVisible(false);
                    qtySpinner.setVisible(false);
                    updateQtyLabel.setVisible(false);
                    updateButton.setVisible(false);
                    unit.setVisible(false);
                    deleteButton.setVisible(false);
                    detailsPanel.setBackground(Color.GRAY);

                    productTitle.setText("");
                    productDetails.setText("");
                    productCategory.setText("");
                    productSubcategory.setText("");
                    productPrice.setText("");
                    productQty.setText("");
                    new EmptyCartDialog();
                }
            }
        });





        detailsPanel.add(productTitle);
        detailsPanel.add(productDetails);
        detailsPanel.add(productCategory);
        detailsPanel.add(productSubcategory);
        detailsPanel.add(productPrice);
        detailsPanel.add(productQty);
        detailsPanel.add(spareLabel);
        detailsPanel.add(updateQtyLabel);
        detailsPanel.add(addToCartButton);
        detailsPanel.add(qtySpinner);
        detailsPanel.add(unit);
        detailsPanel.add(updateButton);
        detailsPanel.add(deleteButton);


        sparePanel2.add(upLabel);
        productsPanel.add(sparePanel2);
        productsPanel.add(scrollPane);
        productsPanel.add(sparePanel3);
        productsPanel.add(detailsPanel);





        productsPanel.setBackground(Color.GRAY);




        custFrame.add(userInfo,BorderLayout.WEST);
        custFrame.add(searchPanel,BorderLayout.NORTH);
        custFrame.add(productsPanel,BorderLayout.CENTER);

        scrollPane.setVisible(true);
        searchPanel.setVisible(true);
        custFrame.setVisible(true);
    }
}
