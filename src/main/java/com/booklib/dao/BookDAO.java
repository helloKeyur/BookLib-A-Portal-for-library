package com.booklib.dao;

import com.booklib.model.Author;
import com.booklib.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    //static constant variable queries
    private static final String SELECT_BOOK_WITH_AUTHOR_BY_ID = "SELECT book.*, author.* FROM `booklib_books` AS book JOIN `booklib_authors` AS author ON book.author_id = author.id WHERE book.book_id=?";
    private static final String SELECT_ALL_BOOKS_WITH_AUTHOR = "SELECT book.*, author.* FROM `booklib_books` AS book JOIN `booklib_authors` AS author ON book.author_id = author.id";
    private static final String INSERT_BOOK_SQL = "INSERT INTO `booklib_books`(`title`, `description`, `price`, `author_id`) VALUES (?,?,?,?)";
    private static final String UPDATE_BOOK_SQL = "UPDATE `booklib_books` SET `title`=?, `description`=?, `price`=?, `author_id`=? WHERE `book_id`=?";
    private static final String DESTROY_BOOK_SQL = "DELETE FROM `booklib_books` WHERE `book_id`=?";

    private Connection con;
    
    public BookDAO(Connection con){
        this.con = con;
    }
    
    /**
     * @return bool
     * @param Book book
     */
    public boolean store(Book book){
        boolean status = false;
        try(PreparedStatement ps = con.prepareStatement(INSERT_BOOK_SQL)){
            ps.setString(1,book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setString(3, book.getPrice());
            ps.setInt(4, book.getAuthor_id());
            ps.executeUpdate();
            status = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    
    /**
     * @return bool
     * @param Book book
     */
    public boolean update(Book book){
        boolean status = false;
        try(PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_SQL)){
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setString(3, book.getPrice());
            ps.setInt(4, book.getAuthor_id());
            ps.setInt(5, book.getBook_id());
            ps.executeUpdate();
            status = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    
    /**
     * @return Book book
     * @param int id
     */
    public Book getOne(int id){
        Book book = null;
        try{
            PreparedStatement ps = con.prepareStatement(SELECT_BOOK_WITH_AUTHOR_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                book = new Book();
                book.setBook_id(id);
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setPrice(rs.getString("price"));
                book.setAuthor_id(rs.getInt("author_id"));
                book.setCreatedAt(rs.getTimestamp("created_at"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return book;
    }
    
    /**
     * @return List<Book> listOfAuthor
     */
    public List<Book> getAll(){
        List<Book> listOfAuthor = new ArrayList<>();
        Book book = null;
        Author author = null;
        try{
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOKS_WITH_AUTHOR);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setBio(rs.getString("bio"));
                author.setEmail(rs.getString("email"));
                author.setProfile(rs.getString("profile"));
                
                book = new Book();
                book.setBook_id(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setPrice(rs.getString("price"));
                book.setAuthor_id(rs.getInt("author_id"));
                book.setCreatedAt(rs.getTimestamp("created_at"));
                book.setAuthor(author);
                listOfAuthor.add(book);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listOfAuthor;
    }
    
    /**
     * @return bool
     * @param int id
     */
    public boolean destroy(int id){
        boolean status = false;
        try(PreparedStatement ps = con.prepareStatement(DESTROY_BOOK_SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
            status = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
