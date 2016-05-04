<%-- 
    Document   : search
    Created on : Mar 4, 2015, 9:33:03 AM
    Author     : sebaslab
--%>

<%@page import="domains.Photo"%>
<%@page import="java.util.ArrayList"%>
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
        <title>GoldTree | Search</title>
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
            <div class="Container">
                <%= (request.getAttribute("message")!=null)?request.getAttribute("message"):"" %>
                <div class="center">
                    <%
                        if(request.getSession(false) != null)
                        {
                            if(request.getSession().getAttribute("photoList") != null)
                            {
                                ArrayList photoList = (ArrayList)request.getSession().getAttribute("photoList");
                                PhotoDisplayHelper pdh = new PhotoDisplayHelper();
                                Photo photo = new Photo();
                                for(int i=0; i<photoList.size();i++)
                                {
                                    photo = (Photo) photoList.get(i);
                                    out.write(pdh.displayPhotoThumbnail(photo));
                                }
                            }
                        }
                    %>
                </div>
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
