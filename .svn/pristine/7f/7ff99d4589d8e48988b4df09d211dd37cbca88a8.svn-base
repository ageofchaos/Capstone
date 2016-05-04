/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
          document.getElementById('submitbutton').disabled = true;
          if(document.getElementById("registerMessage").innerHTML !== "")
          {
              alert(document.getElementById("registerMessage").innerHTML);
          }
        });


