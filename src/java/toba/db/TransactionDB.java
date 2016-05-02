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
public class TransactionDB {
    
     public static int insert(Transaction transaction) throws SQLException
    {
        // Establish Connection Pool
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO transactions (Username, Transfer, Destination, Source, Date) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try
        {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, transaction.getUsername());
            ps.setString(2, Float.toString(transaction.getTransfer()));
            ps.setString(3, transaction.getDestination());
            ps.setString(4, transaction.getSource());
            if(transaction.getDate()==null)
            {
                transaction.updateTime();
            }
            ps.setString(5, transaction.getDate());
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
    
    public static List<Transaction> ListTransactions(String username)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM transactions WHERE Username = ?";
        List<Transaction> transactions = new ArrayList<Transaction>();
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next())
            {
                
                Transaction transaction = new Transaction();
                transaction.setUsername(rs.getString("Username"));
                transaction.setSource(rs.getString("Source"));
                transaction.setDate(rs.getString("Date"));
                transaction.setTransfer(rs.getFloat("Transfer"));
                transaction.setDestination(rs.getString("Destination"));
                transactions.add(transaction);
            }
            return transactions;
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
