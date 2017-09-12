CREATE OR REPLACE PROCEDURE Update_Territory(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Territory set Name=Name,
		Country=Country,
		Location=Location,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Account
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Account set Account2Territory=pnObjid where Account2Territory=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Site
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Site set Site2Territory=pnObjid where Site2Territory=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
