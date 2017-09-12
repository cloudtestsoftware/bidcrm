CREATE OR REPLACE PROCEDURE Insert_Submodule(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ModuleObject_cur IS Select
		Name,
		Title,
		ObjectRole,
		DesignUse,
		moduser
		from table_ModuleObject
	 where objid=pnObjid 
	 and not exists (select *from table_ModuleObject where ModuleObject2Submodule=pnObjid);


--variables
id	NUMBER:=0;
i_ModuleObject_cur	m_ModuleObject_cur%rowtype;

BEGIN
--opening cursor m_ModuleObject_cur
	id:=0;
Begin
	OPEN m_ModuleObject_cur;
	LOOP
	id := id-1;
	FETCH m_ModuleObject_cur INTO i_ModuleObject_cur;
	EXIT WHEN m_ModuleObject_cur%NOTFOUND;

--Insert records in ModuleObject

	INSERT INTO table_ModuleObject(
		Objid
		,Name,
		Title,
		ObjectRole,
		DesignUse,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ModuleObject_cur.Name,
		i_ModuleObject_cur.Title,
		i_ModuleObject_cur.ObjectRole,
		i_ModuleObject_cur.DesignUse,
		id,
		pnObjid,
		i_ModuleObject_cur.groupuser,
		i_ModuleObject_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ModuleObject_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
