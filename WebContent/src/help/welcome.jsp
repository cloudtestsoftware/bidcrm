<html>

<head>
<style>
		   .recordlabel{ 
				font-family: tahoma;
				font-size: 12px;
				font-weight: bold;
				font-style: normal;
			}
			.recordtext{ 
				font-family: tahoma;
				font-size: 12px;
				font-weight: normal;
				font-style: normal;
			}
			.fullname{ 
				font-family: tahoma;
				font-size: 12px;
				color:#E678C4;
				font-weight: bold;
				font-style: normal;
			}
		</style>
</head>
<div style='width:100%;height:100%;overflow:auto'>

<p class="recordlabel">Welcome <font class="fullname"><%=request.getParameter("fullname") %>!</font></p>

<p class="recordtext">
To start choose any app from the App menu
<p>

<p class="recordlabel">
Selecting Menu item from panel
</p>
<ul class="recordtext">
<li>After selecting the Apps, select any item on the panel. 
<li>if you already created data then enter search criteria then click search button. 
<li>Otherwise just click on search button to create new record in the grid. 

</ul>
</div>

</html>