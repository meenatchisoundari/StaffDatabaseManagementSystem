/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staffdatabasemanagementsystem;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author ELCOT
 */

public class GetChoice extends JFrame implements ActionListener
{
    Container content;
    JButton view_staff,update_staff,delete_staff,exit_button;
    public GetChoice(){
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        int h = d.height;
        int w = d.width;
        this.setSize(w,h );
                
        setTitle("Staff Registration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ELCOT\\Documents\\NetBeansProjects\\StaffDatabaseManagementSystem\\build\\classes\\staffdatabasemanagementsystem\\logo.png");  
        setIconImage(icon);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        
        Color c = new Color(255, 150, 50, 100);
        getContentPane().setBackground(c);

        content = getContentPane();
        content.setLayout(null);
        
        JLabel login_title = new JLabel("SRI KRISHNA COLLEGE OF TECHNOLOGY");
        login_title.setSize(600,55);
        login_title.setLocation(355,20);
        login_title.setFont(new Font("Serif", Font.BOLD, 28));  
        content.add(login_title);
        
        JLabel staff_login_title = new JLabel("CHOOSE ONE OF THE OPTIONS");
        staff_login_title.setSize(600,55);
        staff_login_title.setLocation(363,80);
        staff_login_title.setHorizontalAlignment(JLabel.CENTER);
        staff_login_title.setFont(new Font("Serif", Font.PLAIN, 25));  
        content.add(staff_login_title);
        
        view_staff = new JButton("VIEW STAFF DETIALS");
        view_staff.setFont(new Font("Serif", Font.PLAIN, 15));
        view_staff.setSize(220, 25);
        view_staff.setLocation(560, 200);
        view_staff.addActionListener(this);
        content.add(view_staff);
        
        update_staff = new JButton("UPDATE STAFF DETAILS");
        update_staff.setFont(new Font("Serif", Font.PLAIN, 15));
        update_staff.setSize(220, 25);
        update_staff.setLocation(560, 250);
        update_staff.addActionListener(this);
        content.add(update_staff);
        
        delete_staff = new JButton("DELETE STAFF DETAILS");
        delete_staff.setFont(new Font("Serif", Font.PLAIN, 15));
        delete_staff.setSize(220, 25);
        delete_staff.setLocation(560, 300);
        delete_staff.addActionListener(this);
        content.add(delete_staff);
        
        exit_button = new JButton("EXIT");
        exit_button.setFont(new Font("Serif", Font.PLAIN, 15));
        exit_button.setSize(100, 25);
        exit_button.setLocation(620, 350);
        exit_button.addActionListener(this);
        content.add(exit_button);
        
        setVisible(true);
    }
    public static void main(String[] args) {
        new GetChoice();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj_source=ae.getSource();

        if(obj_source==view_staff){
            ViewStaffDetails open = new ViewStaffDetails();
            boolean can_open_choice = open.ViewDetails();
            if(can_open_choice){
                new GetChoice();
            }
        }
        
        if(obj_source==update_staff){
            String staff_id_toUpdate = JOptionPane.showInputDialog("ENTER STAFF ID TO UPDATE");
            try{
                if(staff_id_toUpdate.length()<1){
                    JOptionPane.showMessageDialog(null,"Staff ID cannot be empty");
                }
                else if(staff_id_toUpdate.length()==7){
                    try{
                            boolean check_staff_id_toupd = false;
                            getConnection getCon = new getConnection();
                            Connection connect_to_driver = getCon.getConnection();

                            String query_for_staff_id = "SELECT STAFF_ID FROM STAFF_REGISTRATION";
                            PreparedStatement prestmnt = connect_to_driver.prepareStatement(query_for_staff_id);
                            ResultSet res = prestmnt.executeQuery();


                            String table_staff="";
                            while(res.next()){
                                table_staff =res.getString("STAFF_ID");
                                if(table_staff.equals(staff_id_toUpdate)){
                                   check_staff_id_toupd = true;
                                   break;
                                }
                            }
                            if(table_staff.equals(staff_id_toUpdate)){
                                setVisible(false);
                                UpdateStaffDetails update = new UpdateStaffDetails();
                                update.passStaffid(staff_id_toUpdate);   
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Staff ID not found");
                            }
                    }
                    catch(Exception aw){

                    }
                }

                else if(staff_id_toUpdate.length()>7 || staff_id_toUpdate.length()<7){
                    JOptionPane.showMessageDialog(null, "Staff ID should contain 7 characters");
                }
            }
            catch(Exception fr){
                
            }
            
        }
        
        
        if(obj_source==delete_staff){
            String staff_id_toDelete=JOptionPane.showInputDialog("ENTER STAFF ID TO DELETE");   
            try{
                if(staff_id_toDelete.length()<1){
                    JOptionPane.showMessageDialog(null,"Staff ID cannot be empty");
                    staff_id_toDelete=JOptionPane.showInputDialog("Enter Staff ID to DELETE"); 
                }
                else if(staff_id_toDelete.length()==7){
                    try{
                        boolean check_staff_id_todel = false;
                        getConnection getCon = new getConnection();
                        Connection connect_to_driver = getCon.getConnection();

                        String query_for_staff_id = "SELECT STAFF_ID FROM STAFF_REGISTRATION";
                        PreparedStatement prestmnt = connect_to_driver.prepareStatement(query_for_staff_id);
                        ResultSet res = prestmnt.executeQuery();
                        
                       
                        String table_staff="";
                        while(res.next()){
                            table_staff =res.getString("STAFF_ID");
                            if(table_staff.equals(staff_id_toDelete)){
                               check_staff_id_todel = true;
                               break;
                            }
                        }
                        
                        if(table_staff.equals(staff_id_toDelete)){
                            Statement stmt = connect_to_driver.createStatement();
                            String query="delete from staff_registration where staff_id='"+staff_id_toDelete+"'";
                            stmt.executeUpdate(query);
                            connect_to_driver.setAutoCommit(true);
                            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Staff ID not found");
                        }
                    }
                    catch(Exception ca){
                        System.out.println("Staff ID not found");

                    }
                }
            }
            catch(Exception ed){
                
            }
        }
        if(obj_source==exit_button){
            setVisible(false);
            System. exit(0);
        }
    }
}
