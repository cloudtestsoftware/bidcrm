CREATE OR REPLACE PROCEDURE Insert_Emailresponse(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_AutomationLog_cur IS Select
		Name,
		Description,
		StageCode,
		ChannelsCode,
		Status,
		moduser
		from table_AutomationLog
	 where objid=pnObjid 
	 and not exists (select *from table_AutomationLog where AutomationLog2Emailresponse=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ActionNote_cur IS Select
		Name,
		moduser
		from table_ActionNote
	 where objid=pnObjid 
	 and not exists (select *from table_ActionNote where ActionNote2Emailresponse=pnObjid);


--variables
id	NUMBER:=0;
i_AutomationLog_cur	m_AutomationLog_cur%rowtype;
i_ActionNote_cur	m_ActionNote_cur%rowtype;

BEGIN
--opening cursor m_AutomationLog_cur
	id:=0;
Begin
	OPEN m_AutomationLog_cur;
	LOOP
	id := id-1;
	FETCH m_AutomationLog_cur INTO i_AutomationLog_cur;
	EXIT WHEN m_AutomationLog_cur%NOTFOUND;

--Insert records in AutomationLog

	INSERT INTO table_AutomationLog(
		Objid
		,Name,
		Description,
		StageCode,
		ChannelsCode,
		Status,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_AutomationLog_cur.Name,
		i_AutomationLog_cur.Description,
		i_AutomationLog_cur.StageCode,
		i_AutomationLog_cur.ChannelsCode,
		i_AutomationLog_cur.Status,
		id,
		pnObjid,
		i_AutomationLog_cur.groupuser,
		i_AutomationLog_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_AutomationLog_cur;
 End;
--opening cursor m_ActionNote_cur
	id:=0;
Begin
	OPEN m_ActionNote_cur;
	LOOP
	id := id-1;
	FETCH m_ActionNote_cur INTO i_ActionNote_cur;
	EXIT WHEN m_ActionNote_cur%NOTFOUND;

--Insert records in ActionNote

	INSERT INTO table_ActionNote(
		Objid
		,Name,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ActionNote_cur.Name,
		id,
		pnObjid,
		i_ActionNote_cur.groupuser,
		i_ActionNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ActionNote_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
