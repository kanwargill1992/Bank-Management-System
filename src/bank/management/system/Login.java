package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, clear, signup;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("Automated Teller Machine");

        setLayout(null);

        ImageIcon il = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = il.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardNumber = new JLabel("Card No:");
        cardNumber.setFont(new Font("Raleway", Font.BOLD, 28));
        cardNumber.setBounds(120, 150, 150, 30);
        add(cardNumber);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        // Set default background and foreground colors for buttons
//        UIManager.put("Button.background", Color.BLACK);
//        UIManager.put("Button.foreground", Color.WHITE);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGNUP");
        signup.setBounds(300, 350, 230, 30);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            try {
                if (conn != null) {
                    String cardNumber = cardTextField.getText();
                    String pinNumber = pinTextField.getText();
                    String query = "select * from login where cardNumber = '" + cardNumber + "' and pinNumber = '" + pinNumber + "'";

                    ResultSet rs = conn.getStatement().executeQuery(query);
                    if (rs.next()) {
                        setVisible(false);
                        new Transactions(pinNumber).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Card number or PIN");
                    }

                    rs.close(); // Close ResultSet after usage
                } else {
                    // Handle null connection if it occurs
                    JOptionPane.showMessageDialog(null, "Error connecting to the database!");
                }
            } catch (SQLException e) {
                System.out.println(e);
                // Handle SQL exception
            } finally {
                // Close the connection after using it (good practice)
                try {
                    if (conn != null) {
                        conn.getStatement().close();
                        conn.close(); // Close the connection
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }


    public static void main(String[] args) {
        new Login();
    }
}
