CREATE OR REPLACE PROCEDURE Insert_User(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_MessageQueue_cur IS Select
		Name,
		FirstName,
		LastName,
		DeptCode,
		Email,
		Status,
		Login,
		moduser
		from table_MessageQueue
	 where objid=pnObjid 
	 and not exists (select *from table_MessageQueue where MessageQueue2User=pnObjid);


--variables
id	NUMBER:=0;
i_MessageQueue_cur	m_MessageQueue_cur%rowtype;

BEGIN
--opening cursor m_MessageQueue_cur
	id:=0;
Begin
	OPEN m_MessageQueue_cur;
	LOOP
	id := id-1;
	FETCH m_MessageQueue_cur INTO i_MessageQueue_cur;
	EXIT WHEN m_MessageQueue_cur%NOTFOUND;

--Insert records in MessageQueue

	INSERT INTO table_MessageQueue(
		Objid
		,Name,
		FirstName,
		LastName,
		DeptCode,
		Email,
		Status,
		Login,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_MessageQueue_cur.Name,
		i_MessageQueue_cur.FirstName,
		i_MessageQueue_cur.LastName,
		i_MessageQueue_cur.DeptCode,
		i_MessageQueue_cur.Email,
		i_MessageQueue_cur.Status,
		i_MessageQueue_cur.Login,
		id,
		pnObjid,
		i_MessageQueue_cur.groupuser,
		i_MessageQueue_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_MessageQueue_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
