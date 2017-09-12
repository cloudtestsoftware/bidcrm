CREATE OR REPLACE PROCEDURE Update_Contactlist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Contactlist set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ContactListLog
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ContactListLog set ContactListLog2Contactlist=pnObjid where ContactListLog2Contactlist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for EmailSetting
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EmailSetting set EmailSetting2Contactlist=pnObjid where EmailSetting2Contactlist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
