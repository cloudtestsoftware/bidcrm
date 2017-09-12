CREATE OR REPLACE PROCEDURE Insert_Privilegegroup(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_User_cur IS Select
		Name,
		LastName,
		LoginName,
		Password,
		VerifyPassword,
		Status,
		UserType,
		Email,
		moduser
		from table_User
	 where objid=pnObjid 
	 and not exists (select *from table_User where User2Privilegegroup=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Module_cur IS Select
		Name,
		ModuleIndex,
		moduser
		from table_Module
	 where objid=pnObjid 
	 and not exists (select *from table_Module where Module2Privilegegroup=pnObjid);



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
	 and not exists (select *from table_ObjectPrivilege where ObjectPrivilege2Privilegegroup=pnObjid);


--variables
id	NUMBER:=0;
i_User_cur	m_User_cur%rowtype;
i_Module_cur	m_Module_cur%rowtype;
i_ObjectPrivilege_cur	m_ObjectPrivilege_cur%rowtype;

BEGIN
--opening cursor m_User_cur
	id:=0;
Begin
	OPEN m_User_cur;
	LOOP
	id := id-1;
	FETCH m_User_cur INTO i_User_cur;
	EXIT WHEN m_User_cur%NOTFOUND;

--Insert records in User

	INSERT INTO table_User(
		Objid
		,Name,
		LastName,
		LoginName,
		Password,
		VerifyPassword,
		Status,
		UserType,
		Email,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_User_cur.Name,
		i_User_cur.LastName,
		i_User_cur.LoginName,
		i_User_cur.Password,
		i_User_cur.VerifyPassword,
		i_User_cur.Status,
		i_User_cur.UserType,
		i_User_cur.Email,
		id,
		pnObjid,
		i_User_cur.groupuser,
		i_User_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_User_cur;
 End;
--opening cursor m_Module_cur
	id:=0;
Begin
	OPEN m_Module_cur;
	LOOP
	id := id-1;
	FETCH m_Module_cur INTO i_Module_cur;
	EXIT WHEN m_Module_cur%NOTFOUND;

--Insert records in Module

	INSERT INTO table_Module(
		Objid
		,Name,
		ModuleIndex,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Module_cur.Name,
		i_Module_cur.ModuleIndex,
		id,
		pnObjid,
		i_Module_cur.groupuser,
		i_Module_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Module_cur;
 End;
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
