package com.booklib.servlet.book;

import com.booklib.dao.BookDAO;
import com.booklib.model.Message;
import com.booklib.util.DBConnectionManager;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookDestroyServle", urlPatterns = {"/book-servlet/destroy"})
public class DestroyServle extends HttpServlet {
    
    
    private BookDAO bookDAO;
    
    public void init() {
    	this.bookDAO = new BookDAO(DBConnectionManager.getConnection());
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
            destroy(request,response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * @param HttpServletRequest, HttpServletResponse
     * @redirect to author/index.jsp
	 *
     */
    private void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("book_id"));
        HttpSession session = request.getSession();
        
        bookDAO.destroy(id);
        Message msg = new Message("Book removed Successfully.", "success", "alert-success");
        session.setAttribute("msg", msg);
        response.sendRedirect(request.getContextPath() + "/book-servlet/index");
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
