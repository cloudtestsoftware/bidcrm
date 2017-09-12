CREATE OR REPLACE PROCEDURE Update_Attribute(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Attribute set Name=Name,
		TableName=TableName,
		HasProperty=HasProperty,
		HasCodeObject=HasCodeObject,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for AttrPrivilege
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_AttrPrivilege set AttrPrivilege2Attribute=pnObjid where AttrPrivilege2Attribute=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for CustomList
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_CustomList set CustomList2Attribute=pnObjid where CustomList2Attribute=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ListProperty
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ListProperty set ListProperty2Attribute=pnObjid where ListProperty2Attribute=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
