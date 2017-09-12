CREATE OR REPLACE PROCEDURE Update_Company(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Company set Name=Name,
		Description=Description,
		adminemail=adminemail,
		adminname=adminname,
		phone=phone,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Currency
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Currency set Currency2Company=pnObjid where Currency2Company=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Territory
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Territory set Territory2Company=pnObjid where Territory2Company=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for User
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_User set User2Company=pnObjid where User2Company=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
