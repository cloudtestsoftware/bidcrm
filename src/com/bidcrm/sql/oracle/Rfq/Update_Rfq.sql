CREATE OR REPLACE PROCEDURE Update_Rfq(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Rfq set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for RfqParts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_RfqParts set RfqParts2Rfq=pnObjid where RfqParts2Rfq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for RFQEmail
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_RFQEmail set RFQEmail2Rfq=pnObjid where RFQEmail2Rfq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for TRTasks
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TRTasks set TRTasks2Rfq=pnObjid where TRTasks2Rfq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for QRTasks
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QRTasks set QRTasks2Rfq=pnObjid where QRTasks2Rfq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for BidTasks
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BidTasks set BidTasks2Rfq=pnObjid where BidTasks2Rfq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for POTasks
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_POTasks set POTasks2Rfq=pnObjid where POTasks2Rfq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for EmailDocs
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EmailDocs set EmailDocs2Rfq=pnObjid where EmailDocs2Rfq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
