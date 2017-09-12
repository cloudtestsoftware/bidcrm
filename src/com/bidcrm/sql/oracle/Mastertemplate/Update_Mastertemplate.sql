CREATE OR REPLACE PROCEDURE Update_Mastertemplate(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Mastertemplate set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ReviewNote
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ReviewNote set ReviewNote2Mastertemplate=pnObjid where ReviewNote2Mastertemplate=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Reviewer
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Reviewer set Reviewer2Mastertemplate=pnObjid where Reviewer2Mastertemplate=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for EmailSetting
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EmailSetting set EmailSetting2Mastertemplate=pnObjid where EmailSetting2Mastertemplate=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
