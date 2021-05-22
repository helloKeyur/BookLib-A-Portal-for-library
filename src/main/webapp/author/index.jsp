<%@page import="com.booklib.model.Author"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ page import="com.booklib.model.Message" %>
<%@ page import="com.booklib.dao.AuthorDAO" %>
<%@ page import="com.booklib.util.DBConnectionManager" %>

<%
    Message msg = (Message) session.getAttribute("msg");
    String title = "Create Author";
    Author eauthor = new Author();
    boolean isEdit = false;
    int id = 0;
    if (request.getParameter("e") != null && request.getParameter("e").length() != 0) {
        id = Integer.parseInt(request.getParameter("e"));
        try {
            eauthor = new AuthorDAO(DBConnectionManager.getConnection()).getOne(id);
            title = "Edit Author";
            isEdit = true;
        } catch (Exception e) {
            msg = new Message("Something went wrong please try again.", "error", "alert-danger");
        }
    }
    if(request.getParameter("e") != null && eauthor == null){
        isEdit = false;
        msg = new Message("Record is not exist in database.", "error", "alert-danger");
    }
%>
<!doctype html>
<html lang="en">
    <head>
        <%@ include file="../layouts/head.jsp" %>  
        <title>Author BOOKLIB - A portal for library</title>
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
                                    <li class="breadcrumb-item"><a href="#">Authors</a></li>
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
                                <form method="post" action="${pageContext.request.contextPath}/author-servlet/store" enctype="multipart/form-data">
                                    <div class="mb-3">
                                        <input type="text" name="name" class="form-control" id="title" aria-describedby="nameHelp" placeholder="Author Name" aria-label="Author Name">
                                        <small id="nameHelp" class="form-text">*Kindly a book title here.</small>
                                    </div>
                                    <div class="mb-3">
                                        <input type="email" name="email" class="form-control" id="email" placeholder="Email" aria-label="Email">
                                    </div>
                                    <div class="mb-3">
                                        <textarea class="form-control" name="bio" id="bio" aria-describedby="bioHelp" placeholder="About author..." aria-label="About author..."></textarea>
                                        <small id="bioHelp" class="form-text">*Kindly a author bio here.</small>
                                    </div>
                                    <div class="mb-3">
                                        <input class="form-control" type="file" id="profile" name="profile" placeholder="Choose Profile image" title="Choose Profile image">
                                        <img src="/BookLib/upload/profile/default.png" width="32" height="32" class="rounded output mt-3"/>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-sm float-end">CREATE AUTHOR</button>
                                </form>
                                <% }else { %>
                                    <form method="post" action="${pageContext.request.contextPath}/author-servlet/update" enctype="multipart/form-data">
                                        <div class="mb-3">
                                            <input type="hidden" name="id" value="<%= id %>">
                                            <input type="hidden" name="oldFile" value="<%= eauthor.getProfile() %>">
                                            <input type="text" name="name" class="form-control" id="etitle" placeholder="Author Name" aria-label="Author Name" value="<%= eauthor.getName() %>">
                                            <small class="form-text">*Kindly a book title here.</small>
                                        </div>
                                        <div class="mb-3">
                                            <input type="email" name="email" class="form-control" id="eemail" placeholder="Email" aria-label="Email" value="<%= eauthor.getEmail() %>">
                                        </div>
                                        <div class="mb-3">
                                            <textarea class="form-control" name="bio" id="ebio" placeholder="About author..." aria-label="About author..."><%= eauthor.getBio() %></textarea>
                                            <small id="bioHelp" class="form-text">*Kindly a author bio here.</small>
                                        </div>
                                        <div class="mb-3">
                                            <input class="form-control" type="file" id="eprofile" name="profile" placeholder="Choose Profile image" title="Choose Profile image">
                                            <img src="${pageContext.request.contextPath}/upload/profile/<%= eauthor.getProfile() %>" alt="<%= eauthor.getProfile() %>" width="32" height="32" class="rounded output mt-3"/>
                                                        
                                        </div>
                                        <button type="submit" class="btn btn-success btn-sm float-end">UPDATE AUTHOR</button>
                                        <a href="${pageContext.request.contextPath}/author-servlet/index" class="btn btn-sm btn-outline-dark float-end me-2">CANCLE</a>
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
                                            <th scope="col" width="15%">Author</th>
                                            <th scope="col" width="40%">Bio</th>
                                            <th scope="col" width="20%">Created At</th>
                                            <th scope="col" width="20%">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <core:forEach var="author" items="${authors}">
                                            <tr>
                                                <th scope="row">
                                                    ${authors.indexOf(author)+1}
                                                </th>
                                                <td>
                                                    <div class="d-flex text-muted">
                                                        <!-- <img src="https://play-lh.googleusercontent.com/IeNJWoKYx1waOhfWF6TiuSiWBLfqLb18lmZYXSgsH1fvb8v1IYiZr5aYWe0Gxu-pVZX3" width="32" height="32" class="rounded">-->
                                                        <img src="${pageContext.request.contextPath}/upload/profile/${author.getProfile()}" width="32" height="32" class="rounded"/>
                                                        <p class="ps-3 pb-3 mb-0 small lh-sm">
                                                            <strong class="d-block text-gray-dark"><core:out value="${author.name}" /></strong>
                                                            <core:out value="${author.email}" />
                                                        </p>
                                                    </div>
                                                </td>
                                                <td><core:out value="${author.bio}" /></td>
                                                <td>
                                                    <core:out value="${author.createdAt}" />
                                                </td>
                                                <td class="text-center">
                                                    <a href="?e=${author.id}" class="btn btn-sm btn-outline-primary">EDIT</a>

                                                    <form method="post" action="${pageContext.request.contextPath}/author-servlet/destroy" class="d-inline deleteAuthor">
                                                        <input type="hidden" name="author_id" value="${author.id}">
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
                function readURL(input) {
                    console.log(input.files);
                    if(input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $('img.output').attr('src', e.target.result);
                        }
                        reader.readAsDataURL(input.files[0]);
                    }
                }
                $(document).ready(function (e) {
                    $("table").DataTable();
                    $(".deleteAuthor").on("submit", function () {
                        return confirm("Do you want to remove Author?");
                    });
                    
                    $("input[type=file]").on('change',function(){ readURL(this) });
                });
            </script>
        </body>
    </html>