CREATE OR REPLACE PROCEDURE Update_Module(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Module set Name=Name,
		ModuleIndex=ModuleIndex,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ModuleObject
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ModuleObject set ModuleObject2Module=pnObjid where ModuleObject2Module=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for SubModule
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_SubModule set SubModule2Module=pnObjid where SubModule2Module=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
