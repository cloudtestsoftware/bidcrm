<html>


	<head>
	 
		<script src="./src/codebase/dhtmlx_web.js" type="text/javascript" charset="utf-8"></script>
		<script src="./src/codebase/dhtmlxgrid_export.js" type="text/javascript" charset="utf-8"></script>
		<script src="./src/codebase/dhtmlxgrid_excell_passw.js" type="text/javascript" charset="utf-8"></script>
		<script src="./src/codebase/ext/swfobject.js" type="text/javascript" charset="utf-8"></script>
		
		<link rel="stylesheet" href="./src/codebase/dhtmlx_web.css" type="text/css" charset="utf-8">
		<link rel="stylesheet"  type="text/css" title="deep_blue" href="./src/codebase/skins/web_deep_blue/dhtmlx_custom.css" charset="utf-8">		
		<link rel="alternate stylesheet" type="text/css" title="rose" href="./src/codebase/skins/web_rose/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<link rel="alternate stylesheet" type="text/css" title="grass" href="./src/codebase/skins/web_grass/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<link rel="alternate stylesheet" type="text/css" title="brown" href="./src/codebase/skins/web_brown/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<link rel="alternate stylesheet" type="text/css" title="oak" href="./src/codebase/skins/web_oak/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<link rel="alternate stylesheet" type="text/css" title="summer" href="./src/codebase/skins/web_summer/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<link rel="alternate stylesheet" type="text/css" title="blue" href="./src/codebase/skins/web_blue/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<link rel="alternate stylesheet" type="text/css" title="ocean" href="./src/codebase/skins/web_ocean/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<link rel="alternate stylesheet" type="text/css" title="violet" href="./src/codebase/skins/web_violet/dhtmlx_custom.css" type="text/css" charset="utf-8">
		<script language="JavaScript" src="./src/toolbar.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/change_style.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/biderp_script.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/popup_grid.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/object_data.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/upload_download.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/send_email.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/button_action.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/grid_export.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/grid_utility.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/my_console.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/popup_form.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/context/custom_handler.js" type="text/javascript"></script>
		<script language="JavaScript" src="./src/3rdparty/accounting.js" type="text/javascript"></script>
		
		
		<style>
		   .recordlabel{ 
				font-family: tahoma;
				font-size: 12px;
				font-weight: bold;
				font-style: normal;
				width:20%;
				
			}
			.recordtext{ 
				font-family: tahoma;
				font-size: 12px;
				font-weight: normal;
				font-style: normal;
			}
			
		</style>
		
	</head>
	<body  style='width:100%;height:100%'>
	    <input type="hidden" id="usertoken" value="<%=request.getParameter("usertoken")%>"/>
	    <input type="hidden" id="username" value="<%=request.getParameter("username")%>"/>
	    <input type="hidden" id="refobjid" value="<%=request.getParameter("refobjid")%>"/>
	    <input type="hidden" id="mqid" value="<%=request.getParameter("mqid")%>"/>
		<script type="text/javascript" charset="utf-8">
		
		 var www_url="<%=request.getParameter("baseurl") %>";
		 var username=document.getElementById("username").value;
		 var token=document.getElementById("usertoken").value;
		 var refobjid=document.getElementById("refobjid").value;
		 var mqid=document.getElementById("mqid").value;
		 var my_currency="$";
		 var conversion_rate=1;
		 var timezone=Intl.DateTimeFormat().resolvedOptions().timeZone;
		 var fullname;
		 
		 addMainLayout();
		 addMenuLayout();
		 addMainLeftToolbar();
		 addHiddenForm();
		 addLeftLayout();
		 addTopActivityGrid();
		 addBottomActivityGrid();
		 //addMyReminder();
		 addWorkPlace();
		 addWorkplaceToolbar();
		 addMasterGrid();
		 addActionButtonContainer();
		 addTabBar();
		 addMasterDetailToolbar();
		 addStatusBar();
		 setupMenu();
		 addIntroduction();
		 openRefObject();
		 call_biderp_toolbar_skin("skin_summer");
		
	 
	
		</script>
	</body>
</html>