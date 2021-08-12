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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author ELCOT
 */
class StaffRegistration extends JFrame implements ActionListener{
    
    private Container content;
    private JLabel staff_first_name,staff_last_name,top_title,staff_id,email_id,gender,dob,mobile_num,experience,create_pass,confirm_pass,reg_title;
    private JTextField t_staff_first_name,t_staff_last_name,t_staff_id,t_email_id,t_dob,t_mobile_num,t_create_pass,t_confirm_pass;
    private JComboBox t_experience;
    private JRadioButton male,female;
    private ButtonGroup gendergp;
    private JButton submit_staff,already_registered;
    
    private String exp_years[]
        = { " ","0","1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30"};
    
    public StaffRegistration(){
         
            
            Toolkit tk = this.getToolkit();
            Dimension dim = tk.getScreenSize();
            int x = (int) dim.getWidth() / 2 - this.getWidth() / 2;
            int y = (int) dim.getHeight() / 2 - this.getHeight() / 2;
           
           
            setTitle("Staff Registration");
            setSize(1300,850);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ELCOT\\Documents\\NetBeansProjects\\StaffDatabaseManagementSystem\\build\\classes\\staffdatabasemanagementsystem\\logo.png");  
            setIconImage(icon);  
            
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            
            Color c = new Color(255, 150, 50, 100);
            getContentPane().setBackground(c);

            setResizable(true);
            
            content = getContentPane();
            content.setLayout(null);
            
            reg_title = new JLabel("SRI KRISHNA COLLEGE OF TECHNOLOGY");
            reg_title.setSize(600,55);
            reg_title.setLocation(350,20);
            reg_title.setFont(new Font("Serif", Font.BOLD, 28));  
            content.add(reg_title);
            
            top_title = new JLabel("REGISTER");
            top_title.setSize(150,55);
            top_title.setLocation(600,80);
            top_title.setFont(new Font("Serif", Font.BOLD, 20)); 
            content.add(top_title);

            staff_first_name = new JLabel("FIRST NAME:");
            staff_first_name.setSize(200,20);
            staff_first_name.setLocation(500, 150);
            staff_first_name.setFont(new Font("Serif", Font.PLAIN, 14));  
            content.add(staff_first_name);
            
            t_staff_first_name = new JTextField();
            t_staff_first_name.setSize(140,25);
            t_staff_first_name.setLocation(650, 150);
            content.add(t_staff_first_name);
            
            staff_last_name = new JLabel("LAST NAME:");
            staff_last_name.setSize(200,20);
            staff_last_name.setLocation(500, 190);
            staff_last_name.setFont(new Font("Serif", Font.PLAIN, 14)); 
            content.add(staff_last_name);
            
            t_staff_last_name = new JTextField();
            t_staff_last_name.setSize(140,25);
            t_staff_last_name.setLocation(650, 190);
            content.add(t_staff_last_name);
            
            staff_id = new JLabel("STAFF ID:");
            staff_id.setSize(200,20);
            staff_id.setLocation(500, 230);
            staff_id.setFont(new Font("Serif", Font.PLAIN, 14));
            content.add(staff_id);
            
            t_staff_id = new JTextField();
            t_staff_id.setSize(140,25);
            t_staff_id.setLocation(650, 230);
            content.add(t_staff_id);
            
            email_id = new JLabel("EMAIL ID:");
            email_id.setSize(200,20);
            email_id.setLocation(500, 270);
            email_id.setFont(new Font("Serif", Font.PLAIN, 14));
            content.add(email_id);
            
            t_email_id = new JTextField();
            t_email_id.setSize(140,25);
            t_email_id.setLocation(650, 270);
            content.add(t_email_id);
            
            gender = new JLabel("GENDER:");
            gender.setFont(new Font("Serif", Font.PLAIN, 14));
            gender.setSize(100, 20);
            gender.setLocation(500, 310);
            content.add(gender);
            
            male = new JRadioButton("Male");
            male.setFont(new Font("Serif", Font.PLAIN, 14));
            male.setSelected(true);
            male.setSize(60, 20);
            male.setLocation(650, 310);
            content.add(male);

            female = new JRadioButton("Female");
            female.setFont(new Font("Serif", Font.PLAIN, 14));
            female.setSelected(false);
            female.setSize(75, 20);
            female.setLocation(713, 310);
            content.add(female);

            gendergp = new ButtonGroup();
            gendergp.add(male);
            gendergp.add(female);
            
            dob = new JLabel("DATE OF BIRTH:");
            dob.setSize(200,20);
            dob.setLocation(500, 350);
            dob.setFont(new Font("Serif", Font.PLAIN, 14));
            content.add(dob);
            
            t_dob = new JTextField();
            t_dob.setForeground(Color.GRAY);
            t_dob.setText("dd/mm/yyyy");
            t_dob.addFocusListener(new FocusListener() {
                
                @Override
                public void focusGained(FocusEvent e) {
                    if (t_dob.getText().equals("dd/mm/yyyy")) {
                        t_dob.setText("");
                        t_dob.setForeground(Color.BLACK);
                    }

                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (t_dob.getText().isEmpty()) {
                        t_dob.setForeground(Color.GRAY);
                        t_dob.setText("dd/mm/yyyy");
                    }
                }
            });
            
            
            t_dob.setSize(140,25);
            t_dob.setLocation(650, 350);
            content.add(t_dob);
        
            experience = new JLabel("EXPERIENCE:");
            experience.setFont(new Font("Serif", Font.PLAIN, 14));
            experience.setSize(100, 20);
            experience.setLocation(500, 390);
            content.add(experience);
            
            t_experience = new JComboBox(exp_years);
            t_experience.setFont(new Font("Arial", Font.PLAIN, 14));
            t_experience.setSize(140, 20);
            t_experience.setLocation(650, 390);
            content.add(t_experience);
            
            mobile_num = new JLabel("MOBILE NUMBER:");
            mobile_num.setSize(200,20);
            mobile_num.setLocation(500, 430);
            mobile_num.setFont(new Font("Serif", Font.PLAIN, 14));
            content.add(mobile_num);
            
            t_mobile_num = new JTextField();
            t_mobile_num.setSize(140,25);
            t_mobile_num.setLocation(650, 430);
            content.add(t_mobile_num);
            
            create_pass = new JLabel("CREATE PASSWORD:");
            create_pass.setFont(new Font("Serif", Font.PLAIN, 14));
            create_pass.setSize(140, 20);
            create_pass.setLocation(500, 470);
            content.add(create_pass);
            
            t_create_pass = new JPasswordField();
            t_create_pass.setSize(140, 25);
            t_create_pass.setLocation(650, 470);
            content.add(t_create_pass);
            
            confirm_pass = new JLabel("CONFIRM PASSWORD:");
            confirm_pass.setFont(new Font("Serif", Font.PLAIN, 14));
            confirm_pass.setSize(145, 20);
            confirm_pass.setLocation(500, 510);
            content.add(confirm_pass);
            
            t_confirm_pass = new JPasswordField();
            t_confirm_pass.setSize(140, 25);
            t_confirm_pass.setLocation(650, 510);
            content.add(t_confirm_pass);
            
            submit_staff = new JButton("SUBMIT");
            submit_staff.setFont(new Font("Serif", Font.PLAIN, 15));
            submit_staff.setSize(90, 20);
            submit_staff.setLocation(600, 565);
            submit_staff.addActionListener(this);
            content.add(submit_staff);
            
            already_registered = new JButton("ALREADY REGSITERED? LOGIN");
            already_registered.setFont(new Font("Serif", Font.PLAIN, 15));
            already_registered.setSize(270, 20);
            already_registered.setLocation(510, 600);
            already_registered.addActionListener(this);
            content.add(already_registered);
            
            setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object obj_source=e.getSource();

        if(obj_source==submit_staff){
            
            StaffValidation val = new StaffValidation();
            
            String f_name_staff = t_staff_first_name.getText().toString();      
            boolean validated_f_name = val.setFirstName(f_name_staff);
            if(validated_f_name==true){
            
                String l_name_staff = t_staff_last_name.getText().toString();   
                boolean validated_l_name = val.setLastName(l_name_staff); 
                
                if(validated_l_name==true){
                    String val_staff_id =  t_staff_id.getText().toString();
                    boolean validated_staff_id = val.setStaffId(val_staff_id);
                    
                    if(validated_staff_id==true){                               
                        String val_email_id = t_email_id.getText().toString();  
                        boolean validated_email_id = val.setEmailId(val_email_id);
                         
                        if(validated_email_id==true){
                            String val_dob = t_dob.getText().toString();
                            boolean validated_dob = val.setDob(val_dob);
                             
                            if(validated_dob==true){
                                String val_mobile_num = t_mobile_num.getText().toString();
                                boolean validated_mobile_num = val.setMobileNum(val_mobile_num);
                                 
                                if(validated_mobile_num==true){
                                    String val_password = t_create_pass.getText().toString();
                                    boolean validated_password = val.setPassword(val_password);
                                    if(validated_password==true){
                                        String check_pass = val.getPassword();
                                        if(t_confirm_pass.getText().equals(check_pass)){
                                            if (male.isSelected()){
                                                String validated_gender = "Male";
                                                val.setGender(validated_gender);
                                            }
                                            else{
                                                String validated_gender = "Female";
                                                val.setGender(validated_gender);
                                            }
                                            Object validate_exp_years = t_experience.getSelectedItem();
                                            boolean validated_exp_years = val.setExperience(validate_exp_years);
                                            if(validated_exp_years==true){
                                                String f_staff_first_name = val.getFirstName();
                                                String f_staff_last_name = val.getLastName();
                                                String f_staff_id = val.getStaffId();
                                                String f_staff_email_id = val.getEmailId();
                                                String f_staff_gender = val.getGender();
                                                String f_staff_dob = val.getDob();
                                                int f_staff_experience = val.getExperience();
                                                long f_staff_mobile_number = val.getMobileNum();
                                                String f_staff_password = val.getPassword();
                                                
                                                getConnection getCon = new getConnection();
                                                try{
                                                    Connection conn = getCon.getConnection();
                                                    Statement stmt = conn.createStatement();
                     
                                                    String query="insert into staff_registration values('"+f_staff_first_name+"','"+f_staff_last_name+"','"+f_staff_id+"','"+f_staff_email_id+"','"+f_staff_gender+"','"+f_staff_dob+"',"+f_staff_experience+","+f_staff_mobile_number+",'"+f_staff_password+"')";
                                                    stmt.executeUpdate(query);
                                                    conn.setAutoCommit(true);
                                                    JOptionPane.showMessageDialog(null, "Registrated Successfully!");
                                                }
                                                catch(Exception xe){
                                                     JOptionPane.showMessageDialog(null, "User already exists");
                                                }     
                                                setVisible(false);
                                                StaffLogin loginObj = new StaffLogin();
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Password does not match");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(obj_source==already_registered){
            setVisible(false);
            new StaffLogin();
        }
    }
}
public class StaffDatabaseManagementSystem 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        new StaffRegistration();
            
    }
    
}
