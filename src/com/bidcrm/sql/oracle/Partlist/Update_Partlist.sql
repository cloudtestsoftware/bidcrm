CREATE OR REPLACE PROCEDURE Update_Partlist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Partlist set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Parts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Parts set Parts2Partlist=pnObjid where Parts2Partlist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for StorePartList
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_StorePartList set StorePartList2Partlist=pnObjid where StorePartList2Partlist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
