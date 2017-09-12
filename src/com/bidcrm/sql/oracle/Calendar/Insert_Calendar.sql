CREATE OR REPLACE PROCEDURE Insert_Calendar(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Month_cur IS Select
		Name,
		YearCode,
		MonthCode,
		moduser
		from table_Month
	 where objid=pnObjid 
	 and not exists (select *from table_Month where Month2Calendar=pnObjid);


--variables
id	NUMBER:=0;
i_Month_cur	m_Month_cur%rowtype;

BEGIN
--opening cursor m_Month_cur
	id:=0;
Begin
	OPEN m_Month_cur;
	LOOP
	id := id-1;
	FETCH m_Month_cur INTO i_Month_cur;
	EXIT WHEN m_Month_cur%NOTFOUND;

--Insert records in Month

	INSERT INTO table_Month(
		Objid
		,Name,
		YearCode,
		MonthCode,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Month_cur.Name,
		i_Month_cur.YearCode,
		i_Month_cur.MonthCode,
		id,
		pnObjid,
		i_Month_cur.groupuser,
		i_Month_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Month_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
