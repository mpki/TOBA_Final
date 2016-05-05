/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import toba.db.ConnectionPool;

/**
 *
 * @author Matt
 */
public class ReportsServlet extends HttpServlet {

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
        
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Table");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("The User Table");
        
        try
        {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users ORDER BY ID";
            ResultSet results = statement.executeQuery(query);
            
            int i = 2;
            while (results.next())
            {
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(results.getInt("ID"));
                row.createCell(1).setCellValue(results.getInt("Username"));
                row.createCell(2).setCellValue(results.getInt("FirstName"));
                row.createCell(3).setCellValue(results.getInt("LastName"));
                row.createCell(4).setCellValue(results.getInt("Phone"));
                row.createCell(5).setCellValue(results.getInt("City"));
                row.createCell(6).setCellValue(results.getInt("Address"));
                row.createCell(7).setCellValue(results.getInt("Zip"));
                row.createCell(8).setCellValue(results.getInt("State"));
                row.createCell(9).setCellValue(results.getInt("Email"));
                row.createCell(10).setCellValue(results.getInt("Transactions"));
                row.createCell(11).setCellValue(results.getInt("Password"));
                row.createCell(12).setCellValue(results.getInt("Salt"));
                i++;
            }
            results.close();
            statement.close();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReportsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.setHeader("content-disposition", "attachment; filename=users.xls");
        response.setHeader("cache-control", "no-cache");
        
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
