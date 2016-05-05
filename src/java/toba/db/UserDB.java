package toba.db;

import java.sql.*;

import toba.beans.User;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB {
    
    public static int insert(User user) throws SQLException
    {
        // Establish Connection Pool
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO users (FirstName, LastName, Phone, City, Address, Zip, State, Email, Username, Password, Transactions, Salt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try
        {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getCity());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getZip());
            ps.setString(7, user.getState());
            ps.setString(8, user.getEmail());
            ps.setString(9, user.getUsername());
            ps.setString(10, user.getPassword());
            ps.setString(11, "0");
            ps.setString(12, user.getSalt());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        } finally {
            ps.close();
            pool.freeConnection(connection);
        }
    }
    
    public static int update(User user)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE users SET "
                + "FirstName = ?, "
                + "LastName = ?, "
                + "Phone = ?, "
                + "City = ?, "
                + "Address = ?, "
                + "Zip = ?, "
                + "State = ?, "
                + "Username = ?, "
                + "Password = ? "
                + "Transactions = ?"
                + "WHERE Email = ?; ";
       
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getCity());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getZip());
            ps.setString(7, user.getState());
            ps.setString(8, user.getUsername());
            ps.setString(9, user.getPassword());
            ps.setString(10, Integer.toString(user.getTransactionsAmount()));
            ps.setString(11, user.getEmail());
            
            
            
            ps.executeUpdate();
            connection.commit();
            return 1;
        } catch (SQLException ex) {
            
            System.out.println(ex);
            return 0;
        }
        finally {
            pool.freeConnection(connection);
        }

    
    }
    
    public static int delete(User user)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM users "
                + "WHERE Email = ?";
       
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            
            return ps.executeUpdate();
        } catch (SQLException ex) {
            
            System.out.println(ex);
            return 0;
        }
        finally {
            pool.freeConnection(connection);
        }
    }
    
        public static boolean exists(String email)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT Email FROM users "
                + "WHERE Email = ?";
       
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
            return false;
        }
        finally {
            pool.freeConnection(connection);
        }
    }
    
        public static User selectUser(String email)
        {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM users WHERE Email = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next())
            {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setPhone(rs.getString("Phone"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setZip(rs.getString("Zip"));
                user.setAddress(rs.getString("Address"));
                user.setEmail(rs.getString("Email"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }finally {
            pool.freeConnection(connection);
        }
        
      
    }
        
    public void IncrementTransaction(User user)
    {
        user.setTransactionsAmount((user.getTransactionsAmount()+1));
    }
        
       public static User selectUserByUsernameAndPass(String u, String p)
        {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM users WHERE Username = ?";
        
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, u);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next())
            {
                
                if(!rs.getString("Password").equals(p))
                {
                    return null;
                }
                
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setPhone(rs.getString("Phone"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setZip(rs.getString("Zip"));
                user.setAddress(rs.getString("Address"));
                user.setEmail(rs.getString("Email"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }finally {
            pool.freeConnection(connection);
        } 
            
            
        }
       
    

}