CREATE OR REPLACE PROCEDURE Update_Emaillist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Emaillist set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for EmailContact
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EmailContact set EmailContact2Emaillist=pnObjid where EmailContact2Emaillist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Accounts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Accounts set Accounts2Emaillist=pnObjid where Accounts2Emaillist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ContactList
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ContactList set ContactList2Emaillist=pnObjid where ContactList2Emaillist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
