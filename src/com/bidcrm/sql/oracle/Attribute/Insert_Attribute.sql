CREATE OR REPLACE PROCEDURE Insert_Attribute(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_AttrPrivilege_cur IS Select
		Name,
		Value,
		moduser
		from table_AttrPrivilege
	 where objid=pnObjid 
	 and not exists (select *from table_AttrPrivilege where AttrPrivilege2Attribute=pnObjid);



--Please Modify the cursor as you need

CURSOR m_CustomList_cur IS Select
		PropertyString,
		PropertyValue,
		Scope,
		PropIndex,
		moduser
		from table_CustomList
	 where objid=pnObjid 
	 and not exists (select *from table_CustomList where CustomList2Attribute=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ListProperty_cur IS Select
		Name,
		TableName,
		PropertyString,
		PropertyValue,
		Scope,
		PropIndex,
		moduser
		from table_ListProperty
	 where objid=pnObjid 
	 and not exists (select *from table_ListProperty where ListProperty2Attribute=pnObjid);


--variables
id	NUMBER:=0;
i_AttrPrivilege_cur	m_AttrPrivilege_cur%rowtype;
i_CustomList_cur	m_CustomList_cur%rowtype;
i_ListProperty_cur	m_ListProperty_cur%rowtype;

BEGIN
--opening cursor m_AttrPrivilege_cur
	id:=0;
Begin
	OPEN m_AttrPrivilege_cur;
	LOOP
	id := id-1;
	FETCH m_AttrPrivilege_cur INTO i_AttrPrivilege_cur;
	EXIT WHEN m_AttrPrivilege_cur%NOTFOUND;

--Insert records in AttrPrivilege

	INSERT INTO table_AttrPrivilege(
		Objid
		,Name,
		Value,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_AttrPrivilege_cur.Name,
		i_AttrPrivilege_cur.Value,
		id,
		pnObjid,
		i_AttrPrivilege_cur.groupuser,
		i_AttrPrivilege_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_AttrPrivilege_cur;
 End;
--opening cursor m_CustomList_cur
	id:=0;
Begin
	OPEN m_CustomList_cur;
	LOOP
	id := id-1;
	FETCH m_CustomList_cur INTO i_CustomList_cur;
	EXIT WHEN m_CustomList_cur%NOTFOUND;

--Insert records in CustomList

	INSERT INTO table_CustomList(
		Objid
		,PropertyString,
		PropertyValue,
		Scope,
		PropIndex,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_CustomList_cur.PropertyString,
		i_CustomList_cur.PropertyValue,
		i_CustomList_cur.Scope,
		i_CustomList_cur.PropIndex,
		id,
		pnObjid,
		i_CustomList_cur.groupuser,
		i_CustomList_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_CustomList_cur;
 End;
--opening cursor m_ListProperty_cur
	id:=0;
Begin
	OPEN m_ListProperty_cur;
	LOOP
	id := id-1;
	FETCH m_ListProperty_cur INTO i_ListProperty_cur;
	EXIT WHEN m_ListProperty_cur%NOTFOUND;

--Insert records in ListProperty

	INSERT INTO table_ListProperty(
		Objid
		,Name,
		TableName,
		PropertyString,
		PropertyValue,
		Scope,
		PropIndex,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ListProperty_cur.Name,
		i_ListProperty_cur.TableName,
		i_ListProperty_cur.PropertyString,
		i_ListProperty_cur.PropertyValue,
		i_ListProperty_cur.Scope,
		i_ListProperty_cur.PropIndex,
		id,
		pnObjid,
		i_ListProperty_cur.groupuser,
		i_ListProperty_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ListProperty_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
