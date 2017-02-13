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
import java.util.Scanner;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mihir
 */
@Named(value = "passwordRetrieval")
@RequestScoped
public class PasswordRetrieval{

    /**
     * Creates a new instance of PasswordRetrieval
     */
    public PasswordRetrieval() {
    }
    
    private static String accountnumber;

    private String question  = GetQuestion();
    private String answer;
    private String password ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  

    public static String getAccountnumber() {
        return accountnumber;
    }

    public static void setAccountnumber(String accountnumber) {
        PasswordRetrieval.accountnumber = accountnumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
    
    
    public String Submit()
    {
       
        //access the database and then login
      
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelm8116";
        //three variables
        
          LoadDriver();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String loginId = ForgotPassword.userId;
        
        
        try{
            //connect to the Database
            
            conn = DriverManager.getConnection(DB_URL , "patelm8116" , "1460202");
            
            stat = conn.createStatement();
            
           //do a query
           rs = stat.executeQuery("Select * from user_details where LoginId = '"
                   + loginId + "'");
           if(rs.next()){
               
               if(question.equals(rs.getString(6)))
               {
                   if(answer.equals(rs.getString(7)))
                   {
                       this.setPassword(rs.getString(3));
                       return "PasswordRetrieval";
                   }
                   else 
                   {
                       return "Error";
                   }
               }
               
               else if(question.equals(rs.getString(8)))
               {
                    if(answer.equals(rs.getString(9)))
                   {
                        this.setPassword(rs.getString(3));
                        return "PasswordRetrieval";
                   }
                    else 
                   {
                       return "Error";
                   }
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
        return "ForgotPsw"; 
        
    }
    
   
    
     public String GetQuestion()
    {
        
      
        
        
        //access the database and then login
      
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelm8116";
        //three variables
        
          LoadDriver();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String loginId = ForgotPassword.userId;
        
        
        try{
            //connect to the Database
            
            conn = DriverManager.getConnection(DB_URL , "patelm8116" , "1460202");
            
            stat = conn.createStatement();
            
           //do a query
           rs = stat.executeQuery("Select * from user_details where LoginId = '"
                   + loginId + "'");
           if(rs.next()){
               
               
               Random rand = new Random();  
               int randNum = rand.nextInt(3);
           
               
               
               
            if(randNum == 1){
                
                question =  rs.getString(6);
                return question;
                
            }   
            else {
               question =  rs.getString(8);
                return question;
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

