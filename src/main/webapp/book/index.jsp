<%@page import="java.util.List"%>
<%@page import="com.booklib.dao.AuthorDAO"%>
<%@page import="com.booklib.model.Author"%>
<%@page import="com.booklib.model.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ page import="com.booklib.model.Message" %>
<%@ page import="com.booklib.dao.BookDAO" %>
<%@ page import="com.booklib.util.DBConnectionManager" %>

<%
    Message msg = (Message) session.getAttribute("msg");
    String title = "Create Book";
    List<Author> authors = new AuthorDAO(DBConnectionManager.getConnection()).getAll();
    
    Book ebook = new Book();
    boolean isEdit = false;
    int id = 0;
    if (request.getParameter("e") != null && request.getParameter("e").length() != 0) {
        id = Integer.parseInt(request.getParameter("e"));
        try {
            ebook = new BookDAO(DBConnectionManager.getConnection()).getOne(id);
            title = "Edit Book";
            isEdit = true;
        } catch (Exception e) {
            msg = new Message("Something went wrong please try again.", "error", "alert-danger");
        }
    }
    if(request.getParameter("e") != null && ebook == null){
        isEdit = false;
        msg = new Message("Record is not exist in database.", "error", "alert-danger");
    }
%>
<!doctype html>
<html lang="en">
    <head>
        <%@ include file="../layouts/head.jsp" %>  
        <title>Books BOOKLIB - A portal for library</title>
        <style>
            .dataTables_paginate,.dataTables_filter {
                float: right!important;
            }
        </style>
    </head>
    <body>
        <!-- navbar -->  
        <jsp:include page="/layouts/navbar.jsp"/>
        <!-- end navbar -->

        <!-- main container -->
        <div class="container-fluid py-4 bg-light">
            <div class="row g-x3 mb-3">
                <div class="col-md-12">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb mb-0">
                                    <li class="breadcrumb-item"><a href="#">Booklib</a></li>
                                    <li class="breadcrumb-item"><a href="#">Books</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">List</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row g-x3">
                <div class="col-sm-6 col-md-3">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title mb-3">
                                <%= title%>
                            </h5>
                            <%
                                if(isEdit == false){
                            %>
                                <form method="post" action="${pageContext.request.contextPath}/book-servlet/store">
                                    <div class="mb-3">
                                        <input type="text" name="title" class="form-control" placeholder="Book Title">
                                        <small class="form-text">*Kindly a book title here.</small>
                                    </div>
                                    <div class="mb-3">
                                        <input type="number" name="price" class="form-control" placeholder="Price">
                                    </div>
                                    <div class="mb-3">
                                        <textarea class="form-control" name="description" placeholder="Book Description..." aria-label="About author..."></textarea>
                                        <small id="bioHelp" class="form-text">*Kindly a book description here.</small>
                                    </div>
                                    <div class="mb-3">
                                        <select name="author_id" class="form-control">
                                            <core:forEach var="author" items="<%= authors %>">
                                                <option value="${author.id}">${author.name}</option>
                                            </core:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-sm float-end">CREATE BOOK</button>
                                </form>
                                <% }else { %>
                                    <form method="post" action="${pageContext.request.contextPath}/book-servlet/update">
                                        <div class="mb-3">
                                            <input type="hidden" name="book_id" value="<%= id %>">
                                            <input type="text" name="title" class="form-control" placeholder="Book Title" value="<%= ebook.getTitle() %>">
                                            <small class="form-text">*Kindly a book title here.</small>
                                        </div>
                                        <div class="mb-3">
                                            <input type="number" name="price" class="form-control" placeholder="Price" value="<%= ebook.getPrice() %>">
                                        </div>
                                        <div class="mb-3">
                                            <textarea class="form-control" name="description" placeholder="Book Description..."><%= ebook.getDescription() %></textarea>
                                            <small id="bioHelp" class="form-text">*Kindly a book description here.</small>
                                        </div>
                                        <div class="mb-3">
                                            <select name="author_id" class="form-control">
                                            <%
                                                for(Author author : authors){
                                                    if(author.getId() == ebook.getAuthor_id()){
                                                        out.print("<option selected value="+author.getId()+">"+author.getName()+"</option>");
                                                    }else{
                                                        out.print("<option value="+author.getId()+">"+author.getName()+"</option>");
                                                    }
                                                }
                                            %>
                                        </select>         
                                        </div>
                                        <button type="submit" class="btn btn-success btn-sm float-end">UPDATE BOOK</button>
                                        <a href="${pageContext.request.contextPath}/book-servlet/index" class="btn btn-sm btn-outline-dark float-end me-2">CANCLE</a>
                                    </form>
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <div class="col-6 col-md-9">
                        <div class="card shadow-sm">
                            <div class="card-body">
                                <!-- Alert Message -->
                                <%
                                    if (msg != null) {
                                %>
                                <div class="alert <%= msg.getCssClass()%> alert-dismissible fade show" role="alert">
                                    <%= msg.getMsg()%>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                                <%
                                        session.removeAttribute("msg");
                                    }
                                %>
                                <!-- End of Alert Message -->
                                <table class="table table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th scope="col" width="5%">#</th>
                                            <th scope="col" width="30%">Book Details</th>
                                            <th scope="col" width="10%">Price</th>
                                            <th scope="col" width="15%">Author</th>
                                            <th scope="col" width="20%">Created At</th>
                                            <th scope="col" width="20%">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <core:forEach var="book" items="${books}">
                                            <tr>
                                                <th scope="row">
                                                    ${books.indexOf(book)+1}
                                                </th>
                                                <td>
                                                    <strong><core:out value="${book.title}" /></strong><br>
                                                    <small><core:out value="${book.description}" /></small>
                                                </td>
                                                <td><core:out value="${book.price}" /></td>
                                                <td>
                                                    <div class="d-flex text-muted">
                                                        <img src="${pageContext.request.contextPath}/upload/profile/${book.author.getProfile()}" width="32" height="32" class="rounded"/>
                                                        <p class="ps-3 pb-3 mb-0 small lh-sm">
                                                            <strong class="d-block text-gray-dark"><core:out value="${book.author.getName()}" /></strong>
                                                            <core:out value="${book.author.getEmail()}" />
                                                        </p>
                                                    </div>
                                                </td>
                                                <td>
                                                    <core:out value="${book.createdAt}" />
                                                </td>
                                                <td class="text-center">
                                                    <a href="?e=${book.book_id}" class="btn btn-sm btn-outline-primary">EDIT</a>

                                                    <form method="post" action="${pageContext.request.contextPath}/book-servlet/destroy" class="d-inline deleteBook">
                                                        <input type="hidden" name="book_id" value="${book.book_id}">
                                                        <button type="submit" class="btn btn-sm btn-outline-danger">REMOVE</button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </core:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end main container -->

            <!-- footer scripts -->
            <%@ include file="../layouts/footer_js.jsp" %>
            <!-- end footer scripts -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
            <script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap5.min.js"></script>
            <script>
                $(document).ready(function (e) {
                    $("table").DataTable();
                    $(".deleteBook").on("submit", function () {
                        return confirm("Do you want to remove Book?");
                    });
                });
            </script>
        </body>
    </html>