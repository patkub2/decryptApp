/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.kubala.patryk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.kubala.patryk.model.IncorrectTextException;

/**
 * Servlet that the web app forwards to when the user wants to show history of
 * action
 *
 * @author Patryk Kubala
 * @version 5.0
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class History extends HttpServlet {

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
        PrintWriter output = response.getWriter();

       

        
        // make a connection to DB
        try ( Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/cypher", "app", "app")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Data");
            // Przeglądamy otrzymane wyniki
           
            output.println("<html>\n<table><tr><th>ID</th><th>text</th><th>decodedtext</th><th>operation</th><th>seed</th></tr>");
            output.println("-----------------------------------");
            while (rs.next()) {
                output.println(
                        "<tr>\n"
                        + "    <td>" + rs.getInt("id") + "</td>\n"
                        + "    <td>" + rs.getString("text") + "</td>\n"
                        + "    <td>" + rs.getString("decodedtext") + "</td>\n"
                        + "    <td>" + rs.getString("operation") + "</td>\n"
                        + "    <td>" + rs.getInt("seed") + "</td>\n"
                        + "  </tr>\n"
                );
            }
             output.println(
                        "</table></html>"
                );
            output.println("-----------------------------------");

            rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
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
