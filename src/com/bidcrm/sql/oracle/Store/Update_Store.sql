CREATE OR REPLACE PROCEDURE Update_Store(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Store set Name=Name,
		Location=Location,
		Phone=Phone,
		Email=Email,
		Manager=Manager,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for StorePartList
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_StorePartList set StorePartList2Store=pnObjid where StorePartList2Store=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
