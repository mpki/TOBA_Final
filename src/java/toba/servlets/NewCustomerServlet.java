/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import toba.beans.User;
import toba.db.UserDB;
import toba.db.Account;
import toba.db.Account.AccountType;
import static toba.db.Account.AccountType.CHECKING;
import static toba.db.Account.AccountType.SAVINGS;
import toba.db.AccountDB;

/**
 *
 * @author Matt
 */
public class NewCustomerServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String Firstname = request.getParameter("Firstname");
            String Lastname = request.getParameter("Lastname");
            String Phone = request.getParameter("Phone");
            String Address = request.getParameter("Address");
            String City = request.getParameter("City");
            String State = request.getParameter("State");
            String Zipcode = request.getParameter("Zipcode");
            String Email = request.getParameter("Email");
            
            if(Firstname.equals("")||Lastname.equals("")||Phone.equals("")||Address.equals("")||City.equals("")||Zipcode.equals("")||Email.equals(""))
            {
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('Please fill out all of the Fields in order to Create your Account!');");
                out.println("window.location=\'New_customer.html';");
                out.println("</script>");
            }
            else
            {
                String Username = Lastname + Zipcode;
                String Password = "welcome1";
                /* Params
                String username, String password, String firstName, String lastName, 
            String phone, String city, String address, String zip, String state, String email
                */
                User new_user = new User(Username, Password, Firstname, Lastname, Phone, City, Address, Zipcode, State, Email);
                try {
                    Account SavingsAccount = new Account(new_user, (float) 25.0);
                    SavingsAccount.setAccount(SAVINGS);
                    SavingsAccount.setUsername(new_user.getUsername());
                    SavingsAccount.setSavings(1);
                    request.getSession().setAttribute("savings", SavingsAccount);
                    Account CheckingAccount = new Account(new_user, (float) 0.0);
                    CheckingAccount.setAccount(CHECKING);
                    CheckingAccount.setUsername(new_user.getUsername());
                    CheckingAccount.setChecking(1);
                    request.getSession().setAttribute("checking", CheckingAccount);
                    AccountDB.insert(SavingsAccount);
                    AccountDB.insert(CheckingAccount);
                    new_user.ConnectAccounts(SavingsAccount, CheckingAccount);
                    request.getSession().setAttribute("user", new_user);
                    UserDB.insert(new_user);
                } catch (SQLException ex) {
                    Logger.getLogger(NewCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.sendRedirect("Success.jsp");
            }
        
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
