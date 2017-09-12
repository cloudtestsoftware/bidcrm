CREATE OR REPLACE PROCEDURE Insert_Dataimport(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ImportLog_cur IS Select
		Note,
		LogDate,
		moduser
		from table_ImportLog
	 where objid=pnObjid 
	 and not exists (select *from table_ImportLog where ImportLog2Dataimport=pnObjid);


--variables
id	NUMBER:=0;
i_ImportLog_cur	m_ImportLog_cur%rowtype;

BEGIN
--opening cursor m_ImportLog_cur
	id:=0;
Begin
	OPEN m_ImportLog_cur;
	LOOP
	id := id-1;
	FETCH m_ImportLog_cur INTO i_ImportLog_cur;
	EXIT WHEN m_ImportLog_cur%NOTFOUND;

--Insert records in ImportLog

	INSERT INTO table_ImportLog(
		Objid
		,Note,
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
		i_ImportLog_cur.Note,
		i_ImportLog_cur.LogDate,
		id,
		pnObjid,
		i_ImportLog_cur.groupuser,
		i_ImportLog_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ImportLog_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
