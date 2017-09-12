CREATE OR REPLACE PROCEDURE Insert_Moduleobject(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ObjectPrivilege_cur IS Select
		Name,
		Title,
		Value,
		Type,
		IsRecursive,
		moduser
		from table_ObjectPrivilege
	 where objid=pnObjid 
	 and not exists (select *from table_ObjectPrivilege where ObjectPrivilege2Moduleobject=pnObjid);


--variables
id	NUMBER:=0;
i_ObjectPrivilege_cur	m_ObjectPrivilege_cur%rowtype;

BEGIN
--opening cursor m_ObjectPrivilege_cur
	id:=0;
Begin
	OPEN m_ObjectPrivilege_cur;
	LOOP
	id := id-1;
	FETCH m_ObjectPrivilege_cur INTO i_ObjectPrivilege_cur;
	EXIT WHEN m_ObjectPrivilege_cur%NOTFOUND;

--Insert records in ObjectPrivilege

	INSERT INTO table_ObjectPrivilege(
		Objid
		,Name,
		Title,
		Value,
		Type,
		IsRecursive,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ObjectPrivilege_cur.Name,
		i_ObjectPrivilege_cur.Title,
		i_ObjectPrivilege_cur.Value,
		i_ObjectPrivilege_cur.Type,
		i_ObjectPrivilege_cur.IsRecursive,
		id,
		pnObjid,
		i_ObjectPrivilege_cur.groupuser,
		i_ObjectPrivilege_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ObjectPrivilege_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
