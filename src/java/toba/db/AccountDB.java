/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.db;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import toba.beans.User;
import toba.db.Account;


/**
 *
 * @author Matt
 */
public class AccountDB {
    
     public static int insert(Account account) throws SQLException
    {
        // Establish Connection Pool
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO account (Balance, Savings, Checking, Username) "
                + "VALUES (?, ?, ?, ?)";
        
        try
        {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, account.getBalance().toString());
            ps.setString(2, Integer.toString(account.getSavings()));
            ps.setString(3, Integer.toString(account.getChecking()));
            ps.setString(4, account.getUsername());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        } finally {
            ps.close();
            pool.freeConnection(connection);
            return 0;
        }
    }

    public static int update(Account account, int Type)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE account SET "
                + "Balance = ?, "
                + "Savings = ?, "
                + "Checking = ? "
                + "WHERE Username = ? AND Savings = ?;";
       
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, Float.toString(account.getBalance()));
            ps.setString(2, Integer.toString(account.getSavings()));
            ps.setString(3, Integer.toString(account.getChecking()));
            ps.setString(4, account.getUsername());
            ps.setString(5, Integer.toString(Type));

            
            
            
            ps.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            
            System.out.println(ex);
            return 0;
        }
        finally {
            pool.freeConnection(connection);
        }

    
    }

    
    public static List<Account> SelectAccounts(String username)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM account WHERE Username = ?";
        List<Account> accounts = new ArrayList<Account>();
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            Account account = null;
            while (rs.next())
            {
                
                account = new Account();
                account.setBalance(rs.getFloat("Balance"));
                account.setChecking(rs.getInt("Checking"));
                account.setSavings(rs.getInt("Savings"));
                account.setUsername(rs.getString("Username"));
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }finally {
            pool.freeConnection(connection);
        } 
            
            
    }
    

    
    public List<Account> SelectAllAccounts()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM account";
        List<Account> accounts = new ArrayList<Account>();
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next())
            {
                Account account = new Account();
                String Username = rs.getString("Username");
                float Balance = rs.getFloat("Balance");
                int Checking = rs.getInt("Checking");
                int Savings = rs.getInt("Savings");
                
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }finally {
            pool.freeConnection(connection);
        } 
            
            
    }
    
    
    
}
