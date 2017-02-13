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
import java.util.ArrayList;
import java.util.Scanner;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mihir
 */
@Named(value = "sell")
@RequestScoped
public class Sell {

    /**
     * Creates a new instance of Buy
     */
    public Sell() {
    }
    
    
    private String stockCode;
    private double sellPrice;
    private int quantity;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

   

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
   
    public String SellMethod()
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
         
            rs = stat.executeQuery("select * from r_orders where accountNumber = '"
                                    + OnlineAccount.getAccountNumber() + "'");
            ArrayList<String> sr = new ArrayList<>();
            
           while(rs.next())
            {
                sr.add(rs.getString(1));
            }
           if(sr.size()>0){
               
                
                ResultSet rs1 = null;
                
                rs1 = stat.executeQuery("select scount from r_orders where accountNumber = '" 
                        + OnlineAccount.getAccountNumber() + "'" +"and stockCode = '" + stockCode + "'");
               
                 
                 
                 double bprice = 0.0;
                 rs = stat.executeQuery("Select * from pending_orders where stockCode = '"
                   + stockCode + "' and accountNumber <> '" + OnlineAccount.getAccountNumber() + "'"
                   + "and bprice >= '" + bprice + "'"
                   + "and rownum = '1'");
                 
           if(rs.next()){
            int scount = rs.getInt(6);
            if(quantity >= scount){
                
                int r = stat.executeUpdate("update r_orders set scount = quantity - '" 
                        + scount +"' where stockCode = '"
                   + stockCode + "' and accountNumber <> '" + OnlineAccount.getAccountNumber() + "'"
                   + "and sprice >= '" + bprice + "'"
                   + "and rownum = '1'");
               
                int v = stat.executeUpdate("delete from r_orders where scount = '0'");
                return "Done";
            }
            else{
                int currents = scount - quantity;
                
                int z =  stat.executeUpdate("update pending_orders set scount = scount - '" 
                        + quantity +"' where stockCode = '"
                   + stockCode + "' and accountNumber <> '" + OnlineAccount.getAccountNumber() + "'"
                   + "and sprice <= '" + bprice + "'"
                   + "and rownum = '1'");
                return "Done";
      
                
            }
            
               
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
           
            return "Done";
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
