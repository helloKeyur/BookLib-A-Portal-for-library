package com.booklib.servlet.author;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.booklib.dao.AuthorDAO;
import com.booklib.model.Author;
import java.util.List;
import com.booklib.util.DBConnectionManager;
/**
 *
 * @author Keyur
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/author-servlet/index"})
public class IndexServlet extends HttpServlet {
    
    private AuthorDAO authorDAO;
    
    public void init() {
    	this.authorDAO = new AuthorDAO(DBConnectionManager.getConnection());
    }
    
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
        try{
            index(request,response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * @param HttpServletRequest, HttpServletResponse
     * @redirect to author/index.jsp with authors data.
     */
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors = authorDAO.getAll();
        request.setAttribute("authors", authors);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("../author/index.jsp");
        dispatcher.forward(request, response);
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
