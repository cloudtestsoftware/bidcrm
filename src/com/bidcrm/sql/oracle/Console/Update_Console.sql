CREATE OR REPLACE PROCEDURE Update_Console(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Console set Name=Name,
		Title=Title,
		Description=Description,
		Note=Note,
		Status=Status,
		KeyObjid=KeyObjid,
		mqid=mqid,
		isRead=isRead,
		isArchived=isArchived,
		ElapseDay=ElapseDay,
		EntryDate=EntryDate,
		AssignedBy=AssignedBy,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ConsoleLog
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ConsoleLog set ConsoleLog2Console=pnObjid where ConsoleLog2Console=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
