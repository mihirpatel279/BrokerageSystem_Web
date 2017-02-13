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
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Mihir
 */
@Named(value = "signUp")
@SessionScoped
public class SignUp implements Serializable {

    /**
     * Creates a new instance of SignUp
     */
        /**
     * Creates a new instance of SignUp
     */
    public SignUp() {
    }
    
    
    
    private String ssn;
    private String accountNumber;
    private String loginId;
    private String password;
    private double amount;
    private String q1 = "";
    private String q2 = "";
    private String a1 = "";
    private String a2 = "";

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
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
    

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    
    public String SignUpMethod()
    {
        //account
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelm8116";
        //three variables
         LoadDriver();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        
        try{
            
            conn = DriverManager.getConnection(DB_URL, "patelm8116" ,"1460202");
            stat = conn.createStatement();
         
            rs = stat.executeQuery("Select * from user_details where LoginId = '"
                    + loginId + "' or Ssn = '" + ssn + "'");
            
             if(rs.next()){
                
                return  "Error";
                
            }
             else {
                 
                //to get the account number 
                rs = stat.executeQuery("Select * from nextAccountNumber");  //normal select 
                int nextNum = 0;
                while(rs.next())
            {
                //get next account number
               accountNumber = "" + rs.getInt(1); //number of columns
               nextNum = rs.getInt(1) + 1;  
            }
            //update next account number
            
            int t = stat.executeUpdate("Update nextAccountNumber set "
                    + "nextID =  '" + nextNum + "'"); //
                 
             
             
             
             //inset a record into bankAccount table
        int r = stat.executeUpdate("insert into user_details values ('"
                    + loginId + "','  " + ssn + " ', '" + password + "', '" + accountNumber + "','" 
                    + amount + "','" 
                    + q1 + "','" + a1 + "','" + q2 + "','" + a2 + "','" + "N" + "') ");
            
                            return "Home";
            
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
    
        
        return "SignUp";
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
