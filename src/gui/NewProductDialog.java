package gui;

import api.Administrator;
import api.User;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.ParseException;

public class NewProductDialog {

    private static final JLabel titleLabel=new JLabel("            Τίτλος: ");
    private static final JLabel descriptionLabel=new JLabel("     Περιγραφή: ");
    private static final JLabel categoryLabel=new JLabel("     Κατηγορία: ");
    private static final JLabel subcategoryLabel=new JLabel("Υποκατηγορία: ");
    private static final JLabel priceLabel=new JLabel("            Τιμή: ");
    private static final JLabel qtyLabel=new JLabel("Απόθεμα:");
    private static JTextField titleTextField=new JTextField();
    private static JTextField descriptionTextField=new JTextField();
    private static JComboBox<String> categoryBox=new JComboBox<>();
    private static JComboBox<String> subcategoryBox=new JComboBox<>();
    private static JFormattedTextField priceTextField=new JFormattedTextField(getMaskFormatter("##.##"));
    private static JFormattedTextField intQtyTextField=new JFormattedTextField(getMaskFormatter("###"));
    private static JFormattedTextField doubleQtyTextField=new JFormattedTextField(getMaskFormatter("###.##"));
    private static final JLabel euroLabel=new JLabel("€                            ");
    private static JLabel unitLabel=new JLabel();
    private static JDialog dialog=new JDialog();
    private static JPanel panel=new JPanel();
    private static final JButton button=new JButton("Προσθήκη");


    public NewProductDialog(Administrator admin){
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Προσθήκη Νέου Προϊόντος");
        dialog.setSize(420,400);
        dialog.setLocationRelativeTo(null);



        for (String i: admin.getCategories()){
            categoryBox.addItem(i);
        }
        categoryBox.removeItem("Όλες οι κατηγορίες");

        for (String i : admin.getSubcategories(categoryBox.getSelectedItem().toString())) {
            subcategoryBox.addItem(i);
        }
        subcategoryBox.removeItem("Όλες οι υποκατηγορίες");

        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subcategoryBox.removeAllItems();
                for (String i : admin.getSubcategories(categoryBox.getSelectedItem().toString())) {
                    subcategoryBox.addItem(i);
                }
                subcategoryBox.removeItem("Όλες οι υποκατηγορίες");

                if (subcategoryBox.getSelectedItem().equals("Φρούτα") || subcategoryBox.getSelectedItem().equals("Λαχανικά")){
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
                    if (subcategoryBox.getSelectedItem().equals("Φρούτα") || subcategoryBox.getSelectedItem().equals("Λαχανικά")) {
                        unitLabel.setText(" kg                   ");
                        doubleQtyTextField.setVisible(true);
                        intQtyTextField.setVisible(false);
                    } else {
                        unitLabel.setText(" τμχ.                  ");
                        intQtyTextField.setVisible(true);
                        doubleQtyTextField.setVisible(false);
                    }


                }
            }
        });



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


        if (subcategoryBox.getSelectedItem().equals("Φρούτα") || subcategoryBox.getSelectedItem().equals("Λαχανικά")){
            unitLabel.setText(" kg                  ");
            doubleQtyTextField.setVisible(true);
            intQtyTextField.setVisible(false);
        }else {
            unitLabel.setText(" τμχ.                  ");
            intQtyTextField.setVisible(true);
            doubleQtyTextField.setVisible(false);
        }
        intQtyTextField.setPreferredSize(new Dimension(80,25));
        doubleQtyTextField.setPreferredSize(new Dimension(80,25));

        button.setFont(new Font("Serif",Font.BOLD,16));
        button.setPreferredSize(new Dimension(120,35));

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

        dialog.add(panel,BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private static MaskFormatter getMaskFormatter(String format) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(format);
            mask.setPlaceholderCharacter('0');
        }catch (ParseException ex) {
            ex.printStackTrace();
        }
        return mask;
    }


}
