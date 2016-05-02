/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import toba.beans.User;
import toba.db.Account;
import toba.db.AccountDB;
import toba.db.Transaction;
import toba.db.TransactionDB;
import toba.db.UserDB;

/**
 *
 * @author Matt
 */
@WebServlet(name = "TransactionServlet", urlPatterns = {"/TransactionServlet"})
public class TransactionServlet extends HttpServlet {

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
            User user = (User) request.getSession().getAttribute("user");
            List<Account> accounts = AccountDB.SelectAccounts(user.getUsername());
            Account SavingsAccount = accounts.get(0);
            Account CheckingAccount = accounts.get(1);
            System.out.println(accounts.get(0).getSavings());
            System.out.println(accounts.get(1).getChecking());
            String destination = request.getParameterValues("destination")[0];
            String Amount = request.getParameter("amount");
            float amt = Float.parseFloat(Amount);
            System.out.println(amt);
            System.out.println(destination);
            if(destination.equalsIgnoreCase("savings"))
            {
                System.out.println("Transferring to Savings Account");
                    if(amt>CheckingAccount.getBalance())
                    {
                        System.out.println("Invalid amount");
                    out.println("<script type=\"text/javascript\">");  
                    out.println("alert('Invalid Amount! Transfer amount exceeds your Savings Account Balance!');");
                    out.println("window.location=\'TransferSystem.jsp';");
                    out.println("</script>");
                    }
                    else
                    {
                        System.out.println("Amount is valid! Attempting Transfer");
                        SavingsAccount.Credit(amt);
                        CheckingAccount.Debit(amt);
                        System.out.println("BALANCE IN SAVINGS: "+SavingsAccount.getBalance());
                        System.out.println("BALANCE IN CHECKING: "+CheckingAccount.getBalance());
                        AccountDB.update(SavingsAccount, 1);
                        AccountDB.update(CheckingAccount, 0);
                        user.IncrementTransaction();
                        UserDB.update(user);
                        request.getSession().setAttribute("user", user);
                        request.getSession().setAttribute("savings", SavingsAccount);
                        request.getSession().setAttribute("checking", CheckingAccount);
                        
                        Transaction t = new Transaction(user.getUsername(), "Checking", "Savings", amt);
                    try {
                        TransactionDB.insert(t);
                    } catch (SQLException ex) {
                        Logger.getLogger(TransactionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                        response.setHeader("Location", "Account_activity.jsp"); 
                    
                    }
            }
            else
            {
                System.out.println("Transferring to Checking Account");
                    if(amt>SavingsAccount.getBalance())
                    {
                        System.out.println("Invalid amount");
                    out.println("<script type=\"text/javascript\">");  
                    out.println("alert('Invalid Amount! Transfer amount exceeds your Checking Account Balance!');");
                    out.println("window.location=\'TransferSystem.jsp';");
                    out.println("</script>");
                    }
                    else
                    {
                        System.out.println("Amount is valid! Attempting Transfer");
                        SavingsAccount.Debit(amt);
                        CheckingAccount.Credit(amt);
                        System.out.println("BALANCE IN SAVINGS: "+SavingsAccount.getBalance());
                        System.out.println("BALANCE IN CHECKING: "+CheckingAccount.getBalance());
                        AccountDB.update(SavingsAccount, 1);
                        AccountDB.update(CheckingAccount, 0);
                        user.IncrementTransaction();
                        UserDB.update(user);
                        request.getSession().setAttribute("user", user);
                        request.getSession().setAttribute("savings", SavingsAccount);
                        request.getSession().setAttribute("checking", CheckingAccount);
                        Transaction t = new Transaction(user.getUsername(), "Savings", "Checking", amt);
                    try {
                        TransactionDB.insert(t);
                    } catch (SQLException ex) {
                        Logger.getLogger(TransactionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                        response.setHeader("Location", "Account_activity.jsp"); 
                    }
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
