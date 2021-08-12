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
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import oracle.net.aso.h;

/**
 *
 * @author ELCOT
 */
class StaffLogin extends JFrame implements ActionListener{
    Container content;
    private JLabel e_staff_id,e_password,login_title,staff_login_title,e_staff_password;
    private JTextField t_e_staff_id,t_e_staff_password;
    private JButton login_submit_staff,back_to_reg;
    public StaffLogin(){
        
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
        
        login_title = new JLabel("SRI KRISHNA COLLEGE OF TECHNOLOGY");
        login_title.setSize(600,55);
        login_title.setLocation(350,20);
        login_title.setFont(new Font("Serif", Font.BOLD, 28));  
        content.add(login_title);
        
        staff_login_title = new JLabel("STAFF LOGIN");
        staff_login_title.setSize(600,55);
        staff_login_title.setLocation(550,80);
        staff_login_title.setFont(new Font("Serif", Font.BOLD, 25));  
        content.add(staff_login_title);
        
        e_staff_id = new JLabel("ENTER STAFF ID:");
        e_staff_id.setSize(600,55);
        e_staff_id.setLocation(480,180);
        e_staff_id.setFont(new Font("Serif", Font.PLAIN, 17));  
        content.add(e_staff_id);
        
        t_e_staff_id = new JTextField();
        t_e_staff_id.setSize(140,25);
        t_e_staff_id.setLocation(650,195);
        content.add(t_e_staff_id);
        
        e_staff_password = new JLabel("ENTER PASSWORD:");
        e_staff_password.setSize(600,55);
        e_staff_password.setLocation(480,240);
        e_staff_password.setFont(new Font("Serif", Font.PLAIN, 17));  
        content.add(e_staff_password);
        
        t_e_staff_password = new JPasswordField();
        t_e_staff_password.setSize(140,25);
        t_e_staff_password.setLocation(650, 253);
        content.add(t_e_staff_password);
        
        login_submit_staff = new JButton("LOGIN");
        login_submit_staff.setFont(new Font("Serif", Font.PLAIN, 15));
        login_submit_staff.setSize(90, 20);
        login_submit_staff.setLocation(600, 315);
        login_submit_staff.addActionListener(this);
        content.add(login_submit_staff);
        
        back_to_reg = new JButton("BACK TO REGISTRATION");
        back_to_reg.setFont(new Font("Serif", Font.PLAIN, 15));
        back_to_reg.setSize(250, 22);
        back_to_reg.setLocation(515, 355);
        back_to_reg.addActionListener(this);
        content.add(back_to_reg);
        
        setVisible(true); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {

         Object obj_source=e.getSource();
         
            if(obj_source==login_submit_staff){
                getConnection getCon = new getConnection();
                String entered_id = t_e_staff_id.getText().toString();
                String entered_password = t_e_staff_password.getText().toString();
                if(entered_id.length()<1){
                    JOptionPane.showMessageDialog(null,"Enter Staff ID");
                }
                if(entered_password.length()<1){
                    JOptionPane.showMessageDialog(null,"Enter password");
                }
                try{
                    Connection conn = getCon.getConnection();
                    boolean check_login_status = false;
                    String query = "SELECT STAFF_ID FROM STAFF_REGISTRATION";
                    PreparedStatement prestmt = conn.prepareStatement(query);
                    ResultSet set = prestmt.executeQuery();
                    
                    while(set.next()){
                        if(entered_id.equals(set.getString("STAFF_ID"))){
                            check_login_status = true;
                            break;
                        }
                    }
                    conn.close();
                    
                    
                    if(check_login_status){
                        Connection connect = getCon.getConnection();
                        boolean check_login_password = false;
                        
                        String query_for_password = "SELECT STAFF_ID,PASSWORD FROM STAFF_REGISTRATION WHERE STAFF_ID='"+entered_id+"'";
                        PreparedStatement prestmnt = connect.prepareStatement(query_for_password);
                        ResultSet res = prestmnt.executeQuery();
                        
                        String table_password = "";
                        String table_staff="";
                        while(res.next()){
                            table_staff +=res.getString("STAFF_ID");
                            table_password +=res.getString("PASSWORD");
                            break;
                        }
                        
                        if(table_password.equals(entered_password) && table_staff.equals(entered_id)){
                            setVisible(false);
                            new GetChoice();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"PASSWORD INCORRECT");
                        }
                    }
                    else if(entered_id.length()>1){
                        JOptionPane.showMessageDialog(null,"USER NOT REGISTERED");
                    }
                    
                }
                catch(Exception xe){
                     JOptionPane.showMessageDialog(null,xe);
                }     
            }
            if(obj_source==back_to_reg){
                setVisible(false);
                new StaffRegistration();
            }
    }
    
}
