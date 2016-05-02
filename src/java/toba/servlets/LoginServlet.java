package toba.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toba.beans.User;
import toba.db.Account;
import static toba.db.Account.AccountType.CHECKING;
import static toba.db.Account.AccountType.SAVINGS;
import toba.db.AccountDB;

/**
 *
 * @author Matt
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String AttemptUser = request.getParameter("Username");
        String AttemptPass = request.getParameter("Password");
        boolean login = false;
        String query, User, Pass;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/toba?zeroDateTimeBehavior=convertToNull", "root", "sesame");
            Statement stmt = (Statement) con.createStatement();
            query = "SELECT * FROM users;";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();

            while(rs.next())
            {
                System.out.println("DEBUG: \n\n\n\n\n");
                
                User = rs.getString("Username");
                System.out.println(User);
                Pass = rs.getString("Password");
                System.out.println(Pass);
                
                    
                /* Params
                String username, String password, String firstName, String lastName, 
            String phone, String city, String address, String zip, String state, String email
                */
                if(AttemptUser.equals(User) && AttemptPass.equals(Pass))
                {
                    System.out.println("Username and Pass match! Attempting to Assign Values to Current Session Scope");
                    String Firstname = rs.getString("FirstName");
                    System.out.println(Firstname);
                    String Lastname = rs.getString("LastName");
                    System.out.println(Lastname);
                    String Phone = rs.getString("Phone");
                    System.out.println(Phone);
                    String City = rs.getString("City");
                    System.out.println(City);
                    String Address = rs.getString("Address");
                    System.out.println(Address);
                    String Zip = rs.getString("Zip");
                    System.out.println(Zip);
                    String State = rs.getString("State");
                    System.out.println(State);
                    String Email = rs.getString("Email");
                    System.out.println(Email);
                    User UReload = new User(User, Pass, Firstname, Lastname, Phone,
                    City, Address, Zip, State, Email);
                    
                    System.out.println("Attempting to Retrieve Accounts attached to User");                 
                        List<Account> accounts = AccountDB.SelectAccounts(UReload.getUsername());
                        Account SavingsAccount = accounts.get(0);
                        Account CheckingAccount = accounts.get(1);
                        request.getSession().setAttribute("savings", SavingsAccount);
                        request.getSession().setAttribute("checking", CheckingAccount);


                    

                    request.getSession().setAttribute("user", UReload);
                    
                    System.out.println("Logged in Successfully!");
                    
                    login = true;
                    
                }
            }
        

        }   catch (ClassNotFoundException ex) 
            {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) 
            {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(login)
        {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "Account_activity.jsp"); 
        }
        else
        {
            System.out.println("Login Attempt Failed");
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "Login_failure.jsp"); 
        }
        
    }
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}