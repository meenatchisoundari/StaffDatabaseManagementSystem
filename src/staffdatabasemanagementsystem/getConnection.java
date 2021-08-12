/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staffdatabasemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ELCOT
 */
public class getConnection {
    
        private static Connection con;
        public static Connection getConnection(){
            try{
               Class.forName("oracle.jdbc.driver.OracleDriver");
               con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","xe","xe");
            }
            catch(Exception e){
                con = (Connection) e;
            }
            return con;
        }
}
