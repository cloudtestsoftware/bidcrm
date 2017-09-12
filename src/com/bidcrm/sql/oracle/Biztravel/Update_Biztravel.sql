CREATE OR REPLACE PROCEDURE Update_Biztravel(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Biztravel set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for TravelBooking
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TravelBooking set TravelBooking2Biztravel=pnObjid where TravelBooking2Biztravel=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for TravelExpense
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TravelExpense set TravelExpense2Biztravel=pnObjid where TravelExpense2Biztravel=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
