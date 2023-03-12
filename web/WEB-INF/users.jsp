<%-- 
    Document   : users
    Created on : Mar 6, 2023, 5:11:09 PM
    Author     : nuket
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <c:if test="${users==null}">
            <h3>No users</h3>
        </c:if>
        <c:if test="${users!=null}">
            <table>
                <tr>
                    <th>Email</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Role</th>
                    <th> </th>
                    <th> </th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.role.name}</td>
                        <td><a href="UserServlet/?action=edit">Edit</a></td>
                        <td><a href="UserServlet/?action=delete">Delete</a></td>
                    </tr>
                </c:forEach>
                
                
            </table>
        </c:if>
        <c:if test="${form.equals('add')}">  
            <h1>Add User</h1>
            <form >
                <input type="text" name="email" value="${email}">
                <input type="text" name="first" value="${first}">
                <input type="text" name="last" value="${last}">
                <input type="password" name="password" value="${password}">
                <select name="role">
                    <option value="admin">System Admin</option>
                    <option value="regular">Regular User</option>
                </select>  
                <input type="submit" action="UserServlet" method="post" value="add">
            </form>
        </c:if>
       <c:if test="${form.equals('edit')}">  
            <h1>Add User</h1>
            <form >
                <span>${email}</span>
                <input type="text" name="first" value="${first}" required><br>
                <input type="text" name="last" value="${last}" required><br>
                <input type="password" name="password" value="${password}" required><br>
                <select name="role" required>
                    <option value="admin">System Admin</option>
                    <option value="regular">Regular User</option>
                </select><br>
                <input type="submit" action="UserServlet" value="update" method="post">
                <input type="button" onClick="parent.location='UserServlet\'" value="Cancel"
            </form>
        </c:if>
        
    </body>
</html>
