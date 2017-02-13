/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mihir
 */
public class OnlineAccount 
{
    
  
    private static int ssn;
    private static int accountNumber;
    private static String loginId;
    private static String password;
    private static double amount; 
    
 
    public static int getSsn() {
        return ssn;
    }

    public static void setSsn(int ssn) {
        OnlineAccount.ssn = ssn;
    }

    public static int getAccountNumber() {
        return accountNumber;
    }

    public static void setAccountNumber(int accountNumber) {
        OnlineAccount.accountNumber = accountNumber;
    }

    public static String getLoginId() {
        return loginId;
    }

    public static void setLoginId(String loginId) {
        OnlineAccount.loginId = loginId;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        OnlineAccount.password = password;
    }

    public static double getAmount() {
        return amount;
    }

    public static void setAmount(double amount) {
        OnlineAccount.amount = amount;
    }
    
}
