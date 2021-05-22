package com.booklib.servlet.author;

import com.booklib.dao.AuthorDAO;
import com.booklib.model.Author;
import com.booklib.model.Message;
import com.booklib.util.DBConnectionManager;
import com.booklib.util.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "StoreServlet", urlPatterns = {"/author-servlet/store"})
@MultipartConfig
public class StoreServlet extends HttpServlet {
    
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
        try {
            store(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * @param HttpServletRequest, HttpServletResponse
     * @redirect to author/index.jsp
	 *
     */
    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        String bio = request.getParameter("bio");
        String email = request.getParameter("email");
        String profile = "default.png";

        /*save file image data*/
        Part part = request.getPart("profile");
        if(part.getSize()>0){
            profile = Helper.getRandomString() + ".png";
            String path = getServletContext().getRealPath("/upload/profile") + File.separator + profile;
            Helper.saveFile(part, path);
        }
        /*end of save file image data*/

        HttpSession session = request.getSession();
        Author author = new Author(name, bio, email, profile);
        authorDAO.store(author);
        Message msg = new Message("New Author created succefully.", "success", "alert-success");
        session.setAttribute("msg", msg);
        response.sendRedirect(request.getContextPath() + "/author-servlet/index");
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
