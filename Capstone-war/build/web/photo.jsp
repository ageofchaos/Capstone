<%-- 
    Document   : photo
    Created on : 29-Mar-2015, 2:50:49 PM
    Author     : sebaslab
--%>

<%@page import="core.PhotoFunctions"%>
<%@page import="pagehelpers.PhotoDisplayHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>GoldTree | Photo</title>
        
        <script type="text/javascript">
            $(document).ready(function(){
                $.adaptiveBackground.run({
                  normalizeTextColor: true
                });
            });
        </script>
        <%
            String count = request.getParameter("photo");
            if(count != null && !count.equals(""))
            {
                PhotoFunctions pf = new PhotoFunctions();
                pf.incrementCount(Integer.parseInt(count));
            }
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
            <div class="Container2">
            <div class="Content">
            
            <div class="center">
                <%
                    String photo = request.getParameter("photo");
                    if(photo != null && !photo.equals(""))
                    {
                        if(Character.isDigit(photo.charAt(0)))
                        {
                            int photoId = Integer.parseInt(photo);
                            PhotoDisplayHelper pdh = new PhotoDisplayHelper();
                            out.print(pdh.displayFullPhoto(photoId));
                        }
                    }
                %>
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
