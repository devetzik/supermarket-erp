package gui;

import api.Administrator;
import api.Product;
import api.User;
import api.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import static api.Administrator.getMaskFormatter;


public class EditProductDialog {
    private static final JLabel titleLabel=new JLabel("            Τίτλος: ");
    private static final JLabel descriptionLabel=new JLabel("     Περιγραφή: ");
    private static final JLabel categoryLabel=new JLabel("     Κατηγορία: ");
    private static final JLabel subcategoryLabel=new JLabel("Υποκατηγορία: ");
    private static final JLabel priceLabel=new JLabel("            Τιμή: ");
    private static final JLabel qtyLabel=new JLabel(" Απόθεμα: ");
    private static final JTextField titleTextField=new JTextField();
    private static final JTextArea descriptionTextField=new JTextArea();
    private static final JComboBox<String> categoryBox=new JComboBox<>();
    private static final JComboBox<String> subcategoryBox=new JComboBox<>();
    private static final JFormattedTextField priceTextField=new JFormattedTextField(getMaskFormatter("##.##"));
    private static final JFormattedTextField intQtyTextField=new JFormattedTextField(getMaskFormatter("###"));
    private static final JFormattedTextField doubleQtyTextField=new JFormattedTextField(getMaskFormatter("###.##"));
    private static final JLabel euroLabel=new JLabel("€                            ");
    private static final JLabel unitLabel=new JLabel();
    private static final JDialog dialog=new JDialog();
    private static final JPanel panel=new JPanel();
    private static final JButton button=new JButton("Ολοκλήρωση");
    private static final JLabel failedLabel=new JLabel("Συμπληρώστε τα κενά πεδία",SwingConstants.CENTER);

