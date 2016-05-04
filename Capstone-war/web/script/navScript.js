/* 
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

var sel1nonToggle = "sel1nonclose";           //Cart animation
$(document).ready(function()
{
    $(".sel1nonhead").click(function()
    {
        if(sel1nonToggle == "sel1nonclose")
        {
            $(".sel1non").animate(
            {
                width:'300px',
                height:'500px'
            });
            sel1nonToggle = "sel1nonopen"; 
        }
        else
        {
            $(".sel1non").animate(
            {
                width:'200px',
                height:'80px'
            });
            sel1nonToggle = "sel1nonclose";
        }
    });
});

$(document).ready(function()
{
    $(".layer1").click(function()
    {
        $(".sel1non").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel2non").click(function()
  {
        $(".sel1non").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
var sel1memberToggle = "sel1memberclose";           //Cart animation
$(document).ready(function()
{
  $(".sel1memberhead").click(function()
    {
    if(sel1memberToggle == "sel1memberclose")
        {
        $(".sel1member").animate(
            {
            width:'300px',
            height:'500px'
            });
       sel1memberToggle = "sel1memberopen"; 
        }
    else
        {
        $(".sel1member").animate(
            {
            width:'200px',
            height:'80px'
            });
        sel1memberToggle = "sel1memberclose";
        }
    });
});
$(document).ready(function()
{
$(".layer1").click(function()
  {
        $(".sel1member").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel2member").click(function()
  {
        $(".sel1member").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
var sel1upgradeToggle = "sel1upgradeclose";           //Cart animation
$(document).ready(function()
{
  $(".sel1upgradehead").click(function()
    {
    if(sel1upgradeToggle == "sel1upgradeclose")
        {
        $(".sel1upgrade").animate(
            {
            width:'300px',
            height:'500px'
            });
       sel1upgradeToggle = "sel1upgradeopen"; 
        }
    else
        {
        $(".sel1upgrade").animate(
            {
            width:'200px',
            height:'80px'
            });
        sel1upgradeToggle = "sel1upgradeclose";
        }
    });
});
$(document).ready(function()
{
$(".layer1").click(function()
  {
        $(".sel1upgrade").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel2upgrade").click(function()
  {
        $(".sel1upgrade").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
var sel1adminToggle = "sel1adminclose";           //Cart animation
$(document).ready(function()
{
  $(".sel1adminhead").click(function()
    {
    if(sel1adminToggle == "sel1adminclose")
        {
        $(".sel1admin").animate(
            {
            width:'300px',
            height:'500px'
            });
       sel1adminToggle = "sel1adminopen"; 
        }
    else
        {
        $(".sel1admin").animate(
            {
            width:'200px',
            height:'80px'
            });
        sel1adminToggle = "sel1adminclose";
        }
    });
});
$(document).ready(function()
{
$(".layer1").click(function()
  {
        $(".sel1admin").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel2admin").click(function()
  {
        $(".sel1admin").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});

var sel2nonToggle = "sel2nonclose";       //Non sel2
$(document).ready(function()
{
  $(".sel2nonhead").click(function()
    {
    if(sel2nonToggle == "sel2nonclose")
        {
        $(".sel2non").animate(
            {
            width:'300px',
            height:'500px'
            });
       sel2nonToggle = "sel2nonopen"; 
        }
    else
        {
        $(".sel2non").animate(
            {
            width:'200px',
            height:'80px'
            });
        sel2nonToggle = "sel2nonclose";
        }
    });
});
$(document).ready(function()
{
$(".layer1").click(function()
  {
        $(".sel2non").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel1non").click(function()
  {
        $(".sel2non").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
var sel2adminToggle = "sel2adminclose";       //Admin sel2
$(document).ready(function()
{
  $(".sel2adminhead").click(function()
    {
    if(sel2adminToggle == "sel2adminclose")
        {
        $(".sel2admin").animate(
            {
            width:'300px',
            height:'500px'
            });
       sel2adminToggle = "sel2adminopen"; 
        }
    else
        {
        $(".sel2admin").animate(
            {
            width:'200px',
            height:'80px'
            });
        sel2adminToggle = "sel2adminclose";
        }
    });
});
$(document).ready(function()
{
$(".layer1").click(function()
  {
        $(".sel2admin").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel1admin").click(function()
  {
        $(".sel2admin").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
var sel2upgradeToggle = "sel2upgradeclose";       //Upgrade sel2
$(document).ready(function()
{
  $(".sel2upgradehead").click(function()
    {
    if(sel2upgradeToggle == "sel2upgradeclose")
        {
        $(".sel2upgrade").animate(
            {
            width:'300px',
            height:'500px'
            });
       sel2upgradeToggle = "sel2upgradeopen"; 
        }
    else
        {
        $(".sel2upgrade").animate(
            {
            width:'200px',
            height:'80px'
            });
        sel2upgradeToggle = "sel2upgradeclose";
        }
    });
});
$(document).ready(function()
{
$(".layer1").click(function()
  {
        $(".sel2upgrade").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel1upgrade").click(function()
  {
        $(".sel2upgrade").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
var sel2memberToggle = "sel2memberclose";       //Member sel2
$(document).ready(function()
{
  $(".sel2memberhead").click(function()
    {
    if(sel2memberToggle == "sel2memberclose")
        {
        $(".sel2member").animate(
            {
            width:'300px',
            height:'500px'
            });
       sel2memberToggle = "sel2memberopen"; 
        }
    else
        {
        $(".sel2member").animate(
            {
            width:'200px',
            height:'80px'
            });
        sel2memberToggle = "sel2memberclose";
        }
    });
});
$(document).ready(function()
{
$(".layer1").click(function()
  {
        $(".sel2member").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});
$(document).ready(function()
{
$(".sel1member").click(function()
  {
        $(".sel2member").animate(
        {
            width:'200px',
            height:'80px'
        });
   });
});



