/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String action = request.getParameter("action");
int count = 0;

        if (action != null && action.equals("logout")) {
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }

        if (session != null && username != null) {
            // show list page with welcome message
            session.setAttribute("welcomeMessage", "Welcome back, " + username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else {
            // session does not exist or username is not set, forward to registration page
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        if (items == null) {
            items = new ArrayList<String>();
            session.setAttribute("items", items);
            count = 0;
            //session.setAttribute("count", count);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else {
            count = items.size();
            
        }
       session.setAttribute("count", count);
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
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String action = request.getParameter("action");

        if (action != null &&  action.equals("register")) {
            if (username != null && !username.isEmpty()) {
                // store the username in a session variable
                session.setAttribute("username", username);
                session.setAttribute("welcomeMessage", "Welcome, " + username);
                // forward
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                        .forward(request, response);
            } else {
                // username is empty, display an error message and forward to login.jsp
                request.setAttribute("errorMessage", "Please enter username");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
            }

        } else if (action != null && action.equals("addItem")) {
            String addItem = request.getParameter("addItem");
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
            if (addItem != null && !addItem.isEmpty()) {
                
                items.add(addItem);
                session.setAttribute("items", items);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                        .forward(request, response);
            }
            else{
                request.setAttribute("errorMessage", "Please add an item");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                        .forward(request, response);
            }
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
