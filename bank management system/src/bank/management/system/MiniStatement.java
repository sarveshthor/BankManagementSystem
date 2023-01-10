package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{
    
    String pinnumber;
    
    MiniStatement(String pinnumber){
        
        this.pinnumber = pinnumber;
        
        setTitle("MiniStatement");
        setLayout(null);
        
        JLabel mini = new JLabel();
        mini.setBounds(20 , 150 , 400 ,200);
        add(mini);
        
        JLabel bank = new JLabel("HDFC Bank");
        bank.setBounds(150 , 20 , 100 , 20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20 , 80 , 300 ,20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20,450 ,300 ,20);
        add(balance);
        
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pinnumber = '"+pinnumber+"'");
            while(rs.next()){
                card.setText("CardNumber: " + rs.getString("cardnumber").substring(0 , 4)+ "XXXXXXXXXXXX");
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while(rs.next()){
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    bal = bal + Integer.parseInt(rs.getString("amount"));
                }else{
                    bal = bal - Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your Current Balance is Rs "+ bal );
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        
        setSize(400 , 600);
        setLocation(20, 20);
        setVisible(true);
        getContentPane().setBackground(Color.white);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
    }


    public static void main(String args[]) {
        new MiniStatement("");
    }
}
