/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Mihir
 */
@Named(value = "myOrders")
@ManagedBean
@Dependent
public class myOrders implements Serializable {

    /**
     * Creates a new instance of myOrders
     */
     public ArrayList<OrdersDetails> myorder(){
         
         ArrayList<OrdersDetails> array1 = new ArrayList<>();
         
        int accountNumber = OnlineAccount.getAccountNumber();
        
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
         
            rs = stat.executeQuery("Select * from r_orders where accountNumber = '"
                    + accountNumber + "'");
            
             while(rs.next())
            {
                array1.add(new OrdersDetails(rs.getString(2),rs.getDouble(2),rs.getDouble(3)));
            }
            
            return array1; 
            
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
           
             return null;
            
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
