CREATE OR REPLACE PROCEDURE Insert_Financialyear(pnObjid RAW) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_CampaignCalendar_cur IS Select
		Name,
		Year,
		StartDate,
		moduser,
		groupuser
		from table_CampaignCalendar
	 where objid=pnObjid 
	 and not exists (select *from table_CampaignCalendar where CampaignCalendar2Financialyear=pnObjid);



--Please Modify the cursor as you need

CURSOR m_EventCalendar_cur IS Select
		Name,
		Year,
		StartDate,
		Count,
		moduser,
		groupuser
		from table_EventCalendar
	 where objid=pnObjid 
	 and not exists (select *from table_EventCalendar where EventCalendar2Financialyear=pnObjid);



--Please Modify the cursor as you need

CURSOR m_TravelCalendar_cur IS Select
		Name,
		Year,
		StartDate,
		Count,
		Amount,
		moduser,
		groupuser
		from table_TravelCalendar
	 where objid=pnObjid 
	 and not exists (select *from table_TravelCalendar where TravelCalendar2Financialyear=pnObjid);


--variables
id	RAW(16);
i_CampaignCalendar_cur	m_CampaignCalendar_cur%rowtype;
i_EventCalendar_cur	m_EventCalendar_cur%rowtype;
i_TravelCalendar_cur	m_TravelCalendar_cur%rowtype;

BEGIN
--opening cursor m_CampaignCalendar_cur
Begin
	OPEN m_CampaignCalendar_cur;
	LOOP
	id := sys_guid();
	FETCH m_CampaignCalendar_cur INTO i_CampaignCalendar_cur;
	EXIT WHEN m_CampaignCalendar_cur%NOTFOUND;

--Insert records in CampaignCalendar

	INSERT INTO table_CampaignCalendar(
		Objid
		,Name,
		Year,
		StartDate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_CampaignCalendar_cur.Name,
		i_CampaignCalendar_cur.Year,
		i_CampaignCalendar_cur.StartDate,
		id,
		pnObjid,
		i_CampaignCalendar_cur.groupuser,
		i_CampaignCalendar_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_CampaignCalendar_cur;
 End;
--opening cursor m_EventCalendar_cur
Begin
	OPEN m_EventCalendar_cur;
	LOOP
	id := sys_guid();
	FETCH m_EventCalendar_cur INTO i_EventCalendar_cur;
	EXIT WHEN m_EventCalendar_cur%NOTFOUND;

--Insert records in EventCalendar

	INSERT INTO table_EventCalendar(
		Objid
		,Name,
		Year,
		StartDate,
		Count,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_EventCalendar_cur.Name,
		i_EventCalendar_cur.Year,
		i_EventCalendar_cur.StartDate,
		i_EventCalendar_cur.Count,
		id,
		pnObjid,
		i_EventCalendar_cur.groupuser,
		i_EventCalendar_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EventCalendar_cur;
 End;
--opening cursor m_TravelCalendar_cur
Begin
	OPEN m_TravelCalendar_cur;
	LOOP
	id := sys_guid();
	FETCH m_TravelCalendar_cur INTO i_TravelCalendar_cur;
	EXIT WHEN m_TravelCalendar_cur%NOTFOUND;

--Insert records in TravelCalendar

	INSERT INTO table_TravelCalendar(
		Objid
		,Name,
		Year,
		StartDate,
		Count,
		Amount,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_TravelCalendar_cur.Name,
		i_TravelCalendar_cur.Year,
		i_TravelCalendar_cur.StartDate,
		i_TravelCalendar_cur.Count,
		i_TravelCalendar_cur.Amount,
		id,
		pnObjid,
		i_TravelCalendar_cur.groupuser,
		i_TravelCalendar_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TravelCalendar_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
