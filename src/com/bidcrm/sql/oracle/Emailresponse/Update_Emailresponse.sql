CREATE OR REPLACE PROCEDURE Update_Emailresponse(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Emailresponse set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for AutomationLog
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_AutomationLog set AutomationLog2Emailresponse=pnObjid where AutomationLog2Emailresponse=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ActionNote
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ActionNote set ActionNote2Emailresponse=pnObjid where ActionNote2Emailresponse=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
