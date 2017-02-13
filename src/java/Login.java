/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mihir
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    
    public static String id ;
    /**
     * Creates a new instance of Login
     */
  
    
    private String ssn;
    private String accountNumber;
    private String loginId;
    private String psw;
    char isLoginFailed = 'N';
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
    
    public String LoginMethod()
    {
        id = loginId;
        
        if(loginId.equals(""))
        {
            return "Login";  
        }
        else 
        {
             //access the database and then login
      
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelm8116";
        //three variables
        LoadDriver();
        
        boolean response = false;
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
               //check the password
                          
               if(psw.equals(rs.getString(3))){
                   //login successful! create an online account
                   
                   OnlineAccount.setAccountNumber(rs.getInt(4));
                   return "Home";
                  
               }
               else {
                   return "Error";
               }
                
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
        
        return "Error";
        
       
    }
    
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