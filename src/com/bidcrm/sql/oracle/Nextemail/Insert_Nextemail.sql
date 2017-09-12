CREATE OR REPLACE PROCEDURE Insert_Nextemail(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



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
		moduser
		from table_Automation
	 where objid=pnObjid 
	 and not exists (select *from table_Automation where Automation2Nextemail=pnObjid);


--variables
id	NUMBER:=0;
i_Automation_cur	m_Automation_cur%rowtype;

BEGIN
--opening cursor m_Automation_cur
	id:=0;
Begin
	OPEN m_Automation_cur;
	LOOP
	id := id-1;
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
