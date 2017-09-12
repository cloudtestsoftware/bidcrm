CREATE OR REPLACE PROCEDURE Update_Parts(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Parts set Name=Name,
		DomainCode=DomainCode,
		ServiceLife=ServiceLife,
		UmCode=UmCode,
		UnitPrice=UnitPrice,
		PctTax=PctTax,
		PartSpec=PartSpec,
		Note=Note,
		PartNo=PartNo,
		PartListNo=PartListNo,
		url=url,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for QRInfo
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QRInfo set QRInfo2Parts=pnObjid where QRInfo2Parts=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Samples
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Samples set Samples2Parts=pnObjid where Samples2Parts=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
