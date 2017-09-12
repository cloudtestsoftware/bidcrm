CREATE OR REPLACE PROCEDURE Update_Object(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Object set Name=Name,
		EntityAccessType=EntityAccessType,
		UserAccessType=UserAccessType,
		DefaultFilter=DefaultFilter,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Attribute
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Attribute set Attribute2Object=pnObjid where Attribute2Object=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Help
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Help set Help2Object=pnObjid where Help2Object=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ObjectRule
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ObjectRule set ObjectRule2Object=pnObjid where ObjectRule2Object=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
