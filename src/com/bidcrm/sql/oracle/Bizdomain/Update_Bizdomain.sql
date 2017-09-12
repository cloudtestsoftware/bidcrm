CREATE OR REPLACE PROCEDURE Update_Bizdomain(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Bizdomain set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for BizProfile
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BizProfile set BizProfile2Bizdomain=pnObjid where BizProfile2Bizdomain=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PartList
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PartList set PartList2Bizdomain=pnObjid where PartList2Bizdomain=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
