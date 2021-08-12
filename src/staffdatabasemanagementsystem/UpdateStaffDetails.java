/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staffdatabasemanagementsystem;

import java.awt.BorderLayout;
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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ELCOT
 */
public class UpdateStaffDetails extends JFrame implements ActionListener{
    private Container content;
    private JLabel staff_first_name,staff_last_name,top_title,email_id,gender,dob,mobile_num,experience,change_pass,reg_title;
    private JTextField t_staff_first_name,t_staff_last_name,t_email_id,t_dob,t_mobile_num,t_pass;
    private JComboBox t_experience;
    private JRadioButton male,female;
    private ButtonGroup gendergp;
    private JButton save_changes;
    private String staff_id_con;
    
    private String exp_years[]
        = { " ","0","1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30"};
    
    public UpdateStaffDetails(String staff_id_to_upd){
            this.staff_id_con = staff_id_to_upd;
            this.setSize(500, 580);
            Toolkit tk = this.getToolkit();
            Dimension dim = tk.getScreenSize();
            int x = (int) dim.getWidth() / 2 - this.getWidth() / 2;
            int y = (int) dim.getHeight() / 2 - this.getHeight() / 2;
            this.setLocation(x, y);
           
            setTitle("Staff Registration");
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
            Color c = new Color(255, 150, 50, 100);
            getContentPane().setBackground(c);
            
            Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ELCOT\\Documents\\NetBeansProjects\\StaffDatabaseManagementSystem\\build\\classes\\staffdatabasemanagementsystem\\logo.png");  
            setIconImage(icon);
            
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(true);
            
            content = getContentPane();
            content.setLayout(null);
            
            reg_title = new JLabel("SRI KRISHNA COLLEGE OF TECHNOLOGY");
            reg_title.setSize(600,55);
            reg_title.setLocation(350,20);
            reg_title.setFont(new Font("Serif", Font.BOLD, 28));  
            content.add(reg_title);
            
            top_title = new JLabel("UPDATE DETAILS");
            top_title.setSize(200,55);
            top_title.setLocation(570,80);
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
            
            
            email_id = new JLabel("EMAIL ID:");
            email_id.setSize(200,20);
            email_id.setLocation(500, 230);
            email_id.setFont(new Font("Serif", Font.PLAIN, 14));
            content.add(email_id);
            
            t_email_id = new JTextField();
            t_email_id.setSize(140,25);
            t_email_id.setLocation(650, 230);
            content.add(t_email_id);
            
            gender = new JLabel("GENDER:");
            gender.setFont(new Font("Serif", Font.PLAIN, 14));
            gender.setSize(100, 20);
            gender.setLocation(500, 270);
            content.add(gender);
            
            male = new JRadioButton("Male");
            male.setFont(new Font("Serif", Font.PLAIN, 14));
            male.setSelected(true);
            male.setSize(65, 20);
            male.setLocation(650, 270);
            content.add(male);

            female = new JRadioButton("Female");
            female.setFont(new Font("Serif", Font.PLAIN, 14));
            female.setSelected(false);
            female.setSize(75, 20);
            female.setLocation(715, 270);
            content.add(female);

            gendergp = new ButtonGroup();
            gendergp.add(male);
            gendergp.add(female);
            
            dob = new JLabel("DATE OF BIRTH:");
            dob.setSize(200,20);
            dob.setLocation(500, 310);
            dob.setFont(new Font("Serif", Font.PLAIN, 14));
            content.add(dob);
            
            t_dob = new JTextField();
            t_dob.setSize(140,25);
            t_dob.setLocation(650, 310);
            content.add(t_dob);
        
            experience = new JLabel("EXPERIENCE:");
            experience.setFont(new Font("Serif", Font.PLAIN, 14));
            experience.setSize(100, 20);
            experience.setLocation(500, 350);
            content.add(experience);
            
            
            t_experience = new JComboBox(exp_years);
            t_experience.setFont(new Font("Arial", Font.PLAIN, 14));
            t_experience.setSize(130, 20);
            t_experience.setLocation(650, 350);
            content.add(t_experience);
            
            mobile_num = new JLabel("MOBILE NUMBER:");
            mobile_num.setSize(200,20);
            mobile_num.setLocation(500, 390);
            mobile_num.setFont(new Font("Serif", Font.PLAIN, 14));
            content.add(mobile_num);
            
            t_mobile_num = new JTextField();
            t_mobile_num.setSize(140,25);
            t_mobile_num.setLocation(650, 390);
            content.add(t_mobile_num);
            
            change_pass = new JLabel("CHANGE PASSWORD:");
            change_pass.setFont(new Font("Serif", Font.PLAIN, 14));
            change_pass.setSize(140, 20);
            change_pass.setLocation(500, 430);
            content.add(change_pass);
            
            t_pass = new JTextField();
            t_pass.setSize(140, 25);
            t_pass.setLocation(650, 430);
            content.add(t_pass);
            
            save_changes = new JButton("SAVE");
            save_changes.setFont(new Font("Serif", Font.PLAIN, 15));
            save_changes.setSize(90, 20);
            save_changes.setLocation(600, 500);
            save_changes.addActionListener(this);
            content.add(save_changes);
            
            try{

                getConnection getConn = new getConnection();
                Connection conn = getConn.getConnection();

                Statement st = conn.createStatement();

                String sql = "select * from staff_registration where staff_id='"+staff_id_to_upd+"'";

                ResultSet rs = st.executeQuery(sql);

                int rowsNumber = 0;
                while(rs.next()){
                    rowsNumber++;
                }
                rs = st.executeQuery(sql);
                //System.out.println("row"+rowsNumber);
                String[][] data = new String[rowsNumber][8];
                int row_count = 0;
                while(rs.next()){
                    int i=0;

                    t_staff_first_name.setText( rs.getString("FIRST_NAME")); 
                    t_staff_last_name.setText(rs.getString("LAST_NAME"));
                    t_email_id.setText(rs.getString("EMAIL_ID"));
                    String gender_in_table = rs.getString("GENDER");

                    if(gender_in_table.equals("Female")){
                        male.setSelected(false);
                        female.setSelected(true);
                    }
                    t_dob.setText(rs.getString("DOB")); 

                    String sel_exp = rs.getString("EXPERIENCE");
                    t_experience.setSelectedItem(sel_exp);

                    t_mobile_num.setText(rs.getString("MOBILE_NUMBER"));
                    t_pass.setText(rs.getString("PASSWORD"));

                }
                conn.close();
                setVisible(true);
            }
            catch(Exception xe){
               JOptionPane.showMessageDialog(null,xe);
            } 
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object obj_source=e.getSource();
        if(obj_source==save_changes){
            StaffValidation check = new StaffValidation();
            
            String f_name_staff = t_staff_first_name.getText().toString();      
            boolean validated_f_name = check.setFirstName(f_name_staff);
            if(validated_f_name==true){
                
                String l_name_staff = t_staff_last_name.getText().toString();   
                boolean validated_l_name = check.setLastName(l_name_staff); 
                
                if(validated_l_name==true){
                        
                    String val_email_id = t_email_id.getText().toString();  
                    boolean validated_email_id = check.setEmailId(val_email_id);
                        
                    if(validated_email_id==true){
                        String val_dob = t_dob.getText().toString();
                        boolean validated_dob = check.setDob(val_dob);
                            
                        if(validated_dob==true){
                            String val_mobile_num = t_mobile_num.getText().toString();
                            boolean validated_mobile_num = check.setMobileNum(val_mobile_num);
                                 
                            if(validated_mobile_num==true){
                                    
                                String val_password = t_pass.getText().toString();
                                boolean validated_password = check.setPassword(val_password);
                                if(validated_password==true){
                                    
                                    if (male.isSelected()){
                                        String validated_gender = "Male";
                                        check.setGender(validated_gender);
                                    }
                                    else{
                                        String validated_gender = "Female";
                                        check.setGender(validated_gender);
                                    }
                                    Object validate_exp_years = t_experience.getSelectedItem();
                                    boolean validated_exp_years = check.setExperience(validate_exp_years);
                                    
                                    String f_staff_first_name = check.getFirstName();
                                    String f_staff_last_name = check.getLastName();
                                    String f_staff_email_id = check.getEmailId();
                                    String f_staff_gender = check.getGender();
                                    String f_staff_dob = check.getDob();
                                    int f_staff_experience = check.getExperience();
                                    long f_staff_mobile_number = check.getMobileNum();
                                    String f_staff_password = check.getPassword();
                                    
                                    //System.out.println(f_staff_first_name+" "+f_staff_last_name+" "+f_staff_email_id+" "+f_staff_gender+" "+f_staff_dob+" "+f_staff_experience+" "+f_staff_password);
                               
                                    
                                    try{
                                        getConnection getconnect = new getConnection();
                                        Connection conn = getconnect.getConnection();
                                        Statement stmt = conn.createStatement();

                                        String query="update staff_registration set first_name='"+f_staff_first_name+"'"+",last_name='"+f_staff_last_name+"',email_id='"+f_staff_email_id+"',gender='"+f_staff_gender+"',dob='"+f_staff_dob+"',experience="+f_staff_experience+",mobile_number="+f_staff_mobile_number+",password='"+f_staff_password+"' where staff_id='"+staff_id_con+"'";
                                        //System.out.println(query);
                                        stmt.executeUpdate(query);
                                        conn.setAutoCommit(true);
                                        JOptionPane.showMessageDialog(null, "Changes saved!");
                                        setVisible(false);
                                        new GetChoice();
                                    }
                                    catch(Exception xe){
                                         JOptionPane.showMessageDialog(null,xe);
                                    }     
                                }
                            }
                        }
                    }
                }
            }
        }
    }
        
        

    UpdateStaffDetails() {
        
    }
    public void passStaffid(String id){
        new UpdateStaffDetails(id);
    }
    
}
