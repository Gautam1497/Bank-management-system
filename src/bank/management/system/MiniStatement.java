package bank.management.system;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MiniStatement extends JFrame implements ActionListener {

    String pinnumber;
    MiniStatement(String pinnumber) {
        this.pinnumber=pinnumber;

        setLayout(null);

        JLabel mini=new JLabel();
        mini.setBounds(20,140,400,200);
        add(mini);

        JLabel bank=new JLabel("Indian Bank");
        bank.setBounds(150,20,100,20);
        add(bank);

        JLabel card=new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        JLabel balance=new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);

        try{
            Conn conn=new Conn();
            ResultSet rs=conn.s.executeQuery("select * from login where pin='"+pinnumber+"'");
            while (rs.next()){
                card.setText("Card Number: "+rs.getString("cardnumber").substring(0,4)+"XXXXXXXX"+rs.getString("cardnumber").substring(12));
            }
        }catch (Exception e) {
            System.out.println(e);
        }

        try{
            Conn conn=new Conn();
            int bal=0;
            ResultSet rs=conn.s.executeQuery("select * from bank where pin='"+pinnumber+"'");
            while (rs.next()){
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount")+"<br><br><html>");
                if(rs.getString("type").equals("Deposite")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                }else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }balance.setText("Your Current Account Balance is Rs "+bal);
        }catch (Exception e){
            System.out.println(e);
        }
        setSize(400,600);
        setLocation(20,20);
//        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}
