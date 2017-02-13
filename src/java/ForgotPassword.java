/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mihir
 */
@Named(value = "forgotPassword")
@RequestScoped
public class ForgotPassword {

    /**
     * Creates a new instance of ForgotPassword
     */
   
     public static String userId ;
    private String loginId;
   

   
    

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String PasswordRetrieval()
    {
       
        userId = loginId;
        //access the database and then login
      
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelm8116";
        //three variables
        LoadDriver();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        
        try{
            //connect to the Database
            
            conn = DriverManager.getConnection(DB_URL , "patelm8116" , "1460202");
            
            stat = conn.createStatement();
            
           //do a query
           rs = stat.executeQuery("Select * from user_details where LoginId = '"
                   + loginId + "'");
           if(rs.next()){
               
               
            
              return "PasswordRetrieval" ;
            
          
           }
           else {
               return "Error";
           }
                
        }
       catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            
            try{
                rs.close();
                stat.close();
                conn.close();
            }
            catch(Exception e){
                
                e.printStackTrace();
            }
           
            
        }
        return "ForgotPsw";
        
    }
    
    
     public static void LoadDriver()
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
        }
    }

    
}
