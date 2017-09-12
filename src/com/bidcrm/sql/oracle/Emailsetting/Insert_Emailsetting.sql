CREATE OR REPLACE PROCEDURE Insert_Emailsetting(pnObjid RAW) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_EmailAttribute_cur IS Select
		Name,
		Value,
		AttributeType,
		LinkText,
		moduser,
		groupuser
		from table_EmailAttribute
	 where objid=pnObjid 
	 and not exists (select *from table_EmailAttribute where EmailAttribute2Emailsetting=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ReviewNote_cur IS Select
		Name,
		Note,
		CreateDate,
		moduser,
		groupuser
		from table_ReviewNote
	 where objid=pnObjid 
	 and not exists (select *from table_ReviewNote where ReviewNote2Emailsetting=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Reviewer_cur IS Select
		AsignedDate,
		IsApproved,
		moduser,
		groupuser
		from table_Reviewer
	 where objid=pnObjid 
	 and not exists (select *from table_Reviewer where Reviewer2Emailsetting=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Automation_cur IS Select
		Name,
		ActAfterWaiting,
		ActIfOpened,
		ActIfClicked,
		ActIfSubscribed,
		ActIfFilledUp,
		ActIfDownloaded,
		ActIfOrdered,
		GreaterThan,
		LessThan,
		moduser,
		groupuser
		from table_Automation
	 where objid=pnObjid 
	 and not exists (select *from table_Automation where Automation2Emailsetting=pnObjid);


--variables
id	RAW(16);
i_EmailAttribute_cur	m_EmailAttribute_cur%rowtype;
i_ReviewNote_cur	m_ReviewNote_cur%rowtype;
i_Reviewer_cur	m_Reviewer_cur%rowtype;
i_Automation_cur	m_Automation_cur%rowtype;

BEGIN
--opening cursor m_EmailAttribute_cur
Begin
	OPEN m_EmailAttribute_cur;
	LOOP
	id := sys_guid();
	FETCH m_EmailAttribute_cur INTO i_EmailAttribute_cur;
	EXIT WHEN m_EmailAttribute_cur%NOTFOUND;

--Insert records in EmailAttribute

	INSERT INTO table_EmailAttribute(
		Objid
		,Name,
		Value,
		AttributeType,
		LinkText,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_EmailAttribute_cur.Name,
		i_EmailAttribute_cur.Value,
		i_EmailAttribute_cur.AttributeType,
		i_EmailAttribute_cur.LinkText,
		id,
		pnObjid,
		i_EmailAttribute_cur.groupuser,
		i_EmailAttribute_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EmailAttribute_cur;
 End;
--opening cursor m_ReviewNote_cur
Begin
	OPEN m_ReviewNote_cur;
	LOOP
	id := sys_guid();
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
Begin
	OPEN m_Reviewer_cur;
	LOOP
	id := sys_guid();
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
--opening cursor m_Automation_cur
Begin
	OPEN m_Automation_cur;
	LOOP
	id := sys_guid();
	FETCH m_Automation_cur INTO i_Automation_cur;
	EXIT WHEN m_Automation_cur%NOTFOUND;

--Insert records in Automation

	INSERT INTO table_Automation(
		Objid
		,Name,
		ActAfterWaiting,
		ActIfOpened,
		ActIfClicked,
		ActIfSubscribed,
		ActIfFilledUp,
		ActIfDownloaded,
		ActIfOrdered,
		GreaterThan,
		LessThan,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Automation_cur.Name,
		i_Automation_cur.ActAfterWaiting,
		i_Automation_cur.ActIfOpened,
		i_Automation_cur.ActIfClicked,
		i_Automation_cur.ActIfSubscribed,
		i_Automation_cur.ActIfFilledUp,
		i_Automation_cur.ActIfDownloaded,
		i_Automation_cur.ActIfOrdered,
		i_Automation_cur.GreaterThan,
		i_Automation_cur.LessThan,
		id,
		pnObjid,
		i_Automation_cur.groupuser,
		i_Automation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Automation_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
