CREATE OR REPLACE PROCEDURE Insert_Console(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ConsoleLog_cur IS Select
		Name,
		Logdate,
		moduser
		from table_ConsoleLog
	 where objid=pnObjid 
	 and not exists (select *from table_ConsoleLog where ConsoleLog2Console=pnObjid);


--variables
id	NUMBER:=0;
i_ConsoleLog_cur	m_ConsoleLog_cur%rowtype;

BEGIN
--opening cursor m_ConsoleLog_cur
	id:=0;
Begin
	OPEN m_ConsoleLog_cur;
	LOOP
	id := id-1;
	FETCH m_ConsoleLog_cur INTO i_ConsoleLog_cur;
	EXIT WHEN m_ConsoleLog_cur%NOTFOUND;

--Insert records in ConsoleLog

	INSERT INTO table_ConsoleLog(
		Objid
		,Name,
		Logdate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ConsoleLog_cur.Name,
		i_ConsoleLog_cur.Logdate,
		id,
		pnObjid,
		i_ConsoleLog_cur.groupuser,
		i_ConsoleLog_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ConsoleLog_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
