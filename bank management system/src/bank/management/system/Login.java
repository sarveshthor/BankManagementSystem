package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login, signup, clear;// defining button globally , so they can be used out of the constructor
    JTextField cardTextField;
    JPasswordField pinTextField;
    
    Login(){
        setLayout(null); //for disabling the default layout
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100 , 100 , Image.SCALE_DEFAULT); // for scaling the logo image
        ImageIcon i3 = new ImageIcon(i2);// JLable does not take Image object as argument, so convert the Image object
                                         //to ImageIcon
        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100); // for placing the logo at top left
        add(label); // adding image(label) to the framework
        
        JLabel text = new JLabel("Welcome to ATM");
        add(text); // adding text to framework
        text.setFont(new Font("Osward" , Font.BOLD, 38)); // setting the font
        text.setBounds(200,40,400,40); // without this text might not show up on framework
        
        JLabel cardNo = new JLabel("Card No.");
        add(cardNo); // adding text to framework
        cardNo.setFont(new Font("Raleway" , Font.BOLD, 28)); // setting the font
        cardNo.setBounds(120,150,150,30); // without this text might not show up on framework
        
        cardTextField = new JTextField();
        cardTextField.setBounds(300 , 150 , 230, 30);
        cardTextField.setFont(new Font("Arial" , Font.BOLD, 14));
        add(cardTextField);
        
        JLabel pin = new JLabel("Pin");
        add(pin); // adding text to framework
        pin.setFont(new Font("Raleway" , Font.BOLD, 28)); // setting the font
        pin.setBounds(120,220,250,30); // without this text might not show up on framework
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300 , 220 , 230, 30);
        pinTextField.setFont(new Font("Arial" , Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this); // funtion for indicating that the button is clicked
        add(signup);
        
        getContentPane().setBackground(Color.WHITE);
        
        setTitle("Automated Teller Machine");//setting the title of the frame
        setSize(800 , 480); //creating frame, setting length and breadth of the frame.
        setVisible(true); // for seeing the frame.
        setLocation(350, 200); //for opening the frame in center
    }
    
    
    public void actionPerformed(ActionEvent ae){ // defining action which are performed onClick //Overriding abstract method
        if(ae.getSource() == clear){
           cardTextField.setText("");
           pinTextField.setText("");
        }
        else if(ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pinnumber = '"+pinnumber+"' ";
            
            try{
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){ //data found
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "User Not Found");
                }
                
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else if(ae.getSource() == signup){
            setVisible(false);// closing the login frame
            new SignupOne().setVisible(true); //opening the signup frame
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
