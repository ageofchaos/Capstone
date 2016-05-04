<%-- 
    Document   : transaction
    Created on : Apr 9, 2015, 3:02:36 PM
    Author     : CPRG352
--%>

<%@page import="pagehelpers.ShoppingCartPageHelper"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Integer userid = 0;
    try{
        userid = (Integer) session.getAttribute("userId"); // if this causes a null pointer exception, it means the user is not logged in
    }
    catch(NullPointerException e)
    {
        response.sendRedirect("index.jsp?message=You are not logged in");
    }
    ShoppingCartPageHelper scph = new ShoppingCartPageHelper();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/mainStyle.css">
        <link rel="stylesheet" type="text/css" href="styles/testcss.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script type="text/javascript" src="script/navScript.js"></script>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Goldtree | Transaction History</title>
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
            <div class="Container2">
            <div class="Content">
            <% 
                if(request.getParameter("message")!=null)
                    out.print(request.getParameter("message")); 
            %>
            
            <div class="Container">
        <h1>Transaction History</h1>
        <%= scph.getTransactionHistory(userid) %>
        </div>
            </div>
            </div>
            <div class="footer">
                <div class="links"> </div>
                <div class="copyrite"> </div>
            </div>
        </div>
    </body>
</html>
