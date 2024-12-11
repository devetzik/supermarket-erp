package gui;

import api.Customer;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerFrame {
    private static JFrame custFrame=new JFrame("Supermarket e-shop Customer Console");
    private static JLabel username=new JLabel();

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





        username.setText(customer.getUsername());
        username.setFont(new Font("Serif",Font.BOLD,16));

        custFrame.add(username,BorderLayout.NORTH);








        custFrame.setVisible(true);
    }


}
