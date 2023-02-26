/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mitchell
 */
public class ShoppingListServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShoppingListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
                   HttpSession session = request.getSession(false);
        
                           
        String logout = request.getParameter("logout");
        if (logout != null) {
              session.invalidate();
              request.setAttribute("message", "You have successfully logged out.");
              getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);
            }
                   
        if(session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            
            // show list page with welcome message
            session.setAttribute("welcomeMessage", "Welcome back, " + username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
               .forward(request, response);
        } else {
            // session does not exist or username is not set, forward to registration page
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
               .forward(request, response);
        }

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
        String username = request.getParameter("username");
        if(username != null && !username.isEmpty()) {
            
                HttpSession session = request.getSession();

                // store the username in a session variable
                session.setAttribute("username", username);
                session.setAttribute("welcomeMessage", "Welcome, " + username);
                // redirect to home 
                        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);
            }
    else {
        // username or password is empty, display an error message and forward to login.jsp
        request.setAttribute("errorMessage", "Please enter username");
        request.setAttribute("username", username);
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);
    }
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
