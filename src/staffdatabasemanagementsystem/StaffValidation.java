/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staffdatabasemanagementsystem;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author ELCOT
 */
public class StaffValidation {
    
    private String FirstName,LastName,StaffId,EmailId,Dob,Password,Gender;
    private long Mobile_num;
    private int Experience;
    
    public boolean setFirstName(String first_name){
        String name = first_name.replaceAll("\\s","");
        String regex_f_name = "^[A-Za-z]{3,30}+$";
        Pattern p = Pattern.compile(regex_f_name);
        Matcher m_f_name = p.matcher(name);
        boolean f_name_match = m_f_name.matches();
        if(f_name_match==true){
            this.FirstName = first_name;
            return f_name_match;
        }
        else if(first_name.length()<3){
            JOptionPane.showMessageDialog(null, "First name should be minimum 3 characters long");
        }
        else{
            Pattern pattern_f_name_check = Pattern.compile("[^a-zA-Z]");
            Matcher matcher_f_name = pattern_f_name_check.matcher(first_name);
            boolean spl_char_check = matcher_f_name.find();
            if(spl_char_check==true){
                JOptionPane.showMessageDialog(null, "Special characters and numbers not allowed in name");
            }
        }
        return false;
    }
    
    public String getFirstName(){
        return this.FirstName;
    }
    
    
    public boolean setLastName(String last_name){
        String regex_l_name = "^[A-Za-z]{1,30}+$";
        Pattern p = Pattern.compile(regex_l_name);
        Matcher m_l_name = p.matcher(last_name);
        boolean l_name_match = m_l_name.matches();
        if(l_name_match==true){
            this.LastName = last_name;
            return l_name_match;
        }
        else if(last_name.length()<1){
            JOptionPane.showMessageDialog(null, "Last name should be minimum 1 character");
        }
        else{
            Pattern pattern_l_name_check = Pattern.compile("[^a-zA-Z]");
            Matcher matcher_l_name = pattern_l_name_check.matcher(last_name);
            boolean spl_char_check = matcher_l_name.find();
            if(spl_char_check==true){
                JOptionPane.showMessageDialog(null, "Special characters and numbers not allowed in name");
            }
        }
        return false;
    }
    public String getLastName(){
        return this.LastName;
    }
    
    
    public boolean setStaffId(String check_staff_id){
        String regex_staff_id = "^[A-Z]{3}+[0-9]{4}";
        Pattern for_staff_id = Pattern.compile(regex_staff_id);
        Matcher m_staff_id = for_staff_id.matcher(check_staff_id);
        boolean staff_id_match = m_staff_id.matches();
        if(staff_id_match==true){
            this.StaffId = check_staff_id;
            return staff_id_match;
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Staff Id");
        }
        return false;
    }
    public String getStaffId(){
        return this.StaffId;
    }
    
    
    public boolean setEmailId(String check_email_id){
        String regex_email_id = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern for_email_id = Pattern.compile(regex_email_id);
        Matcher m_email_id = for_email_id.matcher(check_email_id);
        boolean email_id_match = m_email_id.matches();
        if(email_id_match==true){
            this.EmailId = check_email_id;
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid mail");
        }
        return false;
    }
    public String getEmailId(){
        return this.EmailId;
    }
    
    
    public boolean setDob(String check_dob){
        
        boolean dob_match = false;
        if(check_dob.charAt(2)=='.'){
            String PATTERN="^[0-9]{0,2}[.][0-9]{0,2}[.][0-9]{0,4}$";
            Pattern patt=Pattern.compile(PATTERN);
            Matcher match=patt.matcher(check_dob);
            if(!match.matches()){
                JOptionPane.showMessageDialog(null, "Invalid date of birth");
            }
            else{
                this.Dob = check_dob.replace(".","-");
                dob_match = true;
            }
        }
        else if(check_dob.charAt(2)=='-'){
            String PATTERN="^[0-9]{0,2}[-][0-9]{0,2}[-][0-9]{0,4}$";
            Pattern patt=Pattern.compile(PATTERN);
            Matcher match=patt.matcher(check_dob);
            if(!match.matches()){
                JOptionPane.showMessageDialog(null, "Invalid date of birth");
            }
            else{
                this.Dob = check_dob;
                dob_match =true;
            }
        }
        else if(check_dob.charAt(2)=='/'){
            String PATTERN="^[0-9]{0,2}[/][0-9]{0,2}[/][0-9]{0,4}$";
            Pattern patt=Pattern.compile(PATTERN);
            Matcher match=patt.matcher(check_dob);
            if(!match.matches()){
                JOptionPane.showMessageDialog(null, "Invalid date of birth");
            }
            else{
                this.Dob = check_dob.replace("/","-");
                dob_match = true;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid date of birth");
        }
        String dates[] = Dob.split("-");
        int current_year = Calendar.getInstance().get(Calendar.YEAR);
        if(Integer.parseInt(dates[0])>31 || Integer.parseInt(dates[1])>12 || Integer.parseInt(dates[2])>current_year ){
            dob_match = false;
            JOptionPane.showMessageDialog(null, "Invalid date of birth");
        }
        
        return dob_match;
 
    }
    
    public String getDob(){
        return this.Dob;
    }
    
    
    public void setGender(String staff_gender){
          this.Gender = staff_gender;
    }
    public String getGender(){
        return this.Gender;
    }
    
    
    public boolean setExperience(Object e_years){
        boolean check_exp = false;
        String ex_year = (String)e_years;
        if(ex_year.equals(" ")){
            JOptionPane.showMessageDialog(null, "Select experience");
        }
        else{
            check_exp = true;
            this.Experience = Integer.parseInt(ex_year);
        }
        return check_exp;
    }
    public int getExperience(){
        return this.Experience;
    }
    
    
    public boolean setMobileNum(String check_mobile_num){
        boolean mob_match = false;
        String PATTERN ="^[6-9]{1}[0-9]{9}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(check_mobile_num);
        if(!match.matches()){
            JOptionPane.showMessageDialog(null, "Invalid mobile number");
        }
        else{
            mob_match = true;
            this.Mobile_num = Long.parseLong(check_mobile_num);
        }
        return mob_match;
    }
    
    public long getMobileNum(){
        return this.Mobile_num;
    }
    
    
    public boolean setPassword(String password){
        boolean password_match = false;
        String PATTERN ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#.?!@$%^&*-]).{8,}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(password);
        if(!match.matches()){
            JOptionPane.showMessageDialog(null, "Password must contain atleast one uppercase,lowercase,special character and digit");
        }
        else{
            password_match = true;
            this.Password = password;
        }
        return password_match;
    }
    public String getPassword(){
        return this.Password;
    }
    
}
