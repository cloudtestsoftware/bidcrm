CREATE OR REPLACE PROCEDURE Update_Privilegegroup(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Privilegegroup set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for User
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_User set User2Privilegegroup=pnObjid where User2Privilegegroup=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Module
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Module set Module2Privilegegroup=pnObjid where Module2Privilegegroup=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ObjectPrivilege
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ObjectPrivilege set ObjectPrivilege2Privilegegroup=pnObjid where ObjectPrivilege2Privilegegroup=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
