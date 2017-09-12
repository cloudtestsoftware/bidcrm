CREATE OR REPLACE PROCEDURE Update_Codeattribute(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Codeattribute set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for CustomCode
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_CustomCode set CustomCode2Codeattribute=pnObjid where CustomCode2Codeattribute=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for GenericCode
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_GenericCode set GenericCode2Codeattribute=pnObjid where GenericCode2Codeattribute=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
