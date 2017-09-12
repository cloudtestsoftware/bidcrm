CREATE OR REPLACE PROCEDURE Update_Samplereserve(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Samplereserve set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Samples
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Samples set Samples2Samplereserve=pnObjid where Samples2Samplereserve=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ReorderSamples
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ReorderSamples set ReorderSamples2Samplereserve=pnObjid where ReorderSamples2Samplereserve=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
