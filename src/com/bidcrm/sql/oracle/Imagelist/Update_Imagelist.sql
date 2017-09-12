CREATE OR REPLACE PROCEDURE Update_Imagelist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Imagelist set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Images
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Images set Images2Imagelist=pnObjid where Images2Imagelist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for MasterTemplate
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MasterTemplate set MasterTemplate2Imagelist=pnObjid where MasterTemplate2Imagelist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
