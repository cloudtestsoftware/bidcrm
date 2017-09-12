CREATE OR REPLACE PROCEDURE Insert_Site(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Store_cur IS Select
		Name,
		Location,
		Phone,
		Email,
		Manager,
		moduser
		from table_Store
	 where objid=pnObjid 
	 and not exists (select *from table_Store where Store2Site=pnObjid);


--variables
id	NUMBER:=0;
i_Store_cur	m_Store_cur%rowtype;

BEGIN
--opening cursor m_Store_cur
	id:=0;
Begin
	OPEN m_Store_cur;
	LOOP
	id := id-1;
	FETCH m_Store_cur INTO i_Store_cur;
	EXIT WHEN m_Store_cur%NOTFOUND;

--Insert records in Store

	INSERT INTO table_Store(
		Objid
		,Name,
		Location,
		Phone,
		Email,
		Manager,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Store_cur.Name,
		i_Store_cur.Location,
		i_Store_cur.Phone,
		i_Store_cur.Email,
		i_Store_cur.Manager,
		id,
		pnObjid,
		i_Store_cur.groupuser,
		i_Store_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Store_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
