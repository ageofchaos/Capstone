<%-- 
    Document   : register
    Created on : Feb 4, 2015, 10:58:05 AM
    Author     : 633630
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
        <title>GoldTree | Register</title>
        <%
            if(session.getAttribute("username") != null)
                response.sendRedirect("index.jsp?message=You are already signed in");
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
            <div class="center">
                <% 
                    if(request.getParameter("message")!=null)
                        out.print(request.getParameter("message")); 
                %>
                <div class="registerContainer">
                    <form action="MemberOperations" method="POST" onsubmit="return checkRegisterPassword();">
                    <div class="registerUpgrade">
                        <img src="images/LotusTest.jpg" style="height: 500px; width: 500px; overflow: hidden; filter: grayscale(100%); float: left; position: absolute; z-index: 0; ">
                        <div class="upgradeHeader">
                            <h1>Become an Upgraded Member</h1>
                        </div>
                        <div class="upgradeInner">
                            <div class="upgradedInnerText">
                                Upgraded members are able to sell their own photos, using their own licensing terms, after paying a small fee.<br/><br/>
                                <div class="upgradeCheckBox">
                                    <input type="checkbox" name="type" value="u">Become an Upgraded Member<br/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="register">
                        <div class="descHeader registerHeader">
                            <h1>Register</h1>
                        </div>
                        <div class="registerText">
                            <b>Register an account to be able to purchase beautiful and high quality images.</b>
                        </div>
                        <input required type="text" name="firstname" class="prettytextbox" placeholder="First Name">
                        <input required type="text" name="lastname" class="prettytextbox" placeholder="Last Name">
                        <input required type="email" name="username" class="prettytextbox" placeholder="Email">
                        <input required type="password" name="password" class="prettytextbox" placeholder="Password" oninput="style.backgroundColor='white'" id="registerpassword">
                        <input type="password" name="confirmPassword" class="prettytextbox" placeholder="Confirm Password" oninput="checkRegisterPassword();" id="registerconfirm">
                        <input type="submit" value="register" name="register" class="registerButton" id="submitbutton">
                        <b id="registerMessage" style="display: none"><%= (request.getParameter("message")!=null)?request.getParameter("message"):"" %></b>
                    </div>
                    </form>
                </div> 
            </div>
            </div>
            <div class="footer">
                <div class="links"></div>
                <div class="copyrite"></div>
            </div>
        </div>
    </body>
</html>
