package pl.polsl.kubala.patryk.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Main class of the servlet that demonstrates parameter download given during
 * servlet initialization
 *
 * @author Patryk Kubala
 * @version 1.0
 */
@WebServlet("/Form")
public class FormServlet extends HttpServlet {

    /**
     * Collection of statistics
     */
    private final HashMap<String, String> stats;

    /**
     * Constructor initiating statistics collection
     */
    public FormServlet() {
        this.stats = new HashMap<>();
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        
        // Get parameter values - firstName i lastName
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        // FirstName or lastName was not given - send error message
        if (firstName.length() == 0 || lastName.length() == 0) {
            response.sendError(response.SC_BAD_REQUEST, "You should give two parameters!");
        } else {
            out.println("<html>\n<body>\n<h1>Hello " + firstName + " "
                    + lastName + "!!!</h1>\n");

            // How many times the person with given lastName visited the site
            String counter = stats.get(firstName + lastName);

            int value = 0;

            // First visit
            if (counter == null) {
                out.println("You are the first person with name " + firstName + " " + lastName
                        + " who visited this site!!!\n");
            } else {
                value = Integer.parseInt(counter) + 1;
                out.println("There are already " + value + " people with the last name " + lastName
                        + " who visited this site!!!\n");
            }
            out.println("</body>\n</html>");

            // Save current visit count
            stats.put(firstName + lastName, "" + value);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        out.println("Passed parameters:");

        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            out.println(name + " = " + request.getParameter(name));
        }
    }
}
