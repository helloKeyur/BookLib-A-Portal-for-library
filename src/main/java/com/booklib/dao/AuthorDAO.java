package com.booklib.dao;

import java.sql.Connection;
import java.sql.SQLException;
import com.booklib.model.Author;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    
    //static constant variable queries
    private static final String SELECT_AUTHOR_BY_ID = "SELECT * FROM `booklib_authors` WHERE `id`=?";
    private static final String SELECT_ALL_AUTHORS = "SELECT * FROM `booklib_authors`";
    private static final String INSERT_AUTHOR_SQL = "INSERT INTO `booklib_authors`(`name`, `bio`, `email`, `profile`) VALUES (?,?,?,?)";
    private static final String UPDATE_AUTHOR_SQL = "UPDATE `booklib_authors` SET `name`=?, `bio`=?, `email`=?, `profile`=? WHERE `id`=?";
    private static final String DESTROY_AUTHOR_SQL = "DELETE FROM `booklib_authors` WHERE `id`=?";

    private Connection con;
    
    public AuthorDAO(Connection con){
        this.con = con;
    }
    
    /**
     * @return bool
     * @param Author author
     */
    public boolean store(Author author){
        boolean status = false;
        try(PreparedStatement ps = con.prepareStatement(INSERT_AUTHOR_SQL)){
            ps.setString(1,author.getName());
            ps.setString(2, author.getBio());
            ps.setString(3, author.getEmail());
            ps.setString(4, author.getProfile());
            ps.executeUpdate();
            status = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    
    /**
     * @return bool
     * @param Author author
     */
    public boolean update(Author author){
        boolean status = false;
        try(PreparedStatement ps = con.prepareStatement(UPDATE_AUTHOR_SQL)){
            ps.setString(1, author.getName());
            ps.setString(2, author.getBio());
            ps.setString(3, author.getEmail());
            ps.setString(4, author.getProfile());
            ps.setInt(5, author.getId());
            ps.executeUpdate();
            status = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    
    /**
     * @return Author author
     * @param int id
     */
    public Author getOne(int id){
        Author author = null;
        try{
            PreparedStatement ps = con.prepareStatement(SELECT_AUTHOR_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                author = new Author();
                author.setId(id);
                author.setName(rs.getString("name"));
                author.setBio(rs.getString("bio"));
                author.setEmail(rs.getString("email"));
                author.setProfile(rs.getString("profile"));
                author.setCreatedAt(rs.getTimestamp("created_at"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return author;
    }
    
    /**
     * @return List<Author> listOfAuthor
     */
    public List<Author> getAll(){
        List<Author> listOfAuthor = new ArrayList<>();
        Author author = null;
        try{
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_AUTHORS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setBio(rs.getString("bio"));
                author.setEmail(rs.getString("email"));
                author.setProfile(rs.getString("profile"));
                author.setCreatedAt(rs.getTimestamp("created_at"));
                listOfAuthor.add(author);
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
        try(PreparedStatement ps = con.prepareStatement(DESTROY_AUTHOR_SQL)){
            ps.setInt(1, id);
            ps.executeUpdate();
            status = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
