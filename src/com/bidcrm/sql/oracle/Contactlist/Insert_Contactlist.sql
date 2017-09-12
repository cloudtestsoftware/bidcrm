CREATE OR REPLACE PROCEDURE Insert_Contactlist(pnObjid RAW) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ContactListLog_cur IS Select
		Note,
		TotalEmail,
		TotalSent,
		TotalInvalid,
		StageCode,
		LogDate,
		moduser,
		groupuser
		from table_ContactListLog
	 where objid=pnObjid 
	 and not exists (select *from table_ContactListLog where ContactListLog2Contactlist=pnObjid);



--Please Modify the cursor as you need

CURSOR m_EmailSetting_cur IS Select
		Name,
		EmailSubject,
		StageCode,
		ContentType,
		ChannelsCode,
		StartDate,
		StartTimeCode,
		EndDate,
		UseMaster,
		UploadUrl,
		moduser,
		groupuser
		from table_EmailSetting
	 where objid=pnObjid 
	 and not exists (select *from table_EmailSetting where EmailSetting2Contactlist=pnObjid);


--variables
id	RAW(16);
i_ContactListLog_cur	m_ContactListLog_cur%rowtype;
i_EmailSetting_cur	m_EmailSetting_cur%rowtype;

BEGIN
--opening cursor m_ContactListLog_cur
Begin
	OPEN m_ContactListLog_cur;
	LOOP
	id := sys_guid();
	FETCH m_ContactListLog_cur INTO i_ContactListLog_cur;
	EXIT WHEN m_ContactListLog_cur%NOTFOUND;

--Insert records in ContactListLog

	INSERT INTO table_ContactListLog(
		Objid
		,Note,
		TotalEmail,
		TotalSent,
		TotalInvalid,
		StageCode,
		LogDate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ContactListLog_cur.Note,
		i_ContactListLog_cur.TotalEmail,
		i_ContactListLog_cur.TotalSent,
		i_ContactListLog_cur.TotalInvalid,
		i_ContactListLog_cur.StageCode,
		i_ContactListLog_cur.LogDate,
		id,
		pnObjid,
		i_ContactListLog_cur.groupuser,
		i_ContactListLog_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ContactListLog_cur;
 End;
--opening cursor m_EmailSetting_cur
Begin
	OPEN m_EmailSetting_cur;
	LOOP
	id := sys_guid();
	FETCH m_EmailSetting_cur INTO i_EmailSetting_cur;
	EXIT WHEN m_EmailSetting_cur%NOTFOUND;

--Insert records in EmailSetting

	INSERT INTO table_EmailSetting(
		Objid
		,Name,
		EmailSubject,
		StageCode,
		ContentType,
		ChannelsCode,
		StartDate,
		StartTimeCode,
		EndDate,
		UseMaster,
		UploadUrl,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_EmailSetting_cur.Name,
		i_EmailSetting_cur.EmailSubject,
		i_EmailSetting_cur.StageCode,
		i_EmailSetting_cur.ContentType,
		i_EmailSetting_cur.ChannelsCode,
		i_EmailSetting_cur.StartDate,
		i_EmailSetting_cur.StartTimeCode,
		i_EmailSetting_cur.EndDate,
		i_EmailSetting_cur.UseMaster,
		i_EmailSetting_cur.UploadUrl,
		id,
		pnObjid,
		i_EmailSetting_cur.groupuser,
		i_EmailSetting_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EmailSetting_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