    public EditProductDialog(Administrator admin, Product product){
        failedLabel.setVisible(false);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Επεξεργασία Προϊόντος");
        dialog.setSize(420,380);
        dialog.setLocationRelativeTo(null);


        for (String i: User.getCategories()){
            categoryBox.addItem(i);
        }
        categoryBox.removeItem("Όλες οι κατηγορίες");

        for (String i : User.getSubcategories(Objects.requireNonNull(categoryBox.getSelectedItem()).toString())) {
            subcategoryBox.addItem(i);
        }
        subcategoryBox.removeItem("Όλες οι υποκατηγορίες");

        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subcategoryBox.removeAllItems();
                for (String i : User.getSubcategories(categoryBox.getSelectedItem().toString())) {
                    subcategoryBox.addItem(i);
                }
                subcategoryBox.removeItem("Όλες οι υποκατηγορίες");

                if (Objects.equals(subcategoryBox.getSelectedItem(), "Φρούτα") || Objects.equals(subcategoryBox.getSelectedItem(), "Λαχανικά")){
                    unitLabel.setText(" kg                     ");
                    doubleQtyTextField.setVisible(true);
                    intQtyTextField.setVisible(false);
                }else {
                    unitLabel.setText(" τμχ.                   ");
                    intQtyTextField.setVisible(true);
                    doubleQtyTextField.setVisible(false);
                }
            }
        });

        subcategoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (categoryBox.getSelectedItem().equals("Φρέσκα τρόφιμα")) {
                    if (Objects.equals(subcategoryBox.getSelectedItem(), "Φρούτα") || Objects.equals(subcategoryBox.getSelectedItem(), "Λαχανικά")) {
                        unitLabel.setText(" kg                     ");
                        doubleQtyTextField.setVisible(true);
                        intQtyTextField.setVisible(false);
                    } else {
                        unitLabel.setText(" τμχ.                   ");
                        intQtyTextField.setVisible(true);
                        doubleQtyTextField.setVisible(false);
                    }
                }
            }
        });

        titleTextField.setText(product.getTitle());
        descriptionTextField.setText(product.getDescription());
        categoryBox.setSelectedItem(product.getCategory());
        subcategoryBox.setSelectedItem(product.getSubcategory());
        if (product.getPrice()>=10) {
            priceTextField.setText(String.valueOf(product.getPrice()));
        }else {
            priceTextField.setText("0"+ product.getPrice());
        }
        if (product.getSubcategory().equals("Φρούτα") || product.getSubcategory().equals("Λαχανικά")){
            if (product.getQty()>=100) {
                doubleQtyTextField.setText(String.valueOf(product.getQty()));
            }else {
                doubleQtyTextField.setText("0"+ product.getQty());
            }
        }else {
            if (product.getQty()>=100) {
                intQtyTextField.setText(String.valueOf(product.getQty()));
            }else {
                intQtyTextField.setText("0"+ product.getQty());
            }
        }

        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        descriptionLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        categoryLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        subcategoryLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        priceLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        euroLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        qtyLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        unitLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

        titleTextField.setPreferredSize(new Dimension(250,25));
        descriptionTextField.setPreferredSize(new Dimension(250,90));
        categoryBox.setPreferredSize(new Dimension(250,25));
        subcategoryBox.setPreferredSize(new Dimension(250,25));
        priceTextField.setPreferredSize(new Dimension(80,25));

        descriptionTextField.setLineWrap(true);

        titleTextField.setFont(new Font("Times New Roman", Font.BOLD, 16));
        descriptionTextField.setFont(new Font("Times New Roman", Font.BOLD, 16));

        if (Objects.equals(subcategoryBox.getSelectedItem(), "Φρούτα") || Objects.equals(subcategoryBox.getSelectedItem(), "Λαχανικά")){
            unitLabel.setText(" kg                     ");
            doubleQtyTextField.setVisible(true);
            intQtyTextField.setVisible(false);
        }else {
            unitLabel.setText(" τμχ.                   ");
            intQtyTextField.setVisible(true);
            doubleQtyTextField.setVisible(false);
        }
        intQtyTextField.setPreferredSize(new Dimension(80,25));
        doubleQtyTextField.setPreferredSize(new Dimension(80,25));

        button.setFont(new Font("Serif",Font.BOLD,16));
        button.setPreferredSize(new Dimension(140,35));

        failedLabel.setFont(new Font("Serif",Font.BOLD,16));
        failedLabel.setPreferredSize(new Dimension(400,30));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x;
                String title= titleTextField.getText();
                String description= descriptionTextField.getText();
                String category= categoryBox.getSelectedItem().toString();
                String subcategory= Objects.requireNonNull(subcategoryBox.getSelectedItem()).toString();
                double price= Double.parseDouble(priceTextField.getText());
                double qty;
                if (subcategory.equals("Φρούτα")  || subcategory.equals("Λαχανικά")){
                    qty= Double.parseDouble(doubleQtyTextField.getText());
                }else {
                    qty = Double.parseDouble(intQtyTextField.getText());
                }
                x= admin.CheckAddProduct(title,description,price);

                if(x==0){
                    Utilities.productsRemover(product);
                    Administrator.addProduct(title,description,category,subcategory,price,qty);
                    AdminFrame.setProductDetails(title,description,category,subcategory,price,qty,unitLabel.getText());
                    Administrator.setProducts();
                    new ProductEditSuccessDialog();
                    dialog.dispose();
                }else {
                    failedLabel.setVisible(true);
                }
            }
        });


        panel.add(titleLabel);
        panel.add(titleTextField);
        panel.add(descriptionLabel);
        panel.add(descriptionTextField);
        panel.add(categoryLabel);
        panel.add(categoryBox);
        panel.add(subcategoryLabel);
        panel.add(subcategoryBox);
        panel.add(priceLabel);
        panel.add(priceTextField);
        panel.add(euroLabel);
        panel.add(qtyLabel);
        panel.add(intQtyTextField);
        panel.add(doubleQtyTextField);
        panel.add(unitLabel);

        panel.add(button);
        panel.add(failedLabel);

        dialog.add(panel,BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}
