CREATE OR REPLACE PROCEDURE Insert_Mastertemplate(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ReviewNote_cur IS Select
		Name,
		Note,
		CreateDate,
		moduser
		from table_ReviewNote
	 where objid=pnObjid 
	 and not exists (select *from table_ReviewNote where ReviewNote2Mastertemplate=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Reviewer_cur IS Select
		AsignedDate,
		IsApproved,
		moduser
		from table_Reviewer
	 where objid=pnObjid 
	 and not exists (select *from table_Reviewer where Reviewer2Mastertemplate=pnObjid);



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
		Url,
		moduser
		from table_EmailSetting
	 where objid=pnObjid 
	 and not exists (select *from table_EmailSetting where EmailSetting2Mastertemplate=pnObjid);


--variables
id	NUMBER:=0;
i_ReviewNote_cur	m_ReviewNote_cur%rowtype;
i_Reviewer_cur	m_Reviewer_cur%rowtype;
i_EmailSetting_cur	m_EmailSetting_cur%rowtype;

BEGIN
--opening cursor m_ReviewNote_cur
	id:=0;
Begin
	OPEN m_ReviewNote_cur;
	LOOP
	id := id-1;
	FETCH m_ReviewNote_cur INTO i_ReviewNote_cur;
	EXIT WHEN m_ReviewNote_cur%NOTFOUND;

--Insert records in ReviewNote

	INSERT INTO table_ReviewNote(
		Objid
		,Name,
		Note,
		CreateDate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ReviewNote_cur.Name,
		i_ReviewNote_cur.Note,
		i_ReviewNote_cur.CreateDate,
		id,
		pnObjid,
		i_ReviewNote_cur.groupuser,
		i_ReviewNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ReviewNote_cur;
 End;
--opening cursor m_Reviewer_cur
	id:=0;
Begin
	OPEN m_Reviewer_cur;
	LOOP
	id := id-1;
	FETCH m_Reviewer_cur INTO i_Reviewer_cur;
	EXIT WHEN m_Reviewer_cur%NOTFOUND;

--Insert records in Reviewer

	INSERT INTO table_Reviewer(
		Objid
		,AsignedDate,
		IsApproved,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Reviewer_cur.AsignedDate,
		i_Reviewer_cur.IsApproved,
		id,
		pnObjid,
		i_Reviewer_cur.groupuser,
		i_Reviewer_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Reviewer_cur;
 End;
--opening cursor m_EmailSetting_cur
	id:=0;
Begin
	OPEN m_EmailSetting_cur;
	LOOP
	id := id-1;
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
		Url,
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
		i_EmailSetting_cur.Url,
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
