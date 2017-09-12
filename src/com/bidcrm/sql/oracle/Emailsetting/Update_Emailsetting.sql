CREATE OR REPLACE PROCEDURE Update_Emailsetting(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Emailsetting set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for EmailAttribute
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EmailAttribute set EmailAttribute2Emailsetting=pnObjid where EmailAttribute2Emailsetting=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ReviewNote
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ReviewNote set ReviewNote2Emailsetting=pnObjid where ReviewNote2Emailsetting=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Reviewer
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Reviewer set Reviewer2Emailsetting=pnObjid where Reviewer2Emailsetting=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Automation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Automation set Automation2Emailsetting=pnObjid where Automation2Emailsetting=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
