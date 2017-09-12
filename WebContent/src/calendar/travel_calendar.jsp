<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>Event Calendar Demo - dhtmlxScheduler</title>
	<meta name="description" content="This demo shows a simple JavaScript event calendar where the user can browse the events in Day, Week, Month, Year, or Agenda Views.">
	<meta name="keywords" content="javascript, scheduler, event calendar, events calendar, event, calendar, ajax, google-like">	
</head>
	<script src="../codebase/dhtmlxscheduler41.js" type="text/javascript" charset="utf-8"></script>
	<script src="../codebase/ext/dhtmlxscheduler_year_view.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="../codebase/dhtmlxscheduler41.css" type="text/css" media="screen" title="no title" charset="utf-8">

	
<style type="text/css" media="screen">
	html, body{
		margin:0px;
		padding:0px;
		height:100%;
		overflow:hidden;
	}	
	.dhx_cal_event_line.custom, .dhx_cal_event.custom div{
		background-color:#fd7;
		border-color:#da6;
		color:#444;
	}
</style>

<script type="text/javascript" charset="utf-8">

	var www_url="<%=request.getParameter("baseurl") %>";
	var username="<%=request.getParameter("username") %>";
	var token="<%=request.getParameter("token") %>";
	
	function init() {
		
		
		var url;
		
		var url=www_url+"/rest/appcalendar/biztravel?token="+token;
				
		window.resizeTo(950,700)
		modSchedHeight();
		scheduler.config.xml_date="%Y-%m-%d %H:%i";
		scheduler.config.first_hour = 8;
		scheduler.config.multi_day = true;
		scheduler.config.date_step = "5"
		scheduler.init('scheduler_here',null);
		//scheduler.init('scheduler_here', new Date(2012, 07, 22));
		scheduler.templates.event_class=function(s,e,ev){ return ev.custom?"custom":""; };
		/*
		scheduler.templates.month_date_class=function(date,today){
			if (date.getDate()==16 || date.getDate()==11)
				return "good_day";	
			return "";
		}
		*/
		scheduler.load(url,"json");
		
	}
</script>

<body onload="init();" onresize="modSchedHeight()">
	
	
	
	
	
	
	<!-- info block
		href-prev
		href-next
		title
		desc-short
		desc-long
-->
    <style>
        a img{
            border: none;
        }
        li{
            list-style: none;
        }
    </style>
	<script>
		function modSchedHeight(){
			var headHeight = 100;
			var sch = document.getElementById("scheduler_here");
			sch.style.height = (parseInt(document.body.offsetHeight)-headHeight)+"px";
			var contbox = document.getElementById("contbox");
			contbox.style.width = (parseInt(document.body.offsetWidth)-300)+"px";
		}
	</script>
	<script language="JavaScript" src="../context/custom_handler.js" type="text/javascript"></script>
		
	<div style="position: relative; height:95px;background-color:#3D7F1C;border-bottom:5px solid #828282;">
		<a style="position: absolute; left: 25px; top: 22px; z-index: 10;" href="javascript:showAnnualCalendar(null,null);"><img src="images/btn-left.gif"></a>
		<div id="contbox" style="position: relative; padding: 22px 25px 0 75px; font: normal 17px Arial, Helvetica; color:white;">
			<div style="position: absolute; left: 75px; top: 22px; border-right:5px solid #2D8EB6;color:#2D8EB6;width:175px;height:50px;text-align:right;padding-right:25px;">Travel Calendar</div>
			<div style="padding-left: 205px; min-width: 400px;">
                <div style="font-size:12px;padding-left:20px;">Browse the events in Day, Week, Month, Year, or Agenda Views with Travel Plan.</div>
    		</div>
		</div>
		<a style="position: absolute; right: 25px; top: 22px;" href="javascript:showTravelCampaignOverlayCalendar(null,null);"><img src="images/btn-right.gif"></a>
	</div>
	<!-- end. info block -->
    <ul>
        <li>
            <a></a>
            <span></span>
        </li>
    </ul>
	
	<div id="scheduler_here" class="dhx_cal_container" style='width:100%;height:100%;'>
		<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_today_button"></div>
			<div class="dhx_cal_date"></div>
			<div class="dhx_cal_tab" name="day_tab" style="right:332px;"></div>
			<div class="dhx_cal_tab" name="week_tab" style="right:268px;"></div>
			<div class="dhx_cal_tab" name="month_tab" style="right:204px;"></div>
			<div class="dhx_cal_tab" name="year_tab" style="right:140px;"></div>
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data">
		</div>		
	</div>
	

</body>