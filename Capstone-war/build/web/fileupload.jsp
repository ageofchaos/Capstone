<%-- 
    Document   : fileupload
    Created on : Feb 11, 2015, 9:26:19 AM
    Author     : 633630
--%>

<%@page import="core.PhotoFunctions"%>
<%@page import="core.MemberFunctions"%>
<%@page import="domains.Member"%>
<%@page import="domains.Photo"%>
<%@page import="pagehelpers.PhotoDisplayHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
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
        <title>GoldTree | File Upload</title>
        <%
            String artist = request.getParameter("artist");
            if(artist == null || artist.equals(""))
                response.sendRedirect("index.jsp?message=No artist selected");
            else
            {
                MemberFunctions mf = new MemberFunctions();
                Member owner = mf.getMemberInfo(artist);
                if(owner == null)
                    response.sendRedirect("index.jsp?message=This artist doesnt exist");
                else
                {
                    session.setAttribute("artistName", (owner.getFirstName() +" "+ owner.getLastName()));
                    session.setAttribute("artistId", ""+owner.getIdMember());
                    session.setAttribute("artistUsername", owner.getEmail());
                    String[] styles = owner.getPageStyle().split(",");
                    session.setAttribute("hiddenbgc", styles[0]);
                    session.setAttribute("hiddenft", styles[1]);
                    session.setAttribute("hiddenfc", styles[2]);
                    session.setAttribute("hiddenbgp", styles[3]);
                }
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
        
               
        <div class="Content">    
            <div class="center">
                <h1><%= session.getAttribute("artistName")%>'s Gallery</h1>
                <% 
                    if(request.getParameter("message")!=null)
                        out.print(request.getParameter("message")); 
                %>  
            </div>
                <div class="Container2">
                    <div class="Container">
                    <% 
                        if(session.getAttribute("artistId") != null)
                        {
                            ArrayList photoList = new ArrayList();
                            String username = (String)session.getAttribute("username");
                            PhotoFunctions pf = new PhotoFunctions();
                            photoList = pf.searchPhotoMember(Integer.parseInt((String)session.getAttribute("artistId")));
                            if(photoList != null)
                            {
                                PhotoDisplayHelper pdh = new PhotoDisplayHelper();
                                Photo photo = new Photo();
                                for(int i=0; i<photoList.size();i++)
                                {
                                    photo = (Photo) photoList.get(i);
                                    if(username.equals(artist))
                                        out.write(pdh.displayPhotoThumbnailUpgraded(photo, (String)session.getAttribute("username")));
                                    else
                                        out.write(pdh.displayPhotoThumbnail(photo));
                                }
                            }
                        }
                    %>
                </div>
                </div>
                    <script>
                var hiddenbgc;
                function bgc1()
                {
                    document.body.style.backgroundColor = "#d9c092";
                    hiddenbgc = 1;
                }
                function bgc2()
                {
                    document.body.style.backgroundColor = "#c08a62";
                    hiddenbgc = 2;
                }
                function bgc3()
                {
                    document.body.style.backgroundColor = "#f1dfa1";
                    hiddenbgc = 3;
                }
                function bgc4()
                {
                    document.body.style.backgroundColor = "#d5b8ec";
                    hiddenbgc = 4;
                }
                function bgc5()
                {
                    document.body.style.backgroundColor = "#4b3458";
                    hiddenbgc = 5;
                }
                function bgc6()
                {
                    document.body.style.backgroundColor ="#4a4a4a";
                    hiddenbgc = 6;
                }


            </script>

            <script>
                var hiddenbgp;
                function bgp1()
                {
                    document.body.style.backgroundImage = "url('images/goldTreeBack.png')";
                    hiddenbgp = 1;
                }
                function bgp2()
                {
                    document.body.style.backgroundImage = "url('images/Back1.png')";
                    hiddenbgp = 2;
                }
                function bgp3()
                {
                    document.body.style.backgroundImage = "url('images/back2.png')";
                    hiddenbgp = 3;
                }



            </script>
            <script>
                var hiddenft;
                function ft1()
                {
                    document.body.style.fontFamily = "Raleway";
                    hiddenft = 1;
                }
                function ft2()
                {
                    document.body.style.fontFamily = "Roboto";
                    hiddenft = 2;
                }
                function ft3()
                {
                    document.body.style.fontFamily = "Playfair Display";
                    hiddenft = 3;
                }
                function ft4()
                {
                    document.body.style.fontFamily = "Dancing Script";
                    hiddenft = 4;
                }
                function ft5()
                {
                    document.body.style.fontFamily = "Philosopher";
                    hiddenft = 5;
                }
                function saveAll()
                {
                    window.location.replace("StyleOperations?hiddenbgc="+hiddenbgc+"&hiddenft="+hiddenft+"&hiddenfc="+hiddenfc+"&hiddenbgp="+hiddenbgp);
                }

            </script>
            <script>
                var hiddenfc;
                function fc1()
                {
                    document.body.style.color = "#302a16";
                    hiddenfc = 1;
                }
                function fc2()
                {
                    document.body.style.color = "#583458";
                    hiddenfc = 2;
                }
                function fc3()
                {
                    document.body.style.color = "black";
                    hiddenfc = 3;
                }
                function fc4()
                {
                    document.body.style.color = "#7a451a";
                    hiddenfc = 4;
                }
                function fc5()
                {
                    document.body.style.color = "#e9e9e9";
                    hiddenfc = 5;
                }
                function fc6()
                {
                    document.body.style.color ="#eadcf5";
                    hiddenfc = 6;
                }


            </script>
            <% 
                if(session.getAttribute("username")!=null)
                {
                    if(session.getAttribute("username").equals(session.getAttribute("artistUsername")))
                    {
            %>
            <div class ="sideMenuCont">
                <div class="sideMenu">
                    <div class="editPic"></div>
                    <div class="menuOptions">
                        <h3>File Upload</h3>
                        <div class ="MOcontent"><p>
                            Select a file to upload: <br/>
                            <form method="POST" action="UploadServlet" enctype="multipart/form-data">
                                <input type="file" name="file" id="file" required/> <br/>
                                <input type="text" name="title" id="title" placeholder="Title" required/><br/>
                                <input type="text" name="description" id="description" placeholder="Description" required/><br/>
                                <input type="text" name="tags" id="tags" placeholder="Tags" required/><br/>
                                <input type="number" name="price" placeholder="Price" required/><br/>
                                <input type="number" name="exclusivePrice" placeHolder="Exclusive Price"/><br/>
                                <input type="submit" value="Upload" name="upload" id="upload" />
                            </form>
                        </div>
                        <h3>Background</h3>
                                <div class ="MOcontent">
                            Select a Background Colour:
                            
                                 <div class="bgcolourHolder">
                                     <button type="button" name="bgc1" onclick="bgc1()" class="bgcbutton" id="bgc1"></button>
                                     <button type="button" name="bgc2" onclick="bgc2()" class="bgcbutton" id="bgc2"></button>
                                     <button type="button" name="bgc3" onclick="bgc3()" class="bgcbutton" id="bgc3"></button>
                                     <button type="button" name="bgc4" onclick="bgc4()" class="bgcbutton" id="bgc4"></button>
                                     <button type="button" name="bgc5" onclick="bgc5()" class="bgcbutton" id="bgc5"></button>
                                     <button type="button" name="bgc6" onclick="bgc6()" class="bgcbutton" id="bgc6"></button>
                                </div>
                            
                                <div class="bgpHolder">
                                        <button type="button" name="bgp1" onclick="bgp1()" class="bgpbutton" id="bgp1"></button>
                                        <button type="button" name="bgp2" onclick="bgp2()" class="bgpbutton" id="bgp2"></button>
                                        <button type="button" name="bgp3" onclick="bgp3()" class="bgpbutton" id="bgp3"></button>
                                        
                                    </div>
                            </div>
                        <h3>Font Style</h3>
                        
                        <div class ="MOcontent">Select a Font Type:
                            
                                     <button type="button" name="ft1" onclick="ft1()" class="ftbutton" id="ft1">Raleway</button>
                                     <button type="button" name="ft2" onclick="ft2()" class="ftbutton" id="ft2">Roboto</button>
                                     <button type="button" name="ft3" onclick="ft3()" class="ftbutton" id="ft3">Playfair Display</button>
                                     <button type="button" name="ft4" onclick="ft4()" class="ftbutton" id="ft4">Dancing Script</button>
                                     <button type="button" name="ft5" onclick="ft5()" class="ftbutton" id="ft5">Philosopher</button>
                                     
                                     
                                     
                                     <div class="fcHolder">
                                        <button type="button" name="fc1" onclick="fc1()" class="fcbutton" id="fc1"></button>
                                        <button type="button" name="fc2" onclick="fc2()" class="fcbutton" id="fc2"></button>
                                        <button type="button" name="fc3" onclick="fc3()" class="fcbutton" id="fc3"></button>
                                        <button type="button" name="fc4" onclick="fc4()" class="fcbutton" id="fc4"></button>
                                        <button type="button" name="fc5" onclick="fc5()" class="fcbutton" id="fc5"></button>
                                        <button type="button" name="fc6" onclick="fc6()" class="fcbutton" id="fc6"></button>
                                    </div>
                                     <button type="button" name="save" onclick="saveAll()">Save</button>
                            </div>
                        
                    </div>
                    
                </div>
            </div>
            <%
                    }
                }
                    
            %>
        </div>
            
            <%
                    String hiddenft = (String) session.getAttribute("hiddenft");
                    String hiddenfc = (String) session.getAttribute("hiddenfc");
                    String hiddenbgc = (String) session.getAttribute("hiddenbgc");
                    String hiddenbgp = (String) session.getAttribute("hiddenbgp");
                    out.print("<script>");
                    if(hiddenft.equals("1"))
                    {
                        out.print("ft1();");
                    }
                    else if(hiddenft.equals("2"))
                    {
                        out.print("ft2();");
                    }
                    else if(hiddenft.equals("3"))
                    {
                        out.print("ft3();");
                    }
                    else if(hiddenft.equals("4"))
                    {
                        out.print("ft4();");
                    }
                    else if(hiddenft.equals("5"))
                    {
                        out.print("ft5();");
                    }
                    
                    if (hiddenbgc.equals("1"))
                    {
                        out.print("bgc1();");
                    }
                    else if (hiddenbgc.equals("2"))
                    {
                        out.print("bgc2();");
                    }
                    else if (hiddenbgc.equals("3"))
                    {
                        out.print("bgc3();");
                    }
                    else if (hiddenbgc.equals("4"))
                    {
                        out.print("bgc4();");
                    }
                    else if (hiddenbgc.equals("5"))
                    {
                        out.print("bgc5();");
                    }
                    else if (hiddenbgc.equals("6"))
                    {
                        out.print("bgc6();");
                    }
                    
                    if (hiddenbgp.equals("1"))
                    {
                        out.print("bgp1();");
                    }
                    else if (hiddenbgp.equals("2"))
                    {
                        out.print("bgp2();");
                    }
                    else if (hiddenbgp.equals("3"))
                    {
                        out.print("bgp3();");
                    }
                    if(hiddenfc.equals("1"))
                    {
                        out.print("fc1();");
                    }
                    else if(hiddenfc.equals("2"))
                    {
                        out.print("fc2();");
                    }
                    else if(hiddenfc.equals("3"))
                    {
                        out.print("fc3();");
                    }
                    else if(hiddenfc.equals("4"))
                    {
                        out.print("fc4();");
                    }
                    else if(hiddenfc.equals("5"))
                    {
                        out.print("fc5();");
                    }
                    else if(hiddenfc.equals("6"))
                    {
                        out.print("fc6();");
                    }
                     out.print("</script>");
                %>
        
        <div class="footer">
            <div class="links"></div>
            <div class="copyrite"></div>               
        </div>
        
        <script>
                    var sideMenuToggle = "sideMenuclose";           
                    $(document).ready(function()
                    {
                      $(".editPic").click(function()
                        {
                        if(sideMenuToggle === "sideMenuclose")
                            {
                            $(".sideMenuCont").animate(
                                {
                                width:'345px'
                                });
                            $(".sideMenu").animate(
                                {
                                width:'300px'
                                });
                             
                           sideMenuToggle = "sideMenuopen"; 
                            }
                        else
                            {
                                $(".sideMenuCont").animate(
                                {
                                width:'60px'
                                });
                            $(".sideMenu").animate(
                                {
                                width:'0px'
                                });
                            
                            sideMenuToggle = "sideMenuclose";
                            }
                        });
                    });
                </script>
                <script>
                    $(function(){
                        $(".MOcontent").show();
                        setTimeout("$('.MOcontent').slideToggle('slow');",1000);
                        $(".menuOptions h3").click(function(){
                            $(this).next(".MOcontent").slideToggle("slow").siblings(".MOcontent:visible").slideUp("slow");
                            $(this).toggleClass("current");
                            $(this).siblings("h3").removeClass("current");
                        });
                    });
                
                </script>
    </body>
</html>
