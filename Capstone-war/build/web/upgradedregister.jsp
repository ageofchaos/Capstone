<%-- 
    Document   : upgradedregister
    Created on : 4-Apr-2015, 9:48:27 AM
    Author     : sebaslab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--%import-->
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/mainStyle.css">
        <link rel="stylesheet" type="text/css" href="styles/testcss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script type="text/javascript" src="script/navScript.js"></script>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="script/registerScript.js"></script>
        <title>GoldTree | Upgraded Register</title>
        <%
            if(session.getAttribute("username") == null)
                response.sendRedirect("index.jsp?message=You are not signed in");
        %>
        
    </head>
    <body>
        <div class="header">
            <a href="index.jsp"><div class="logo"></div></a>
            <c:import url="Nav"/>
            <div class="searchContainer">
                <div class="search">
                    <form action="SearchOperation" method="POST">
                        <input required type="text" name="searchValue" class="prettytextboxsearch" placeholder="Search">
                        <input type="submit" value="Search" name="searchButton" class="searchButton" id="submitbutton">
                    </form>
                </div> 
            </div>
        </div>
        
        <div class="layer1">
            <div class="Content">
                <% 
                    if(request.getParameter("message")!=null)
                        out.print(request.getParameter("message")); 
                %>
                <form action="MemberOperations" method="POST">
                    <input required type="text" name="paypalInfo" class="prettytextbox" placeholder="PayPal Info">
                    <input type="submit" value="Submit" name="paypal" class="registerButton" id="submitbutton">
                </form>
            </div>
            <div class="footer">
                <div class="links"></div>
                <div class="copyrite"></div>
            </div>
        </div>
    </body>
</html>
