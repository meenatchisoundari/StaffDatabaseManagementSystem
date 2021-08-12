package staffdatabasemanagementsystem;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ELCOT
 */


public class ViewStaffDetails  implements WindowListener{
    JFrame frame;
    JLabel lblHeading ;
    boolean to_open_choice=false;
    public boolean ViewDetails(){
        try{
                
            getConnection getConn = new getConnection();
            Connection conn = getConn.getConnection();

            Statement st = conn.createStatement();

            String sql = "select * from staff_registration";

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
            
                  data[row_count][i++] = rs.getString("FIRST_NAME");
                  data[row_count][i++] =  rs.getString("LAST_NAME");
                  data[row_count][i++] = rs.getString("STAFF_ID");
                  data[row_count][i++] = rs.getString("EMAIL_ID");
                  data[row_count][i++] = rs.getString("GENDER");
                  data[row_count][i++] = rs.getString("DOB");
                  data[row_count][i++] = rs.getString("EXPERIENCE");
                  data[row_count++][i++] =  rs.getString("MOBILE_NUMBER");
                 
          }
          conn.close();
            frame=new JFrame();
            Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ELCOT\\Documents\\NetBeansProjects\\StaffDatabaseManagementSystem\\build\\classes\\staffdatabasemanagementsystem\\logo.png");  
            frame.setIconImage(icon);
            
            String column[] = {"First Name","Last Name","Staff ID","Email ID","Gender","DOB","Experience","Mobile number"};
            JTable table = new JTable(data, column);
            
            Color c = new Color(255, 150, 50, 100);
            frame.getContentPane().setBackground(c);
            
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

            lblHeading = new JLabel("STAFF DETAILS");
            lblHeading.setFont(new Font("Serif",Font.BOLD,20));
             
 
            frame.getContentPane().setLayout(new BorderLayout());

            frame.getContentPane().add(lblHeading,BorderLayout.NORTH);
            frame.getContentPane().add(scrollPane,BorderLayout.CENTER);

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(1000,700);
            frame.setResizable(true);
            frame.setVisible(true);
//          for(int j=0;j<rowsNumber;j++){
//            for(int k=0;k<8;k++){
//                System.out.print(data[j][k]+" ");
//            }
//            System.out.println();
//          }
        }
        catch(Exception xe){
            JOptionPane.showMessageDialog(null,xe);
        }
        return to_open_choice;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}