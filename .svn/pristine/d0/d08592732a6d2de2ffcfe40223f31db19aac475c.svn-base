<%-- 
    Document   : testjsp
    Created on : Feb 5, 2015, 2:04:46 PM
    Author     : CPRG352
--%>

<%@page import="pagehelpers.PhotoDisplayHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Demo</title>

  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
  <script type="text/javascript" src='src/jquery.adaptive-backgrounds.js'></script>
  <link rel="stylesheet" type="text/css" href="styles/testcss.css" />
  <script type="text/javascript">
    function checkRegisterPassword()
    {
        document.getElementById('submitbutton').disabled = true;
        var password = document.getElementById("registerpassword").value;
        var confirm = document.getElementById("registerconfirm").value;
        if (password === confirm)
        {
                document.getElementById('submitbutton').disabled = false;
                document.getElementById("registerconfirm").style.borderColor = "white";
                return true;
        }
        else
        {
                document.getElementById("registerconfirm").style.borderColor = "#FF4444";
                return false;
        }
    }
      
    $(document).ready(function(){
      $.adaptiveBackground.run({
        normalizeTextColor: true
      });
      document.getElementById('submitbutton').disabled = true;
      if(document.getElementById("registerMessage").innerHTML !== "")
      {
          alert(document.getElementById("registerMessage").innerHTML);
      }
    });
  </script>
</head>
<body>
    <%
    PhotoDisplayHelper pdh = new PhotoDisplayHelper();
    %>
    <div class="center">
        <br/>
        <%= pdh.displayFullPhoto(10) %>
        <%= pdh.displayFullPhoto(12) %>
        <%=pdh.displayTag("building")%>
        <%=pdh.displayTag("market")%>
        <%=pdh.displayTag("city")%>
        <%=pdh.displayTag("bird")%>
        <%= pdh.displayFullPhoto(8) %>
        <%= pdh.displayFullPhoto(10) %>
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
                            <input type="checkbox" name="type" value="U">Become an Upgraded Member<br/>
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
        <div class='img-wrap'>
            <div class="description" >
                <div class="descHeader">
                    <h1>Queen Elizabeth II</h1>
                    <h4>Greenbelt, Maryland</h4>
                </div>
                <div class='licensing'>
                    <a class='buttonStyle'>$49.99</a>
                </div>
                <div class="descText">
                    Elizabeth II (Elizabeth Alexandra Mary; born 21 April 1926) is the Queen of 16 of the 53 member states in the Commonwealth of Nations. She is also Head of the Commonwealth and Supreme Governor of the Church of England.
                </div>
            </div>
            <img id="img" src="images/QueenTest.jpg" data-adaptive-background='1'>
            <div class="cropContainer">
                <div class="center-cropped"style="background-image: url('images/QueenTest.jpg');"></div>
            </div>
        </div>
        <div class='img-wrap'>
            <div class="description" >
                <div class="descHeader">
                    <h1>Stampede</h1>
                    <h4>Calgary, Alberta</h4>
                </div>

                <div class='licensing'>
                    <a class='buttonStyle'>$49.99</a>
                </div>
                <div class="descText">
                    The Calgary Stampede is an annual rodeo, exhibition and festival held every July in Calgary, Alberta, Canada. The ten-day event, which bills itself as "The Greatest Outdoor Show on Earth".
                </div>
            </div>
            <img id="img" src="images/StampedeTest.jpg" data-adaptive-background='1'>
            <div class="cropContainer">
            <div class="center-cropped"style="background-image: url('images/StampedeTest.jpg');"></div>
            </div>
        </div>
    </div>
    

</body>
</html>
