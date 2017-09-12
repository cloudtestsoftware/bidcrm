CREATE OR REPLACE PROCEDURE Update_Financialyear(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Financialyear set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for CampaignCalendar
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_CampaignCalendar set CampaignCalendar2Financialyear=pnObjid where CampaignCalendar2Financialyear=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for EventCalendar
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EventCalendar set EventCalendar2Financialyear=pnObjid where EventCalendar2Financialyear=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for TravelCalendar
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TravelCalendar set TravelCalendar2Financialyear=pnObjid where TravelCalendar2Financialyear=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
