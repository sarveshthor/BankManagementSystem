package bank.management.system;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {
    
    String pinnumber;
    JPasswordField pin, repin;
    JButton change, back;
    
    PinChange(String pinnumber){
        this.pinnumber = pinnumber;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel image = new JLabel(i3);
        image.setBounds(0 , 0 ,900, 900);
        add(image);
        
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System" , Font.BOLD, 16));
        text.setBounds(250 , 280 , 500 ,35);
        image.add(text);
        
        JLabel pintext = new JLabel("New PIN:");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System" , Font.BOLD, 16));
        pintext.setBounds(165 , 320 , 180 ,25);
        image.add(pintext);
        
        JLabel repintext = new JLabel("Re-Enter New PIN:");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System" , Font.BOLD, 16));
        repintext.setBounds(165 , 360 , 180 ,25);
        image.add(repintext);
        
        pin = new JPasswordField();
        pin.setFont(new Font("System" , Font.BOLD, 16));
        pin.setBounds(330, 320 , 180 , 25);
        image.add(pin);
        
        repin = new JPasswordField();
        repin.setFont(new Font("System" , Font.BOLD, 16));
        repin.setBounds(330, 360 , 180 , 25);
        image.add(repin);
        
        change = new JButton("CHANGE");
        change.setBounds(355 , 485 , 150 ,30);
        change.addActionListener(this);
        image.add(change);
        
        back = new JButton("BACK");
        back.setBounds(355 , 520 , 150 ,30);
        back.addActionListener(this);
        image.add(back);
        
        
        
        
        
        setSize(900 , 900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
        
                
                
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
        String spin = pin.getText();
        String srepin = repin.getText();
        if(ae.getSource() == change){
        if(spin.equals("") || srepin.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Fields");
        }
        else if(!spin.equals(srepin)){
            JOptionPane.showMessageDialog(null, "Both fields do not match");
            JOptionPane.showMessageDialog(null, "Kindly re-enter the pin");
        }
        else{
            Conn conn = new Conn();
            String query1 = "update bank set pin = '"+srepin+"' where pin = '"+pinnumber+"'";
            String query2 = "update login set pinnumber = '"+srepin+"' where pinnumber = '"+pinnumber+"'";
            String query3 = "update signupthree set pinnumber = '"+srepin+"' where pinnumber = '"+pinnumber+"'";
            
            conn.s.executeUpdate(query1);
            conn.s.executeUpdate(query2);
            conn.s.executeUpdate(query3);
            
            JOptionPane.showMessageDialog(null, "PIN Changed Successfully");
            setVisible(false);
            new Transactions(srepin).setVisible(true);
        }
        
        }else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
        
    }
    catch(Exception e){
        System.out.println(e);
    }
    
    }
    

    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
